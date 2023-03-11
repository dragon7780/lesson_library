package org.example.config;

import org.example.db.InitDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class Config {
    @Bean
    public InitDatabase initDatabase(){
        InitDatabase initDatabase=new InitDatabase();
        return initDatabase;
    }
}
