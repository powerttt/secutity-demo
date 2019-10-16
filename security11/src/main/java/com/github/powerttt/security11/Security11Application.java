package com.github.powerttt.security11;

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
@MapperScan("com.github.powerttt.security11.dao")
@EnableRedisHttpSession
public class Security11Application {

    public static void main(String[] args) {
        SpringApplication.run(Security11Application.class);
    }

}
