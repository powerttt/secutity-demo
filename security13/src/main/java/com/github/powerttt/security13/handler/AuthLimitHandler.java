package com.github.powerttt.security13.handler;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author tongning
 * @Date 2019/10/19 0019
 * function:<
 * 登入没有权限
 * >
 */
public class AuthLimitHandler implements AccessDeniedHandler {
    /**
     * 处理拒绝访问失败
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write("{\"code\": \"403\", \"msg\": \"权限不足\"}");
    }
}
