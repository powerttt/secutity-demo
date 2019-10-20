package com.github.powerttt.security13.config;

import com.github.powerttt.security13.dao.SysUserDao;
import com.github.powerttt.security13.handler.*;
import com.github.powerttt.security13.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
/**
 * 默认为false，需要开启
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*开启跨域共享，跨域伪造请求限制=无效*/
        http.cors().and().csrf().disable();

        /*开启授权认证*/
        http
                /*允许基于{@link HttpServletRequest}使用限制访问*/
                .authorizeRequests()
                /*映射任何请求*/
                .anyRequest()
                /*指定任何经过身份验证的用户都允许使用URL*/
                .authenticated();

        /* 登录配置 */
        http.formLogin().usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");

        /* 登录失败后的处理 */
        http.formLogin().failureHandler(new LoginFailureHandler());

        /* exceptionHandling 允许配置异常处理。使用时会自动应用 */

        /*登入过期，失败，未登入处理*/
        http.exceptionHandling().authenticationEntryPoint(new LoginExpireHandler());

        /* 权限不足处理*/
        http.exceptionHandling().accessDeniedHandler(new AuthLimitHandler());

        /* 登入成功处理*/
        http.formLogin().successHandler(new LoginSuccessHandler());

        /* 退出成功处理器 */
        http.logout().logoutUrl("/logout").permitAll()
                .invalidateHttpSession(true)
                .logoutSuccessHandler(new LoginoutHandler());
    }

    @Autowired(required = false)
    private SysUserDao sysUserDao;

    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            if (username==null||username.trim().length()<=0) {
                throw new UsernameNotFoundException("用户名为空");
            }

            SysUser sysUser = sysUserDao.getByUserName(username);
            if (sysUser != null){
                return sysUser;
            }
            throw new UsernameNotFoundException("用户不存在!");
        };
    }


    /**
     * 添加跨域
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        /**
         * 配置跨源请求处理。
         */
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

}
