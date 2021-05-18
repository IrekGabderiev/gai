package com.irek.gai.controller;

import com.irek.gai.services.IssueOfNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class MainController {
    @Autowired
    private IssueOfNumbers number;

    @GetMapping("/random")
    public String random(){
        return number.randomNumber();
    }

    @GetMapping("/next")
    public String next(){
        return number.nextNumber();
    }
}
