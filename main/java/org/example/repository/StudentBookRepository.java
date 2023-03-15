package org.example.repository;

import org.example.dto.Book;
import org.example.dto.StudentBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentBookRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void save(StudentBook studentBook){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(studentBook);
        transaction.commit();
        factory.close();
        session.close();
    }
    public List<StudentBook> getAll(){
        String sql=String.format("select*from studentBook");
        List<StudentBook> bookList = jdbcTemplate.queryForList(sql, StudentBook.class);
        return bookList;
    }
}
