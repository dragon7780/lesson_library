package org.example.repository;

import org.example.dto.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public void save(Student student){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        factory.close();
        session.close();
    }
    public Student getStudentById(Integer id)  {
        String sql="select*from student where id="+id;
        Student update = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Student.class));
        return update;
    }
    public List<Student> getStudentByPhone(String id)  {
        String sql="select*from student where phone='%s'";
        String.format(sql, id);
        List<Student> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
        return query;
    }
    public List<Student> getAll(){
        String sql="select*from student";
        List<Student> update = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Student.class));
        return update;
    }
    public int deleteStudentById(int id){
        String sql="update student set variable=false where id="+id;
        int update = jdbcTemplate.update(sql, Student.class);
        return update;
    }

}
