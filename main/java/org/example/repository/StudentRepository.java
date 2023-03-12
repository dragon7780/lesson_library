package org.example.repository;

import org.example.db.Database;
import org.example.dto.Student;
import org.example.enums.StudentRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int save(Student student){
        String sql="insert into student (id,name,surname,role,phone,createdDate,visible) values('%s','%s','%s','%s','%s',now(),true)";
        sql=String.format(sql,student.getId(),student.getName(),student.getSurname(),student.getRole(),student.getPhone(),student.getCreatedDate());
        int update = jdbcTemplate.update(sql);
        return update;
    }
    public Student getStudentById(Integer id)  {
        String sql="select*from student where id="+id;
        Student update = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Student.class));
        return update;
    }
    public Student getStudentByPhone(String id)  {
        String sql="select*from student where phone="+id;
        Student update = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Student.class));
        return update;
    }
    public List<Student> getAll(){
        String sql="select*from student";
        List<Student> update = (List<Student>) jdbcTemplate.queryForObject(sql,Student.class);
        return update;
    }

}
