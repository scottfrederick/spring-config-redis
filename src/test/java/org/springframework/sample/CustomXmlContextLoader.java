package org.springframework.sample;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.test.context.support.GenericXmlContextLoader;

public class CustomXmlContextLoader extends GenericXmlContextLoader {
    @Override
    protected void customizeContext(GenericApplicationContext context) {
        ClassPathXmlApplicationContext propertySourceContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring/property-source-context.xml");

        PropertySource propertySource = propertySourceContext.getBean(RedisPropertySource.class);

        context.getEnvironment().getPropertySources().addFirst(propertySource);
    }
}
