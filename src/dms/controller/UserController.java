/**
 * 
 */
package dms.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dms.bean.User;
import dms.db.dao.UserDAO;

/**
 * @author Kit
 *
 */
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {
	
	@Path("{id}")
	@GET
	public User get(@PathParam("id") int id){
		return new UserDAO().get(id);
	}
	
}
