package org.example;

import org.example.config.Config;
import org.example.controller.AuthController;
import org.example.db.Database;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        AuthController authController = (AuthController) context.getBean("authController");
        Database dataBase = (Database) context.getBean("database");
        dataBase.initTable();
        dataBase.adminInit();
        authController.start();
    }
}