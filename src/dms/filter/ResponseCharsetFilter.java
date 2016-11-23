/**
 * 
 */
package dms.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * @author Kit
 *
 */
@Provider
public class ResponseCharsetFilter implements ContainerResponseFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		String contentType = responseContext.getHeaderString("Content-Type");
		if (contentType != null && contentType.indexOf(";charset=") == -1) {
			contentType += ";charset=UTF-8";
			responseContext.getHeaders().putSingle("Content-Type", contentType);
		}
	}
}
