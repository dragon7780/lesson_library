package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.dto.Student;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.example.sevice.AdminService;
import org.example.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class AdminController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    public void start() {
        boolean isTrue=true;
        while (isTrue){
            menu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.intScanner.nextInt();
            switch (action){
                case 0->isTrue=false;
                case 1->bookList();
                case 2->addBook();
                case 3->deleteBook();
                case 4->studentList();
                case 5->addStudent();
//                case 6->deleteStudent();
//                case 7->stTakenBook();
//                case 8->bookTaken();
                default -> {
                    System.out.println("Don't be mazgi");
                    isTrue=false;
                }
            }
        }
    }

    private void addStudent() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        boolean adding= adminService.addStudent(name,surname,phone);
    }

    private void studentList() {
        List<Student> all = studentRepository.getAll();
        all.forEach(System.out::println);
    }

    private void deleteBook() {
        System.out.print("Enter book id: ");
        int id = ComponentContainer.intScanner.nextInt();
        boolean deleting = adminService.deleteBook(id);
        if (deleting){
            System.out.println("Deleting is succesfully!!!");
        }
    }

    private void addBook() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter author: ");
        String author=scanner.nextLine();
        System.out.println("Enter amount: ");
        int amount = ComponentContainer.intScanner.nextInt();
        boolean adding=adminService.addBook(title,author,amount);
        if(adding){
            System.out.println("Book succesfully added!!!");
        }
    }

    private void bookList() {
        List<Book> all = bookRepository.getAll();
        all.forEach(book ->
                System.out.println(book.getId()+"  "+book.getTitle()+" "+book.getAuthor()+" "+ book.getAmount()));
    }

    public void menu(){
        String menu= """
               0 exit
               1 Book List
               2 Add Book
               3 Delete Book
               4 Student List
               5 Add Student
               6 Delete Student
               7 Student taken Book
               8 BookTaken history
                """;
        System.out.println(menu);
    }
}
