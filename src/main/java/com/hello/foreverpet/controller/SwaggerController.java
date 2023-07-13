package com.hello.foreverpet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping("/")
    public String test() {
        return "redirect:/swagger-ui.html";
    }
    // test
}
