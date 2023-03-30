package com.hilquiascamelo.dbqueryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.hilquiascamelo.dbqueryapi.entity")

public class DbQueryApiApplication {

    public static void main ( String[] args ) {
        SpringApplication.run ( DbQueryApiApplication.class , args );
    }

}
