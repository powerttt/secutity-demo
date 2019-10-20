package com.github.powerttt.security14.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author tongning
 * @Date 2019/10/19 0019
 * function:<
 * <p>
 * >
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

}
