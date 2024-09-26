package com.code.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudCommentPagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudCommentPagingApplication.class, args);
    }

}
