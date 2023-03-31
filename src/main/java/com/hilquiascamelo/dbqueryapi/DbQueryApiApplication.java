package com.hilquiascamelo.dbqueryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.hilquiascamelo.dbqueryapi.entity")

public class DbQueryApiApplication {

    public static void main ( String[] args ) {
        SpringApplication.run ( DbQueryApiApplication.class , args );
    }

}
