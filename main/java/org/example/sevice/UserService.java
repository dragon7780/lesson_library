package org.example.sevice;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.dto.StudentBook;
import org.example.enums.BookRole;
import org.example.repository.BookRepository;
import org.example.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentBookRepository repository;

    public Boolean takeBook(int id) {
        List<Book> byId = bookRepository.getBookById(id);
        Book book=new Book();
        for (Book book1 : byId) {
            book1=book;
        }
        if(byId.size()==0){
            System.err.println("this book doesn't exist");
            return false;
        } else {
            StudentBook studentBook=new StudentBook();
            studentBook.setStudent_id(ComponentContainer.currentUser.getId());
            studentBook.setBook_id(id);
            studentBook.setDuration(10);
            studentBook.setReturnedDate(LocalDate.now().plusDays(10));
            studentBook.setCreatedDate(LocalDate.now());
            studentBook.setStatus("TAKEN");
            bookRepository.changeAmountById(id);
            repository.save(studentBook);
            return true;
        }

    }
}
