package com.lweizhou.cai.godwife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class GodWifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GodWifeApplication.class, args);
    }

}
