package org.springframework.sample.web;

import org.springframework.util.StringUtils;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static org.springframework.web.context.ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        String initializerClasses = servletContext.getInitParameter(CONTEXT_INITIALIZER_CLASSES_PARAM);

        String propertySourceInitClassName = JavaConfigPropertySourceInitializer.class.getName();
//        String propertySourceInitClassName = XmlConfigPropertySourceInitializer.class.getName();

        if (StringUtils.hasText(initializerClasses)) {
            initializerClasses += " " + propertySourceInitClassName;
        }
        else {
            initializerClasses = propertySourceInitClassName;
        }

        servletContext.setInitParameter(CONTEXT_INITIALIZER_CLASSES_PARAM, initializerClasses);
    }
}
