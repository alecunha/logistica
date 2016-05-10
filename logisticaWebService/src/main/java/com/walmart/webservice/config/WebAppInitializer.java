package com.walmart.webservice.config;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.walmart.repository.config.RepositoryConfiguration;
import com.walmart.service.config.ServiceConfig;



public class WebAppInitializer {/*implements WebApplicationInitializer {

	public void onStartup(final ServletContext servletContext)
			throws ServletException {

		AnnotationConfigWebApplicationContext root = null;

		// Bootstrap
		root = bootstrapSpring(servletContext);

		// Application Listeners (Spring, Logging, etc)
		registerListeners(servletContext, root);

		// Application Filters
		registerFilters(servletContext, root);

		// Application Servlets
		registerServlets(servletContext, root);
	}
	
	private void registerFilters(ServletContext servletContext,
			AnnotationConfigWebApplicationContext root) {

		//servletContext.addFilter("springSecurityFilterChain",new DelegatingFilterProxy("springSecurityFilterChain", root)).addMappingForUrlPatterns(null, false, "/*");
		
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		servletContext.addFilter("encoding-filter", cef).addMappingForUrlPatterns(null, false, "/*");;

	}

	private void registerServlets(ServletContext servletContext,
			AnnotationConfigWebApplicationContext root) {

		// MVC -------------
		final ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(root));
		registration.setLoadOnStartup(0);
		registration.addMapping("/*");
	}

	private void registerListeners(ServletContext servletContext, AnnotationConfigWebApplicationContext root) {

		servletContext.addListener(new ContextLoaderListener(root));
		servletContext.addListener(new RequestContextListener());
	}

	// Initializes a Configurable Bean Context
	private AnnotationConfigWebApplicationContext bootstrapSpring(ServletContext servletContext) {

		final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.setServletContext(servletContext); // registers the servlet context
		root.register(RepositoryConfiguration.class, ServiceConfig.class, ApplicationConfiguration.class);
		root.refresh(); // refreshes all beans
		return root;
	}*/

}