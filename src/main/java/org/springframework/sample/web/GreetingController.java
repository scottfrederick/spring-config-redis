package org.springframework.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.sample.services.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
    @Autowired
    private GreetingService service;

    @RequestMapping("/")
    public @ResponseBody String greet() {
        return service.getHelloMessage() +
                "<br/>" +
                service.getWelcomeMessage();
    }
}
