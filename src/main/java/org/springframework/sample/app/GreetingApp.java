package org.springframework.sample.app;

public class GreetingApp {

    public static void main(String[] args) {
        GreetingAppXmlConfig app = new GreetingAppXmlConfig();
//        GreetingAppJavaConfig app = new GreetingAppJavaConfig();

        app.run();
    }
}
