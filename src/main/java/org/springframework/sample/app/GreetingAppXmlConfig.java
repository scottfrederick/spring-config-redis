package org.springframework.sample.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.sample.services.GreetingService;

public class GreetingAppXmlConfig {
    public void run() {
        ApplicationContext parentContext = createParentContext();

        ApplicationContext context = createAppContext(parentContext);

        GreetingService greetingService = context.getBean(GreetingService.class);

        System.out.println(greetingService.getHelloMessage());
        System.out.println(greetingService.getWelcomeMessage());
    }

    private static ApplicationContext createAppContext(ApplicationContext parentContext) {
        String[] locations = {"classpath:/spring/app-context.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations, parentContext);
        context.refresh();
        return context;
    }

    private static ApplicationContext createParentContext() {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/spring/property-source-context.xml");
        context.refresh();

        RedisPropertySource propertySource = context.getBean(RedisPropertySource.class);

        context.getEnvironment().getPropertySources().addFirst(propertySource);

        return context;
    }
}
