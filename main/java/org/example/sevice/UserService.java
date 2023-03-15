package org.example.sevice;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.dto.StudentBook;
import org.example.enums.BookRole;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    private BookRepository bookRepository;

    public Boolean takeBook(int id) {
        Book byId = bookRepository.getBookById(id);
        if(byId==null){
            System.err.println("this book doesn't exist");
            return false;
        }else {
            StudentBook studentBook=new StudentBook();
            studentBook.setStudent_id(ComponentContainer.currentUser.getId());
            studentBook.setBook_id(byId.getId());
            studentBook.setDuration(10);
            studentBook.setReturnedDate(LocalDate.now().plusDays(10));
            studentBook.setStatus(BookRole.TAKEN);

            bookRepository.changeAmountById(id);
        }
        return false;
    }
}
