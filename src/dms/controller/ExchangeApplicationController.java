/**
 * 
 */
package dms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dms.bean.Aircraft;
import dms.bean.DepartureSlot;
import dms.bean.ExchangeApplication;
import dms.bean.RespResult;
import dms.bean.User;
import dms.db.dao.AircraftDAO;
import dms.db.dao.AirlineDAO;
import dms.db.dao.DepartureSlotDAO;
import dms.db.dao.ExchangeApplicationDAO;
import dms.filter.LoggedInFilterBinding;

/**
 * @author Kit
 *
 */
@Path("/exchange-applications")
@Produces({MediaType.APPLICATION_JSON})
@LoggedInFilterBinding
public class ExchangeApplicationController {
	@Context
	private HttpServletRequest request;
	
	private ExchangeApplicationDAO exchangeApplicationDAO  = new ExchangeApplicationDAO();
	private DepartureSlotDAO departureSlotDAO  = new DepartureSlotDAO();
	private AirlineDAO airlineDAO = new AirlineDAO();
	private AircraftDAO aircraftDAO = new AircraftDAO(); 
	
	@GET
	public List<ExchangeApplication> get(){
		User user = SessionHelper.getUser(request);
		List<ExchangeApplication> dataList;
		if (User.TYPE.RAMP_CONTROL.equalsIgnoreCase(user.getType())) {
			dataList = exchangeApplicationDAO.query();
		}else{
			dataList = new ArrayList<>();
			int airlineId = user.getAirlineId();
			List<Aircraft> aircraftList = aircraftDAO.queryByAirlineId(airlineId);
			aircraftList.forEach(aircraft -> {
				departureSlotDAO.queryByAircraft(aircraft.getId()).forEach(departureSlot -> {
					dataList.addAll(exchangeApplicationDAO.queryByFromDepartureSlotId(departureSlot.getId()));
				});;
			});
		}
		return dataList;
	}
	
	@POST
	public RespResult create(ExchangeApplication exchangeApplication){
		User user = SessionHelper.getUser(request);
		exchangeApplication.setUserId(user.getId());
		DepartureSlot fromDS = departureSlotDAO.get(exchangeApplication.getFromDSId());

		if (exchangeApplication.getToDSId() == 0) {
			List<DepartureSlot> availableDSList = departureSlotDAO.queryByTimeRange(new Timestamp(System.currentTimeMillis()), fromDS.getScheduledPushbackTime());
			if (availableDSList.size() > 0) {
				exchangeApplication.setToDSId(availableDSList.get(0).getId());
			}
		}
		
		boolean success = false;
		if (exchangeApplication.getToDSId() > 0) {
			success = exchangeApplicationDAO.insert(exchangeApplication);
		}
		
		return new RespResult(success);
	}
	
	
	@Path("/from")
	@GET
	public List<ExchangeApplication> getByFrom(){
		User user = SessionHelper.getUser(request);
		List<ExchangeApplication> dataList;
		if (User.TYPE.RAMP_CONTROL.equalsIgnoreCase(user.getType())) {
			dataList = exchangeApplicationDAO.query();
		}else{
			dataList = new ArrayList<>();
			int airlineId = user.getAirlineId();
			List<Aircraft> aircraftList = aircraftDAO.queryByAirlineId(airlineId);
			aircraftList.forEach(aircraft -> {
				departureSlotDAO.queryByAircraft(aircraft.getId()).forEach(departureSlot -> {
					dataList.addAll(exchangeApplicationDAO.queryByFromDepartureSlotId(departureSlot.getId()));
				});;
			});
		}
		return dataList;
	}
	
	@Path("/to")
	@GET
	public List<ExchangeApplication> getByTo(){
		User user = SessionHelper.getUser(request);
		List<ExchangeApplication> dataList;
		if (User.TYPE.RAMP_CONTROL.equalsIgnoreCase(user.getType())) {
			dataList = exchangeApplicationDAO.query();
		}else{
			dataList = new ArrayList<>();
			int airlineId = user.getAirlineId();
			List<Aircraft> aircraftList = aircraftDAO.queryByAirlineId(airlineId);
			aircraftList.forEach(aircraft -> {
				departureSlotDAO.queryByAircraft(aircraft.getId()).forEach(departureSlot -> {
					dataList.addAll(exchangeApplicationDAO.queryByToDepartureSlotId(departureSlot.getId()));
				});;
			});
		}
		return dataList;
	}
	
	@Path("{id}")
	@GET
	public ExchangeApplication get(@PathParam("id") int id){
		return exchangeApplicationDAO.get(id);
	}
	
	@Path("{id}")
	@POST
	public RespResult update(ExchangeApplication exchangeApplication){
		boolean success = false;
		if (ExchangeApplication.STATUS.ACCEPTED.equalsIgnoreCase(exchangeApplication.getStatus())) {
			DepartureSlot fromDS = departureSlotDAO.get(exchangeApplication.getFromDSId());
			DepartureSlot toDS = departureSlotDAO.get(exchangeApplication.getToDSId());
			DepartureSlot tempDS = departureSlotDAO.get(exchangeApplication.getToDSId());
			
			toDS.setRequiredPushbackTime(fromDS.getRequiredPushbackTime());
			toDS.setActualPushbackTime(fromDS.getActualPushbackTime());
			toDS.setGateId(fromDS.getGateId());
			toDS.setStatus(fromDS.getStatus());
			toDS.setAircraftId(fromDS.getAircraftId());
			
			departureSlotDAO.update(toDS);
			
			fromDS.setRequiredPushbackTime(tempDS.getRequiredPushbackTime());
			fromDS.setActualPushbackTime(tempDS.getActualPushbackTime());
			fromDS.setGateId(tempDS.getGateId());
			fromDS.setStatus(tempDS.getStatus());
			fromDS.setAircraftId(tempDS.getAircraftId());
			
			departureSlotDAO.update(fromDS);
		}
		success = exchangeApplicationDAO.update(exchangeApplication);
		return new RespResult(success);
	}
	
	
	@Path("{id}")
	@DELETE 
	public RespResult delete(@PathParam("id") int id){
		boolean success = false;
		ExchangeApplication exchangeApplication = new ExchangeApplication();
		exchangeApplication.setId(id);
		if (exchangeApplicationDAO.exist(exchangeApplication)) {
			 success = exchangeApplicationDAO.delete(exchangeApplication);
		}
		return new RespResult(success);
	}
}
