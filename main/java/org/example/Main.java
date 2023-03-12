package org.example;

import org.example.config.Config;
import org.example.dto.Student;
import org.example.repository.StudentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
//        AuthController authController = (AuthController) context.getBean("authController");
//        authController.start();
        StudentRepository studentRepository=(StudentRepository) context.getBean("studentRepository");
        Student studentById = studentRepository.getStudentById(777);
        Student studentByPhone = studentRepository.getStudentByPhone("12");
        System.out.println(studentByPhone);
    }
}