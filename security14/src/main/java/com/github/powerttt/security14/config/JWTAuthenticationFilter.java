package com.github.powerttt.security14.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.powerttt.security14.model.SysUser;
import com.github.powerttt.security14.utils.JwtUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @Author tongning
 * @Date 2019/10/19 0019
 * function:<
 * <p>
 * >
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(JwtUtils.HEADER_TOKEN_NAME);
        /* token为null直接走登录的过滤器，不为空走下面 */
        if (token!=null&&token.trim().length()>0) {
            String tokenBody = null;
            try {
                tokenBody = JwtUtils.testJwt(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /* 从token中取出用户信息，放在上下文中 */
            if (tokenBody!=null&&tokenBody.trim().length()>0){
                JSONObject user = JSON.parseObject(tokenBody).getJSONObject("user");
                SysUser sysUser = JSON.toJavaObject(user,SysUser.class);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(sysUser,null,sysUser.getAuthorities()));
            }else{
                HttpServletResponse res = (HttpServletResponse) response;
                res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                res.getWriter().write("{\"code\": \"405\", \"msg\": \"token错误\"}");
                return;
            }
        }
        chain.doFilter(request, response);

    }
}
