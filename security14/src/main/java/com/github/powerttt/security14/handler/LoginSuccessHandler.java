package com.github.powerttt.security14.handler;

import com.alibaba.fastjson.JSONObject;
import com.github.powerttt.security14.model.Jwt;
import com.github.powerttt.security14.model.SysUser;
import com.github.powerttt.security14.utils.JwtUtils;
import org.springframework.http.MediaType;
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
        // 获取登录成功信息
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        /*身份验证的主体的身份。在使用用户名和密码进行身份验证的情况下，这将是用户名。 要求呼叫者填充身份验证请求的主体。*/
        SysUser user = (SysUser) authentication.getPrincipal();
        user.setPassword(null);
        long now = System.currentTimeMillis();

        JSONObject payload = new JSONObject();
        // 签发人
        payload.put("iss", "sys");
        // 受众
        payload.put("aud", user.getUsername());
        // 过期时间
        payload.put("exp", now + JwtUtils.EXPIRE_TIME);
        // 生效时间
        payload.put("nbf", now);
        // 签发时间
        payload.put("iat", now);
        // 编号
        payload.put("jti", user.getId());
        // 主题
        payload.put("sub", "JWT-TEST");
        // 用户信息
        payload.put("user", user);
        String responseMessage = null;
        try {
            httpServletResponse.setHeader(JwtUtils.HEADER_TOKEN_NAME, new Jwt(payload.toJSONString()).toString());
            responseMessage = "{\"code\": \"200\", \"msg\": \"登录成功\"}";
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage = "{\"code\": \"500\", \"msg\": \"登录失败\"}";
        } finally {
            httpServletResponse.getWriter().write(responseMessage);
        }
    }


}
