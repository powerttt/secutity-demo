package com.github.powerttt.security12.handler;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author tongning
 * @Date 2019/10/19 0019
 * function:<
 * 登入过期，失败，未登入处理
 * >
 */
public class LoginExpireHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE);
        response.getWriter().write("{\"code\": \"401\", \"msg\": \"登录过期或未登录\"}");
    }
}
