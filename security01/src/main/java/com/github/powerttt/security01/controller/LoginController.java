package com.github.powerttt.security01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @Author tongning
 * @Date 2019/10/3 0003
 * function:<
 * <p>
 * >
 */
@Controller
public class LoginController {

    @GetMapping("/")
    public String login(){
        return "home";
    }
}
