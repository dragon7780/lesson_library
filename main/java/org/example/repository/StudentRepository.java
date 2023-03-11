package org.example.repository;

import org.example.db.Database;
import org.example.dto.Student;
import org.example.enums.StudentRole;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
@Repository
public class StudentRepository {
    public int save(Student student){
        Connection connection = Database.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql=String.format("insert into student (name,surname,role,phone,createdDate,visible)" +
                "values('%s','%s','%s','%s','%s','%s');",
                student.getName(),student.getSurname(),student.getRole(),student.getCreatedDate(),student.getVisible());
            int update = statement.executeUpdate(sql);
            connection.close();
            if(update==1){
                return 1;
            }else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Student getStudentByPhone(String name)  {
        Connection connection = Database.getConnection();
        Student student;
        try {
            student =new Student();
            PreparedStatement statement = connection.prepareStatement("select*from student where phone=? and visible=true");
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                student.setName(resultSet.getString("name"));
                student.setId(resultSet.getInt("id"));
                student.setRole(StudentRole.valueOf(resultSet.getString("role")));
                student.setPhone(resultSet.getString("phone"));
                student.setCreatedDate(LocalDate.from(resultSet.getTimestamp("createdDate").toLocalDateTime()));
                student.setVisible(resultSet.getBoolean("visible"));
                return student;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
    public List<Student> getAll(){
        Connection connection = Database.getConnection();
        List<Student> studentList=new LinkedList<>();
        String sql=String.format("select from student");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Student student=new Student();
            while(resultSet.next()) {
                student.setName(resultSet.getString("name"));
                student.setId(resultSet.getInt("id"));
                student.setRole(StudentRole.valueOf(resultSet.getString("role")));
                student.setPhone(resultSet.getString("phone"));
                student.setCreatedDate(LocalDate.from(resultSet.getTimestamp("createdDate").toLocalDateTime()));
                student.setVisible(resultSet.getBoolean("visible"));
                studentList.add(student);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

}
