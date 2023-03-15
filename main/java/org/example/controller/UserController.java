package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.repository.BookRepository;
import org.example.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;
    public void start() {
        boolean isTrue=true;
        while (isTrue){
            menu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.strScanner.nextInt();
            switch (action){
                case 0-> isTrue=false;
                case 1->bookList();
                case 2->takeBook();
//                case 3->takenBook();
//                case 4->returnBook();
//                case 5->history();
//                case 6->orderBook();
                default -> {
                    System.out.println("Don't be mazgi");
                    isTrue=false;
                }
            }
        }
    }

    private void takeBook() {
        System.out.print("Enter id:");
        int id = ComponentContainer.intScanner.nextInt();
        Boolean takeBook = userService.takeBook(id);

    }

    private void bookList() {
        List<Book> all = bookRepository.getAll();
        all.forEach(System.out::println);
    }

    public void menu(){
        String menu= """
                0 exit
                1 Book List
                2 Take Book
                3 Taken Book
                4 Return Book
                5 History
                6 Order Book
                """;
        System.out.println(menu);
    }
}
