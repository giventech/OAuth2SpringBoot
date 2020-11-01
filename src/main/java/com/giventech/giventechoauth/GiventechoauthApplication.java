package com.giventech.giventechoauth;

import com.giventech.giventechoauth.configuration.MethodSecurityConfig;
import com.giventech.giventechoauth.configuration.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class GiventechoauthApplication {

    public static void main(String[] args) {

        SpringApplication.run(GiventechoauthApplication.class, args);
    }

}
