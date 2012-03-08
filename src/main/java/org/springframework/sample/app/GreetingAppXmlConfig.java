package org.springframework.sample.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.sample.services.GreetingService;

public class GreetingAppXmlConfig {
    public void run() {
        ApplicationContext context = createAppContext();

        GreetingService greetingService = context.getBean(GreetingService.class);

        System.out.println(greetingService.getHelloMessage());
        System.out.println(greetingService.getWelcomeMessage());
    }

    private static ApplicationContext createAppContext() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"classpath:/spring/app-context.xml"}, false);

        addPropertySources(context);

        context.refresh();

        return context;
    }

    private static void addPropertySources(ConfigurableApplicationContext context) {
        ClassPathXmlApplicationContext propertySourceContext = new ClassPathXmlApplicationContext("classpath:/spring/property-source-context.xml");

        RedisPropertySource propertySource = propertySourceContext.getBean(RedisPropertySource.class);

        context.getEnvironment().getPropertySources().addFirst(propertySource);
    }
}
