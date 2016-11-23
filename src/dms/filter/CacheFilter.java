/**
 * 
 */
package dms.filter;

import java.io.IOException;

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
public class CacheFilter implements ContainerResponseFilter {
	
	
	
	@Context
	private HttpServletRequest request;
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Cache-control", "no-cache, no-store");
		responseContext.getHeaders().add("Pragma", "no-cache");
		responseContext.getHeaders().add("Expires", "-1");
	}

}
