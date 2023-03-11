package org.example.db;

import org.example.dto.Student;
import org.example.enums.StudentRole;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@Repository
public class Database  {
    @Autowired
    private StudentRepository studentRepository;
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Atto", "postgres", "azizbek");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
    public void initTable() {
        String student = "create table if not exists student ( " +
                "             id serial primary key,\n" +
                "             name varchar(20) not null,\n" +
                "             surname varchar(20) not null,\n" +
                "             phone varchar(12),\n" +
                "             role varchar,\n"+
                "             createdDate timestamp not null default now(),\n" +
                "             status varchar(20) not null, \n" +
                "             visible boolean default true\n" + ");";

        String book = "create table if not exists book(" +
                "   id serial primary key," +
                "   title varchar," +
                "   author varchar not null," +
                "   amount numeric not null," +
                "   visible boolean default true ," +
                "   publisherYear timestamp  default now()" + ");";

        String studentBook = "create table if not exists studentBook( " +
                "id serial primary key, " +
                "student_id integer , " +
                "book_id integer , " +
                "createdDate timestamp default now()," +
                "returnedDate timestamp,"+
                "duration integer , " +
                " foreign key(student_id) references  student(id), " +
                " foreign key(book_id) references  book(id)"+") ;";
        execute(student);
        execute(book);
        execute(studentBook);
    }
    public void adminInit(){
        Student student=new Student();
        student.setName("azizbek");
        student.setSurname("aaa");
        student.setPhone("123");
        student.setRole(StudentRole.ADMIN);
        student.setVisible(true);
        student.setId(777);
        Student phone = studentRepository.getStudentByPhone(student.getPhone());
        if(phone == null){
            studentRepository.save(student);
        }
    }

    private void execute(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
