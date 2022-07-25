package com.gabit.ob_api.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @Value("${app.variable}")
    String message;

    @GetMapping()
    public String saludar() {

        System.out.println(message);

        return "Saludos";
    }
}
