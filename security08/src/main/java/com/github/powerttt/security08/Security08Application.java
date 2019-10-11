package com.github.powerttt.security08;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author tongning
 * @Date 2019/10/3 0003
 * function:<
 * <p>
 * >
 */
@SpringBootApplication
@MapperScan("com.github.powerttt.security08.dao")
public class Security08Application {

    public static void main(String[] args) {
        SpringApplication.run(Security08Application.class);
    }

}
