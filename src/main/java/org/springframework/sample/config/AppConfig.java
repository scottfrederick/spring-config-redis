package org.springframework.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.sample.services.GreetingService;

@Configuration
@ComponentScan({"org.springframework.sample"})
@PropertySource("classpath:org/springframework/sample/app.properties")
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public GreetingService greetingService() {
        String helloMessage = env.getProperty("greeting.hello");
        String welcomeMessage = env.getProperty("greeting.welcome");

        return new GreetingService(helloMessage, welcomeMessage);
    }
}
