/**
 * 
 */
package dms.controller;

import javax.servlet.http.HttpServletRequest;

import dms.bean.User;
import dms.db.dao.UserDAO;

/**
 * @author Kit
 *
 */
public class SessionHelper {

	public static void setUserId(HttpServletRequest request, int userId){
		request.getSession().setAttribute("userid", userId);
	}
	public static int getUserId(HttpServletRequest request){
		return (int) request.getSession().getAttribute("userid");
	}
	
	public static User getUser(HttpServletRequest request){
		int userId = (int) request.getSession().getAttribute("userid");
		return new UserDAO().get(userId);
	}
}
