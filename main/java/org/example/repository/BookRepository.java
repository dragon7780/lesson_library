package org.example.repository;

import org.example.dto.Book;
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
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void save(Book book){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        factory.close();
        session.close();
    }
    public List<Book> getAll(){
        String sql=String.format("select*from book where visible=true");
        List<Book> bookList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    }
    public List<Book> getBookByTitle(String title,String author){
        String sql=String.format("select*from book where title='%s' and author='%s' and visible=true",title,author);
        List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return query;
    }
    public List<Book> getBookById(Integer id){
        String sql=String.format("select*from book where id="+id+" and visible=true");
        List<Book> book = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));

        return book;
    }
    public Book getBookById1(Integer id){
        String sql=String.format("select*from book where id="+id+" and visible=true");
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class));
        return book;
    }
    public Boolean changeBookByTitle(String title,String author,Integer amount){
        String sql=String.format("update book set amount=amount+? where title=? and author=? and visible=true");
        sql= String.format(sql,title,author,amount);
        int update = jdbcTemplate.update(sql);
        if (update==1){
            return true;
        }else {
            return false;
        }
    }
    public boolean deleteBook(int id) {
        String sql=String.format("delete from book where id="+id+" and visible=true");
        int update = jdbcTemplate.update(sql);
        if (update==1){
            return true;
        }else {
            return false;
        }
    }

    public void changeAmountById(int id) {
        Book book = getBookById1(id);
        String sql="update book set amount=amount-1 where id="+id;
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(sql,book);
        transaction.commit();
        factory.close();
        session.close();
    }
}
