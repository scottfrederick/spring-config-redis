package org.springframework.sample.web;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class XmlConfigPropertySourceInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {
    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        propertySources.addFirst(getPropertySource());
    }

    private PropertySource getPropertySource() {
        ClassPathXmlApplicationContext propertySourceContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring/property-source-context.xml");

        return propertySourceContext.getBean(RedisPropertySource.class);
    }
}
