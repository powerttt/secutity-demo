package com.github.powerttt.security08.config;

import com.github.powerttt.security08.dao.SysUserDao;
import com.github.powerttt.security08.handler.LoginSuccessHandler;
import com.github.powerttt.security08.handler.RoleAccessHandler;
import com.github.powerttt.security08.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

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

    @Autowired
    private SysUserDao sysUserDao;

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
     * 自定义身份验证
     * 登录验证方式和密码加密方式。
     * @return
     */
    @Override
    protected UserDetailsService userDetailsService() {

        return username -> {
            if (StringUtils.isEmpty(username)) {
                throw new UsernameNotFoundException("用户名为空");
            }
            SysUser sysUser = sysUserDao.getByUserName(username);
            if (sysUser != null) {
                return sysUser;
            }
            throw new UsernameNotFoundException("用户不存在!");
        };
    }

    /**
     * 根据传递的自定义{@link UserDetailsService}添加身份验证 in。然后返回以允许自定义身份验证。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
