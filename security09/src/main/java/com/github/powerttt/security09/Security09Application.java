package com.github.powerttt.security09;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author tongning
 * @Date 2019/10/3 0003
 * function:<
 * <p>
 * >
 */
@SpringBootApplication
@MapperScan("com.github.powerttt.security09.dao")
@EnableRedisHttpSession
public class Security09Application {

    public static void main(String[] args) {
        SpringApplication.run(Security09Application.class);
    }

}
