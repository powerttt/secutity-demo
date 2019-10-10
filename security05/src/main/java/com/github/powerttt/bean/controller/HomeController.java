package com.github.powerttt.bean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author tongning
 * @Date 2019/10/8 0008
 * function:<
 * <p>
 * >
 */

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
