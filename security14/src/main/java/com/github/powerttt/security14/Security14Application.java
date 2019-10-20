package com.github.powerttt.security14;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author tongning
 * @Date 2019/10/19 0019
 * function:<
 * <p>
 * >
 */
@MapperScan("**.dao")
@SpringBootApplication
public class Security14Application {

    public static void main(String[] args) {
        SpringApplication.run(Security14Application.class);
    }

}
