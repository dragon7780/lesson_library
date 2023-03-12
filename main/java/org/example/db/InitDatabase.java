package org.example.db;

import org.example.dto.Student;
import org.example.enums.StudentRole;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitDatabase {
    @Autowired
    private StudentRepository studentRepository;
//    public void adminInit(){
//        Student student=new Student();
//        student.setName("azizbek");
//        student.setSurname("aaa");
//        student.setPhone("123");
//        student.setRole(StudentRole.ADMIN);
//        student.setVisible(true);
//        student.setId(777);
//        StudentRepository studentRepository1=new StudentRepository();
//        Student phone = studentRepository1.getStudentByPhone(student.getPhone());
//        if(phone!=null){
//            studentRepository.save(student);
//        }
//    }
}
