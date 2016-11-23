package dms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.reflections.Reflections;

@ApplicationPath("/api")
public class RestfulApplication extends Application{

	private static Set<Class<?>> s = new HashSet<Class<?>>();

	static {
		//filter
		Reflections filterReflections = new Reflections("dms.filter");
		Set<Class<? extends Object>> allFilterClasses = filterReflections.getTypesAnnotatedWith(Provider.class);
		s.addAll(allFilterClasses);
		
//		//controller
//		Reflections controllerReflections = new Reflections("hkom.frontend.restful.controller", new SubTypesScanner(false));
//		Set<Class<? extends Object>> allControllerClasses = controllerReflections.getSubTypesOf(Object.class);
//		s.addAll(allControllerClasses);

		//controller
		Reflections controllerReflections = new Reflections("dms.controller");
		Set<Class<? extends Object>> allControllerClasses = controllerReflections.getTypesAnnotatedWith(Path.class);
		s.addAll(allControllerClasses);
		
		
		//Feature
		System.out.println("class set length:"+s.size());
		s.stream().forEach(System.out::println);
		
		
		
		s.add(MultiPartFeature.class);
		
		//		//filter
		//		s.add(LanguageFilter.class);
		//		s.add(CacheFilter.class);
		//		s.add(ResponseCharsetFilter.class);
		//		
		//		//controller
		//		s.add(AdvertisementController.class);
		//        s.add(ImageLibraryController.class);
		//        s.add(CategoryController.class);
		//        s.add(NavigationCategoryController.class);
		//        s.add(ProductController.class);
		//        s.add(IndexTrimDownController.class);
		//        s.add(InfoController.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return s;
	}
	
//	@Override
//	public Map<String, Object> getProperties() {
//		Map<String, Object> properties = new HashMap<>(super.getProperties());
//		properties.put(ServerProperties.WADL_FEATURE_DISABLE, true);
//		return properties;
//	}
}
