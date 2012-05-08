package org.springframework.sample.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.sample.services.GreetingService;

public class GreetingAppXmlConfig {
    public void run() {
        ApplicationContext context = createAppContext();

        GreetingService greetingService = context.getBean(GreetingService.class);

        System.out.println(greetingService.getHelloMessage());
        System.out.println(greetingService.getWelcomeMessage());
    }

    private ApplicationContext createAppContext() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"classpath:/META-INF/spring/app-context.xml"}, false);

        context.getEnvironment().getPropertySources().addFirst(getPropertySource());

        context.refresh();

        return context;
    }

    private PropertySource getPropertySource() {
        ClassPathXmlApplicationContext propertySourceContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring/property-source-context.xml");

        return propertySourceContext.getBean(RedisPropertySource.class);
    }
}
