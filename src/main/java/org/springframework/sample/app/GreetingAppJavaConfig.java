package org.springframework.sample.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.env.redis.RedisPropertySource;
import org.springframework.sample.config.AppConfig;
import org.springframework.sample.config.PropertySourceConfig;
import org.springframework.sample.services.GreetingService;

public class GreetingAppJavaConfig {
    public void run() {
        ApplicationContext context = createAppContext();

        GreetingService greetingService = context.getBean(GreetingService.class);

        System.out.println(greetingService.getHelloMessage());
        System.out.println(greetingService.getWelcomeMessage());
    }

    private ApplicationContext createAppContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.getEnvironment().getPropertySources().addFirst(getPropertySource());

        context.register(AppConfig.class);
        context.refresh();

        return context;
    }

    private PropertySource getPropertySource() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertySourceConfig.class);
        context.refresh();

        return context.getBean(RedisPropertySource.class);
    }
}
