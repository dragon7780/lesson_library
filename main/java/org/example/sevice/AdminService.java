package org.example.sevice;

import org.example.dto.Book;
import org.example.dto.Student;
import org.example.enums.StudentRole;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AdminService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;
    public boolean addBook(String title, String author, Integer amount){
        Book book = bookRepository.getBookByTitle(title, author);
        if(book == null){
            Book book1=new Book();
            book1.setTitle(title);
            book1.setAuthor(author);
            book1.setAmount(amount);
            book1.setPublisherYear(LocalDate.now());
            book1.setVisible(true);
            Integer save = bookRepository.save(book1);
            if (save==1){
                return true;
            }else {
                return false;
            }
        }else {
            Boolean byTitle = bookRepository.changeBookByTitle(title, author, amount);
            if(byTitle){
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean deleteBook(int id) {
        Book bookById = bookRepository.getBookById(id);
        if(bookById == null){
            return false;
        }else {
            boolean delete= bookRepository.deleteBook(id);
            if (!delete){
                return false;
            }else {
                return true;
            }
        }
    }

    public boolean addStudent(String name, String surname, String phone) {
        Student studentByPhone = studentRepository.getStudentByPhone(phone);
        if (studentByPhone != null){
            return false;
        }
        Student student=new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setRole(StudentRole.USER);
        student.setCreatedDate(LocalDate.now());
        student.setVisible(true);
        studentRepository.save(student);

        return true;
    }
}
