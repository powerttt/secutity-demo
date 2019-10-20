package com.github.powerttt.security14.config;

import com.github.powerttt.security14.handler.LoginExpireHandler;
import com.github.powerttt.security14.handler.LoginFailureHandler;
import com.github.powerttt.security14.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author tongning
 * @Date 2019/10/4 0004
 * function:<
 * <p>
 * >
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserLoginService userDetailsService;

    /**
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* 开启跨域共享，  跨域伪造请求限制=无效 */
        http.cors().and().csrf().disable();

        /* 开启授权认证 */
        http.authorizeRequests().anyRequest().authenticated();

        /* 登录配置 */
        http.formLogin().loginProcessingUrl("/login");

        /* 登录失败后的处理 */
        http.formLogin().failureHandler(new LoginFailureHandler());

        /* 登录过期/未登录 处理 */
        http.exceptionHandling().authenticationEntryPoint(new LoginExpireHandler());

        /* 登录成功后的处理 */
        http.formLogin().successHandler(new LoginSuccessHandler());

        /* rest 无状态 无session */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /* 配置token验证过滤器 */
        http.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
