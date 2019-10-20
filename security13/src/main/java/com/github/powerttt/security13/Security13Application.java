package com.github.powerttt.security13;

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
@MapperScan("**.dao")
public class Security13Application {

    public static void main(String[] args) {
        SpringApplication.run(Security13Application.class);
    }

}
