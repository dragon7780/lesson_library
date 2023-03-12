package org.example.sevice;

import org.example.dto.Student;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private StudentRepository studentRepository;
    public boolean LogIn(Integer id){
        Student studentById = studentRepository.getStudentById(id);
        if (studentById != null){
            return true;
        }
        return false;
    }
}
