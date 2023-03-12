package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Student;
import org.example.enums.StudentRole;
import org.example.repository.StudentRepository;
import org.example.sevice.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private AdminController adminController;


    public void start(){
        boolean isTrue=true;
        while (isTrue){
            menu();
            System.out.print("Enter action: ");
            try {
                int action = ComponentContainer.intScanner.nextInt();
                switch (action) {
                    case 0->isTrue=false;
                    case 1->LogIn();
                    default -> isTrue=false;
                }
            }catch (RuntimeException e){
                System.out.println("Don't be mazgi ");
                e.printStackTrace();
                return;
            }
        }
    }

    private void LogIn() {
        System.out.print("Enter your name: ");
        String name = ComponentContainer.strScanner.nextLine();
        System.out.print("Enter your id: ");
        Integer id = ComponentContainer.strScanner.nextInt();
        boolean isActive=authService.LogIn(id);
        if (!isActive){
            return;
        }else {
            Student student = studentRepository.getStudentById(id);
            ComponentContainer.currentUser=student;
            if(student.getRole().equals(StudentRole.USER)){
                userController.start();
            }else {
                adminController.start();
            }
        }
    }

    public void menu(){
        String menu= """
                0 exit
                1 Log In
                """;
        System.out.println(menu);
    }
}
