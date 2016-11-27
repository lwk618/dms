/**
 * 
 */
package dms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import dms.bean.RespResult;
import dms.bean.User;
import dms.db.dao.UserDAO;
import dms.filter.LoggedInFilterBinding;

/**
 * @author Kit
 *
 */
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
@LoggedInFilterBinding
public class UserController {
	private UserDAO userDAO  = new UserDAO();
	
	@GET
	public List<User> get(){
		return userDAO.query();
	}
	
	@POST
	public RespResult create(User user){
		boolean success = userDAO.insert(user);
		return new RespResult(success);
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
		return new RespResult(success);
	}
	
	
	@Path("{id}")
	@DELETE 
	public RespResult delete(@PathParam("id") int id){
		boolean success = false;
		User user = new User();
		user.setId(id);
		if (userDAO.exist(user)) {
			 success = userDAO.delete(user);
		}
		return new RespResult(success);
	}
	
	@Context
	private ServletContext application;
	
	@Path("export")
	@GET
	@Produces({MediaType.APPLICATION_OCTET_STREAM})
	public Response export(@QueryParam("id") List<Integer> idList ) throws IOException{
		JSONArray userJA = new JSONArray();
		for (int id : idList) {
			User user = userDAO.get(id);
			if (user != null) {
				userJA.put(new JSONObject(user));
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		File jsonFile = File.createTempFile("export-"+sdf.format(new Date()), "json");
		FileWriter fw = new FileWriter(jsonFile);
		fw.write(userJA.toString());
		fw.close();
		
		File tempZipFile = File.createTempFile("export-"+sdf.format(new Date()), "zip");
		try (FileInputStream fis = new FileInputStream(jsonFile);
				ZipOutputStream zipOS = new ZipOutputStream(new FileOutputStream(tempZipFile));){
			zipOS.putNextEntry(new ZipEntry("users.json"));
			
			
			IOUtils.copy(fis, zipOS);
			
//			byte[] buffer = new byte[512];
//			int length;
//			
//			while((length = fis.read(buffer)) != -1){
//				zipOS.write(buffer, 0, length);
//			}
			
//			fis.close();
//			zipOS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
//		new ZipFile(File.createTempFile("export-"+new Date().toString(), "zip"));
		
		
		return Response.ok(tempZipFile)
				.type(application.getMimeType(tempZipFile.getAbsolutePath()))
				.header("Content-Disposition", "inline; filename=\"users.backup.zip\"")
				.header("Content-Length", tempZipFile.length())
				.build();
	}
}
