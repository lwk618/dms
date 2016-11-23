/**
 * 
 */
package dms.controller;

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
import dms.bean.RespResult;
import dms.bean.User;
import dms.db.dao.AircraftDAO;

/**
 * @author Kit
 *
 */
@Path("/aircrafts")
@Produces({MediaType.APPLICATION_JSON})
public class AircraftController {
	@Context
	private HttpServletRequest request;
	
	private AircraftDAO aircraftDAO  = new AircraftDAO();
	
	@GET
	public List<Aircraft> get(){
		User user = SessionHelper.getUser(request);
		List<Aircraft> dataList;
		if (user.getAirlineId() == 0) {
			dataList = aircraftDAO.query();
		}else{
			dataList = aircraftDAO.queryByAirlineId(user.getAirlineId());
		}
		
		return dataList;
	}
	
	@POST
	public RespResult create(Aircraft aircraft){
		User user = SessionHelper.getUser(request);
		aircraft.setAirlineId(user.getAirlineId());
		boolean success = aircraftDAO.insert(aircraft);
		return new RespResult();
	}
	
	@Path("{id}")
	@GET
	public Aircraft get(@PathParam("id") int id){
		return aircraftDAO.get(id);
	}
	
	@Path("{id}")
	@POST
	public RespResult update(Aircraft aircraft){
		boolean success = aircraftDAO.update(aircraft);
		return new RespResult();
	}
	
	
	@Path("{id}")
	@DELETE 
	public RespResult delete(@PathParam("id") int id){
		boolean success;
		Aircraft aircraft = new Aircraft();
		aircraft.setId(id);
		if (aircraftDAO.exist(aircraft)) {
			 success = aircraftDAO.delete(aircraft);
		}
		return new RespResult();
	}
}
