package org.example.config;

import org.example.db.InitDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@ComponentScan(basePackages = "org.example")
public class Config {
    @Bean
    public InitDatabase initDatabase(){
        InitDatabase initDatabase=new InitDatabase();
        return initDatabase;
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Atto");
        dataSource.setUsername("postgres");
        dataSource.setPassword("azizbek");
        return dataSource;
    }
    @Bean
    public JdbcTemplate getTemplate(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
}
