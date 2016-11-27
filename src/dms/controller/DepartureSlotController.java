/**
 * 
 */
package dms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dms.bean.Aircraft;
import dms.bean.Airline;
import dms.bean.DepartureSlot;
import dms.bean.RespResult;
import dms.bean.User;
import dms.db.dao.AircraftDAO;
import dms.db.dao.AirlineDAO;
import dms.db.dao.DepartureSlotDAO;
import dms.filter.LoggedInFilterBinding;

/**
 * @author Kit
 *
 */
@Path("/departure-slots")
@Produces({MediaType.APPLICATION_JSON})
@LoggedInFilterBinding
public class DepartureSlotController {
	@Context
	private HttpServletRequest request;
	
	private DepartureSlotDAO departureSlotDAO  = new DepartureSlotDAO();
	private AirlineDAO airlineDAO = new AirlineDAO();
	private AircraftDAO aircraftDAO = new AircraftDAO(); 
	@GET
	public List<DepartureSlot> get(@QueryParam("status")@DefaultValue("available") String status, @QueryParam("from")@DefaultValue("0001-01-01") String from, @QueryParam("to")@DefaultValue("9999-12-30") String to){
		User user = SessionHelper.getUser(request);
		List<DepartureSlot> dataList;
		if (User.TYPE.RAMP_CONTROL.equalsIgnoreCase(user.getType())) {
			dataList = departureSlotDAO.query(from, to);
		}else{
			dataList = new ArrayList<>();
			int airlineId = user.getAirlineId();
			List<Aircraft> aircraftList = aircraftDAO.queryByAirlineId(airlineId);
			aircraftList.forEach(aircraft -> {
				dataList.addAll(departureSlotDAO.queryByAircraftOrStatus(aircraft.getId(), status, from, to));
			});
		}
		
		return dataList;
	}
	
	@POST
	public RespResult create(DepartureSlot departureSlot){
		User user = SessionHelper.getUser(request);
		Airline airline = airlineDAO.get(user.getAirlineId());
		departureSlot.setGateId(airline.getGateId());
		boolean success = departureSlotDAO.insert(departureSlot);
		return new RespResult(success);
	}
	
	@Path("{id}")
	@GET
	public DepartureSlot get(@PathParam("id") int id){
		return departureSlotDAO.get(id);
	}
	
	@Path("{id}")
	@POST
	public RespResult update(DepartureSlot departureSlot){
		User user = SessionHelper.getUser(request);
		DepartureSlot curDS = departureSlotDAO.get(departureSlot.getId());
		departureSlot.setScheduledPushbackTime(curDS.getScheduledPushbackTime());
		Airline airline = airlineDAO.get(user.getAirlineId());
		departureSlot.setGateId(airline.getGateId());
		boolean success = departureSlotDAO.update(departureSlot);
		return new RespResult(success);
	}
	
	
	@Path("{id}")
	@DELETE 
	public RespResult delete(@PathParam("id") int id){
		boolean success = false;
		DepartureSlot departureSlot = departureSlotDAO.get(id);
		departureSlot.setAircraftId(null);
		departureSlot.setGateId(null);
		departureSlot.setRequiredPushbackTime(null);
		departureSlot.setActualPushbackTime(null);
		departureSlot.setStatus(DepartureSlot.STATUS.AVAILABLE);
		
		if (departureSlotDAO.exist(departureSlot)) {
			 success = departureSlotDAO.update(departureSlot);
		}
		return new RespResult(success);
	}
	
	@Path("airlines/{airlineId}")
	@GET
	public List<DepartureSlot> queryDepartureSlotByAirline(@PathParam("airlineId") int airlineId){
		List<DepartureSlot> dataList = new ArrayList<>();
		List<Aircraft> aircraftList = aircraftDAO.queryByAirlineId(airlineId);
		aircraftList.forEach(aircraft -> {
			dataList.addAll(departureSlotDAO.queryByAircraft(aircraft.getId()));
		});
		return dataList;
	}
}
