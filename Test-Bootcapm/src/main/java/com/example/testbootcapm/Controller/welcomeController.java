package com.example.testbootcapm.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcomeController")
public class welcomeController {
    String name;
    int age;
    String status;
    String health;

    @GetMapping("/name")
    public String name(){
        this.name = "Abdulrahman";
        return "My name is " + this.name;
    }

    @GetMapping("/age")
    public String age(){
        this.age = 26;
        return "My age is " + age;
    }

    @GetMapping("/check/status")
    public String check(){
        this.status = "Everything OK";
        return this.status;
    }
    @GetMapping("/health")
    public String health (){
        this.health = "Server health is up and running";
        return this. health;
    }
}
