package org.example.container;

import org.example.dto.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
@Component
public class ComponentContainer {
    public static Student currentUser=null;
    public static Scanner intScanner=new Scanner(System.in);
    public static Scanner strScanner=new Scanner(System.in);
}
