package org.springframework.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ValueAnnotationGreetingServiceTests {

    @Autowired
    private GreetingService greetingService;

    @Test
    public void testProperty() throws Exception {
        assertThat(greetingService.getHelloMessage(), equalTo("Hello, from properties file"));
    }

    @Configuration
    @PropertySource(name = "properties", value = "classpath:org/springframework/sample/app.properties")
    static class ContextConfiguration {
        @Value("${greeting.hello}")
        private String hello;

        @Value("${greeting.welcome}")
        private String welcome;

        @Bean
        public GreetingService greetingService() {
            return new GreetingService(hello, welcome);
        }

        @Bean
        public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }
}
