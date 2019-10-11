package com.github.powerttt.security07.config;

import com.github.powerttt.security07.handler.LoginSuccessHandler;
import com.github.powerttt.security07.handler.RoleAccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @Author tongning
 * @Date 2019/10/4 0004
 * function:<
 * <p>
 * >
 */
@Configuration
/**
 * 没人为false，需要开启
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭csrf
        http.csrf().disable();

        // 激活登入处理器
        http.formLogin().successHandler(new LoginSuccessHandler());

        // 配置登录页面
        http.formLogin().loginPage("/login").permitAll();

        // 配置登录成功后的默认页面 -- 此处使用AuthenticationSuccessHandler#onAuthenticationSuccess()
        // http.formLogin().defaultSuccessUrl("/home");

        // 配置登录页面
        http.logout().permitAll();
        // 用户权限不足处理器
        http.exceptionHandling().accessDeniedHandler(new RoleAccessHandler());

        //授权配置----无状态
        http.authorizeRequests().antMatchers("/css/**", "/img/**", "/url/**").permitAll()
                .anyRequest().fullyAuthenticated();
    }

    /**
     * 注入security user工具类
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

}
