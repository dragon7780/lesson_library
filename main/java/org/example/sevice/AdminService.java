package org.example.sevice;

import org.example.dto.Book;
import org.example.dto.Student;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;
    public boolean addBook(String title, String author, Integer amount){
        List<Book> bookByTitle = bookRepository.getBookByTitle(title, author);
        if(bookByTitle.size()==0){
            Book book1=new Book();
            book1.setTitle(title);
            book1.setAuthor(author);
            book1.setAmount(amount);
            book1.setPublisherYear(LocalDate.now());
            book1.setVisible(true);
            bookRepository.save(book1);
            return true;
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
        List<Book> bookById = bookRepository.getBookById(id);
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
    public boolean deleteStudent(int id){
        Student studentById = studentRepository.getStudentById(id);
        if (studentById == null){
            return false;
        }else {
            int byId = studentRepository.deleteStudentById(id);
            if(byId == 0){
                return false;
            }else {
                return true;
            }
        }
    }

    public boolean addStudent(String name, String surname, String phone) {
        List<Student> studentByPhone = studentRepository.getStudentByPhone(phone);
        if (studentByPhone.size() != 0){
            return false;
        }
        Student student=new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setRole("USER");
        student.setCreatedDate(LocalDate.now());
        student.setPhone(phone);
        student.setVisible(true);
        studentRepository.save(student);
        return true;
    }
}
