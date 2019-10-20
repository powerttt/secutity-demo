package com.github.powerttt.security13.controller;

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

    @GetMapping("one")
    public String one(){
        return "one";
    }

    @GetMapping("two")
    public String two(){
        return "two";
    }

    @GetMapping("three")
    public String three(){
        return "three";
    }

    @GetMapping("error")
    public Object error(){
        Map map = new HashMap();
        map.put("code","500");
        map.put("msg","未知错误");
        return map;
    }
}
