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

import dms.bean.RespResult;
import dms.bean.User;
import dms.bean.Airline;
import dms.db.dao.AirlineDAO;
import dms.filter.LoggedInFilterBinding;

/**
 * @author Kit
 *
 */
@Path("/airlines")
@Produces({MediaType.APPLICATION_JSON})
@LoggedInFilterBinding
public class AirlineController {
	@Context
	private HttpServletRequest request;
	
	private AirlineDAO airlineDAO  = new AirlineDAO();
	
	@GET
	public List<Airline> get(){
		User user = SessionHelper.getUser(request);
		List<Airline> dataList;
		if (user == null || User.TYPE.RAMP_CONTROL.equalsIgnoreCase(user.getType())) {
			dataList = airlineDAO.query();
		}else{
			dataList = new ArrayList<>();
			dataList.add(airlineDAO.get(user.getAirlineId()));
		}
		return dataList;
	}
	
	@POST
	public RespResult create(Airline airline){
		boolean success = airlineDAO.insert(airline);
		return new RespResult(success);
	}
	
	@Path("{id}")
	@GET
	public Airline get(@PathParam("id") int id){
		return airlineDAO.get(id);
	}
	
	@Path("{id}")
	@POST
	public RespResult update(Airline airline){
		boolean success = airlineDAO.update(airline);
		return new RespResult(success);
	}
	
	
	@Path("{id}")
	@DELETE 
	public RespResult delete(@PathParam("id") int id){
		boolean success = false;
		Airline airline = new Airline();
		airline.setId(id);
		if (airlineDAO.exist(airline)) {
			 success = airlineDAO.delete(airline);
		}
		return new RespResult(success);
	}
}
