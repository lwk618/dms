/**
 * 
 */
package dms.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

/**
 * @author Kit
 *
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
@LoggedInFilterBinding
public class LoggedInFilter implements ContainerRequestFilter {

	@Context
	HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
//		System.out.println("trigger LoggedInFilter");
		if (request.getSession().getAttribute("userid") == null) {
			ctx.abortWith(Response.status(Status.UNAUTHORIZED).build());
		}
	}

}
