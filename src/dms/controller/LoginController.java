/**
 * 
 */
package dms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dms.bean.RespResult;
import dms.bean.User;
import dms.db.dao.UserDAO;

/**
 * @author Kit
 *
 */
@Path("/login")
@Produces({MediaType.APPLICATION_JSON})
public class LoginController {
	private UserDAO userDAO = new UserDAO();
	
	@Context
	HttpServletRequest request;
	
	@GET
	public boolean isLoggedIn(){
		boolean loggedIn = request.getSession().getAttribute("userid") != null;
		return loggedIn;
	}
	
	
	@POST
	public RespResult login(User loginUser){
		boolean success = false;
		User user = userDAO.getByLoginId(loginUser.getLoginId());
		
		System.out.println(loginUser.getPassword());
		System.out.println(user.getPassword());
		if(user.getPassword().equals(loginUser.getPassword())){
			SessionHelper.setUserId(request, user.getId());
			success = true;
		}
		return new RespResult(success);
	}
	
	@Path("type")
	@GET
	public String getLoginType(){
		User user = SessionHelper.getUser(request);
		return user.getType();
	}
	
	
	@DELETE
	public RespResult logout(){
		boolean success = false;
		request.getSession().invalidate();
		success = true;
		return new RespResult(success);
	}
	
	
}
