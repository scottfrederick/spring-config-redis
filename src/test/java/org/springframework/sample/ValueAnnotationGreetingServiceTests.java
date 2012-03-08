package org.springframework.sample;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.sample.services.GreetingService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = CustomAnnotationConfigContextLoader.class)
@ActiveProfiles("test")
public class ValueAnnotationGreetingServiceTests {

    @Autowired
    private GreetingService greetingService;

    @BeforeClass
    public static void setUp() {
        System.setProperty("greeting.welcome", "Welcome, from System.properties");
    }

    @Test
    public void testProperty() throws Exception {
        assertThat(greetingService.getHelloMessage(), equalTo("Hello, from properties file"));
        assertThat(greetingService.getWelcomeMessage(), equalTo("Welcome, from System.properties"));
    }



    @Configuration
    @PropertySource(name = "properties", value = "classpath:org/springframework/sample/app.properties")
    static class ContextConfiguration {

        @Bean
        public GreetingService greetingService(@Value("${greeting.hello}") String hello, @Value("${greeting.welcome}") String welcome) {
            return new GreetingService(hello, welcome);
        }

        @Bean
        public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }
}
