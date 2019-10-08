package com.github.powerttt.security05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdController {

    @GetMapping("/ad/hello")
    public String ad(){
        return "ad";
    }
}
