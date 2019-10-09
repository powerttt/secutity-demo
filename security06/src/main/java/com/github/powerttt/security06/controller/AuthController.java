package com.github.powerttt.security06.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class AuthController {

    /**
     * 只有角色ONE才能访问
     * @Secured注解,这个是springsecurity提供的,不用导入额外依赖
     */
    @Secured("ROLE_ONE")
    @GetMapping("/one")
    public String one() {
        return "auth/one";
    }

    /**
     * 表达式的注解@PreAuthorize
     * 注意:PreAuthorize(".....")中采用的是SpEL表达式,常用的
     *      hasRole('ROLE_USER')
     *      hasAnyRole('ROLE_USER','ROLE_ADMIN',...)
     * 只有角色TWO才能访问
     */
    @PreAuthorize("hasRole('TWO')")
    @GetMapping("/two")
    public String two() {
        return "auth/two";
    }

    /**
     * JSR-250注解   @RolesAllowed 表示访问对应方法时所应该具有的角色, 拥有该角色的才能访问次方法
     * 只有角色THREE才能访问
     */
    @RolesAllowed("THREE")
    @GetMapping("/three")
    public String three() {
        return "auth/three";
    }

    /**
     * 权限不足时默认展示的页面
     */
    @GetMapping("/limit")
    public String limit() {
        return "auth/limit";
    }
}


