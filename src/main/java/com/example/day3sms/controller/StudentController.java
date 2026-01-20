package com.example.day3sms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @GetMapping("/")
    public String student(){
        return "This is the homepage for Student Management System";
    }
}
