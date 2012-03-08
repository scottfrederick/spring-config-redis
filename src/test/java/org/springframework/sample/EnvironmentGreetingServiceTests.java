package org.springframework.sample;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.sample.config.AppConfig;
import org.springframework.sample.services.GreetingService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = CustomAnnotationConfigContextLoader.class, classes = {AppConfig.class})
@ActiveProfiles("test")
public class EnvironmentGreetingServiceTests {

    @Autowired
    private GreetingService service;

    @BeforeClass
    public static void setUp() {
        System.setProperty("greeting.welcome", "Welcome, from System.properties");
    }

    @Test
    public void testProperty() throws Exception {
        assertThat(service.getHelloMessage(), equalTo("Hello, from properties file"));
        assertThat(service.getWelcomeMessage(), equalTo("Welcome, from System.properties"));
    }
}
