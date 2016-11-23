/**
 * 
 */
package dms.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dms.bean.RespResult;
import dms.bean.User;
import dms.db.dao.UserDAO;

/**
 * @author Kit
 *
 */
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {
	private UserDAO userDAO  = new UserDAO();
	
	@GET
	public List<User> get(){
		return userDAO.query();
	}
	
	@POST
	public RespResult create(User user){
		boolean success = userDAO.insert(user);
		return new RespResult();
	}
	
	@Path("{id}")
	@GET
	public User get(@PathParam("id") int id){
		return new UserDAO().get(id);
	}
	
	@Path("{id}")
	@POST
	public RespResult update(User user){
		boolean success = userDAO.update(user);
		return new RespResult();
	}
	
	
	@Path("{id}")
	@DELETE 
	public RespResult delete(@PathParam("id") int id){
		boolean success;
		User user = new User();
		user.setId(id);
		if (userDAO.exist(user)) {
			 success = userDAO.delete(user);
		}
		return new RespResult();
		
	}
}
