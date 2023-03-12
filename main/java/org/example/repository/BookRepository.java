package org.example.repository;

import org.example.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Integer save(Book book){
        String sql=String.format("inset into book(title,author,amount,publisherYear,visible)values('%s','%s','%s','%s','%s')");
           sql=String.format(sql,book.getTitle(),book.getAuthor(),book.getAmount(),book.getPublisherYear(),book.getVisible());
        int update = jdbcTemplate.update(sql);
        return update;
    }
    public List<Book> getAll(){
        String sql=String.format("select*from book where visible=true");
        List<Book> bookList = jdbcTemplate.queryForList(sql, Book.class);
        return bookList;
    }
    public Book getBookByTitle(String title,String author){
        String sql=String.format("select*from book where title='%s' and author='%s' and visible=true",title,author);
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class));
        return book;
    }
    public Book getBookById(Integer id){
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
}
