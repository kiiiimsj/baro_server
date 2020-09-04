package com.wantchu.wantchu_server2.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//public class WebConfig implements WebApplicationInitializer {
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        // DispatcherServlet 설정 -s
//        // 1. DispatcherServlet WebApplicationContext 객체 생성
//        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
//        webContext.register(WebContext.class);
//
//        // 2. DispatcherServlet 객체 생성 및 추가
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
//
//        // 3. 서블릿 매핑 및 부가 설정
//        servlet.addMapping("*.do");
//        servlet.setLoadOnStartup(1);
//        // DispatcherServlet 설정 -e
//
//        // Root Context Config 설정 -s
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(RootContext.class);
//
//        ContextLoaderListener listener = new ContextLoaderListener(rootContext);
//        servletContext.addListener(listener);
//        // Root Context Config 설정 -e
//
//        // Filter 설정 -s
//        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
//        filter.setInitParameter("encoding", "UTF-8");
//        filter.addMappingForServletNames(null, false, "dispatcher");
//        // Filter 설정 -e
//    }
//}
