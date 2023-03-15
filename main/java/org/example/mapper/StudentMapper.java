package org.example.mapper;


import org.example.dto.Student;
import org.example.enums.StudentRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student=new Student();
        student.setName(rs.getString("name"));
        student.setSurname(rs.getString("surname"));
        student.setRole(rs.getString("role"));
        student.setPhone(rs.getString("phone"));
        student.setId(rs.getInt("id"));
        student.setVisible(rs.getBoolean("visible"));
        student.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime().toLocalDate());
        return student;
    }
}
