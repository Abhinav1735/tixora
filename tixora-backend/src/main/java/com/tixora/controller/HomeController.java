package com.tixora.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("api")
    public String home(){
        return "Welcome to Tixora Movie Ticket Booking System !";
    }
}
