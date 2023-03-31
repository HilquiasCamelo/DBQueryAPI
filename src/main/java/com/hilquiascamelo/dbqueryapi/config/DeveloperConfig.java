package com.hilquiascamelo.dbqueryapi.config;

import com.hilquiascamelo.dbqueryapi.service.util.SeedDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
@ComponentScan (basePackages = "com.hilquiascamelo.dbqueryapi")
public class DeveloperConfig {

    @Autowired
    private SeedDbService seedDbService;

    @Value ("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!"update".equals(strategy)) {
            return false;
        }

        seedDbService.seedDatabase();
        return true;
    }


}