package org.springframework.sample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.sample.config.PropertySourceConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

public class CustomAnnotationConfigContextLoader extends AnnotationConfigContextLoader {
    @Override
    protected void customizeContext(GenericApplicationContext context) {
        AnnotationConfigApplicationContext propertyLoaderContext =
                new AnnotationConfigApplicationContext(PropertySourceConfig.class);

        PropertySource propertySource = propertyLoaderContext.getBean(RedisPropertySource.class);

        context.getEnvironment().getPropertySources().addFirst(propertySource);
    }
}
