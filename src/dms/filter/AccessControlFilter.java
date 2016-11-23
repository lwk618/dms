/**
 * 
 */
package dms.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 * @author Kit
 *
 */

@Provider
public class AccessControlFilter implements ContainerResponseFilter {

	public static Set<String> ALLOW_ORIGIN_LIST = new HashSet<>();

	@Context
	private HttpServletRequest request;
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		//CORS
		String remoteOrigin = requestContext.getHeaderString("Origin");
		responseContext.getHeaders().add("Access-Control-Allow-Origin", remoteOrigin);
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		responseContext.getHeaders().add("Access-Control-Max-Age", "86400");

		String acRequestHeader = requestContext.getHeaderString("Access-Control-Request-Headers");
        if (acRequestHeader != null) {
        	acRequestHeader = "Origin, Content-Type, Accept, Authorization, X-Requested-With";
        }
		responseContext.getHeaders().add("Access-Control-Allow-Headers", acRequestHeader);
	}

}
