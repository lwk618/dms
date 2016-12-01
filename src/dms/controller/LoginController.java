/**
 * 
 */
package dms.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

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
			user.setLastLogin(new Timestamp(System.currentTimeMillis()));
			success = userDAO.update(user);
		}
		return new RespResult(success);
	}
	
	@Path("type")
	@GET
	public Response getLoginType(){
		String userType="";
		if(request.getSession().getAttribute("userid")!=null){
			User user = SessionHelper.getUser(request);
			userType = user.getType();
		}
		return Response.ok(new JSONObject().put("type", userType).toString()).build();
	}
	
	@Path("info")
	@GET
	public User getLoggedInUserInfo(){
		User user = SessionHelper.getUser(request);
		if (user != null) {
			user.setPassword("");
		}
		return user;
	}
	
	
	@DELETE
	public RespResult logout(){
		boolean success = false;
		request.getSession().invalidate();
		success = true;
		return new RespResult(success);
	}
	
	
}
