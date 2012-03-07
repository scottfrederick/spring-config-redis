package org.springframework.sample.services;

public class GreetingService {
    private String hello;
    private String welcome;

    public GreetingService(String hello, String welcome) {
        this.hello = hello;
        this.welcome = welcome;
    }

    public String getHelloMessage() {
        return hello;
    }

    public String getWelcomeMessage() {
        return welcome;
    }
}
