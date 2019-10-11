package com.github.powerttt.security05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author tongning
 * @Date 2019/10/3 0003
 * function:<
 * <p>
 * >
 */
@SpringBootApplication
public class MainConfig {

    public static void main(String[] args) {
        SpringApplication.run(MainConfig.class);
    }

    @Bean
    public User user(){
        return new User();
    }

}
