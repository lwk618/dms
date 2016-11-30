/**
 * 
 */
package dms.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.dsa224;

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
	public RespResult create(ExchangeApplication exchangeApplication) throws ParseException{
		User user = SessionHelper.getUser(request);
		exchangeApplication.setUserId(user.getId());
		DepartureSlot fromDS = departureSlotDAO.get(exchangeApplication.getFromDSId());
		
		System.out.println(exchangeApplication);
		if (exchangeApplication.getToDSId() == 0) {
			System.out.println("exchangeApplication.getToDSId() == 0");
			//auto assign available departure slot
			List<DepartureSlot> availableDSList = new ArrayList<>();
			if (ExchangeApplication.TYPE.EARLY.equalsIgnoreCase(exchangeApplication.getType())) {
				System.out.println("EARLY");
				System.out.println(new Timestamp(System.currentTimeMillis()));
				System.out.println(fromDS.getScheduledPushbackTime());
				//early departure
				availableDSList = departureSlotDAO.queryByStatus(DepartureSlot.STATUS.AVAILABLE, new Timestamp(System.currentTimeMillis()), fromDS.getScheduledPushbackTime());
			}else{
				System.out.println("DELAY");
				//delay departure
				availableDSList = departureSlotDAO.queryByStatus(DepartureSlot.STATUS.AVAILABLE, fromDS.getScheduledPushbackTime(), new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-30").getTime()));
			}

			availableDSList.forEach(System.out::println);
			if (availableDSList.size() > 0) {
				exchangeApplication.setToDSId(availableDSList.get(0).getId());
			}
		}
		
		boolean success = false;
		if (exchangeApplication.getToDSId() > 0) {
			//exchange departure slot
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
			
			System.out.println(fromDS);
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
