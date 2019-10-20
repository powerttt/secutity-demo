package com.github.powerttt.security12.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @PreAuthorize("hasRole('HELLO')")
    public String say() {
        return "hello";
    }
}
