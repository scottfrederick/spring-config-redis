package org.springframework.sample.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.sample.config.AppConfig;
import org.springframework.sample.config.PropertySourceConfig;
import org.springframework.sample.services.GreetingService;

public class GreetingAppJavaConfig {
    public void run() {
        ApplicationContext parentContext = createParentContext();

        ApplicationContext context = createAppContext(parentContext);

        GreetingService greetingService = context.getBean(GreetingService.class);

        System.out.println(greetingService.getHelloMessage());
        System.out.println(greetingService.getWelcomeMessage());
    }

    private static ApplicationContext createAppContext(ApplicationContext parentContext) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setParent(parentContext);
        context.register(AppConfig.class);
        context.refresh();
        return context;
    }

    private static ApplicationContext createParentContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertySourceConfig.class);
        context.refresh();

        RedisPropertySource propertySource = context.getBean(RedisPropertySource.class);

        context.getEnvironment().getPropertySources().addFirst(propertySource);

        return context;
    }
}
