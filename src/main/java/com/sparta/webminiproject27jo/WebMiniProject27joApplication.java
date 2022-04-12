package com.sparta.webminiproject27jo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebMiniProject27joApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMiniProject27joApplication.class, args);
    }

}
