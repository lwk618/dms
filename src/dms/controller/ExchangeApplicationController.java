/**
 * 
 */
package dms.controller;

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
import dms.bean.ExchangeApplication;
import dms.bean.RespResult;
import dms.bean.User;
import dms.db.dao.AircraftDAO;
import dms.db.dao.AirlineDAO;
import dms.db.dao.DepartureSlotDAO;
import dms.db.dao.ExchangeApplicationDAO;

/**
 * @author Kit
 *
 */
@Path("/exchange-applications")
@Produces({MediaType.APPLICATION_JSON})
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
	
//	@POST
//	public RespResult create(ExchangeApplication exchangeApplication){
//		User user = SessionHelper.getUser(request);
//		exchangeApplication.setAirlineId(user.getAirlineId());
//		boolean success = exchangeApplicationDAO.insert(exchangeApplication);
//		return new RespResult();
//	}
	
	
	@Path("/from")
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
		boolean success = exchangeApplicationDAO.update(exchangeApplication);
		return new RespResult();
	}
	
	
	@Path("{id}")
	@DELETE 
	public RespResult delete(@PathParam("id") int id){
		boolean success;
		ExchangeApplication exchangeApplication = new ExchangeApplication();
		exchangeApplication.setId(id);
		if (exchangeApplicationDAO.exist(exchangeApplication)) {
			 success = exchangeApplicationDAO.delete(exchangeApplication);
		}
		return new RespResult();
	}
}
