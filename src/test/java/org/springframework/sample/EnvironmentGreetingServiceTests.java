package org.springframework.sample;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class EnvironmentGreetingServiceTests {

    @Autowired(required = false)
    private GreetingService service;

    @BeforeClass
    public static void setUp() {
        System.setProperty("greeting.welcome", "Welcome, from System.properties");

        if (System.getProperty("spring.profiles.active") == null) {
            System.setProperty("spring.profiles.active", "test");
        }
    }

    @Test
    public void testProperty() throws Exception {
        assertThat(service.getHelloMessage(), equalTo("Hello, from properties file"));
        assertThat(service.getWelcomeMessage(), equalTo("Welcome, from System.properties"));
    }



    @Configuration
    @PropertySource("classpath:org/springframework/sample/app.properties")
    static class ContextConfiguration {
        @Autowired
        Environment env;

        @Bean
        public GreetingService exampleService() {
            String helloMessage = env.getProperty("greeting.hello");
            String welcomeMessage = env.getProperty("greeting.welcome");

            return new GreetingService(helloMessage, welcomeMessage);
        }
    }

    @Configuration
    @Profile("prod")
    static class ProdContextConfiguration {
        @Bean
        @Order(0)
        public PropertySource externalConfigPropertySource() {
            return null;
        }
    }
}
