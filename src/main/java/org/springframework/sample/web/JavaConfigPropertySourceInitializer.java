package org.springframework.sample.web;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.sample.config.PropertySourceConfig;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class JavaConfigPropertySourceInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {
    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        propertySources.addFirst(getPropertySource());
    }

    private PropertySource getPropertySource() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertySourceConfig.class);

        return context.getBean(RedisPropertySource.class);
    }
}
