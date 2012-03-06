package org.springframework.sample;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class XmlConfigGreetingServiceTests {

    @Autowired
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
}
