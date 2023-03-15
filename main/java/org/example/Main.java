package org.example;

import org.example.config.Config;
import org.example.controller.AuthController;
import org.example.dto.Student;;
import org.example.enums.StudentRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//        SessionFactory factory = meta.getSessionFactoryBuilder().build();
//        Session session = factory.openSession();
//        factory.close();
//        session.close();

        ApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        AuthController authController = (AuthController) context.getBean("authController");
        authController.start();
//        StudentRepository studentRepository=(StudentRepository) context.getBean("studentRepository");
//        Student studentById = studentRepository.getStudentById(777);
//        Student studentByPhone = studentRepository.getStudentByPhone("12");
//        System.out.println(studentByPhone);
    }
}