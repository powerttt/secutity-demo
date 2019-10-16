package com.github.powerttt.security11.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author tongning
 * @Date 2019/10/9 0009
 * function:<
 * <p>
 * >
 */
public class RoleAccessHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        System.out.println("您没有权限访问地址：" + httpServletRequest.getRequestURI());
        httpServletResponse.sendRedirect("/limit");
    }
}
