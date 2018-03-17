package com.wordstalk.legal.drive.inject;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by y on 2017/12/24.
 */
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppContext.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("springMVC",new DispatcherServlet(ctx));

        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
