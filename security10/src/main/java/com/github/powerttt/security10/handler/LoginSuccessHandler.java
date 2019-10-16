package com.github.powerttt.security10.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author tongning
 * @Date 2019/10/8 0008
 * function:<
 * <p>
 * >
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 登入成功处理
     * 登入失败：AuthenticationFailureHandler
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功！!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // some thing
        httpServletResponse.sendRedirect("/home");
    }
}
