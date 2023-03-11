package org.example.repository;

import org.example.db.Database;
import org.example.dto.Book;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class BookRepository {
    public Integer save(Book book){
        Connection connection = Database.getConnection();
        String sql=String.format("inset into book(title,author,amount,publisherYear,visible)"+
                "values('%s','%s','%s','%s','%s')"+
                book.getTitle(),book.getAuthor(),book.getAmount(),book.getPublisherYear(),book.getVisible());
        try {
            Statement statement = connection.createStatement();
            int update = statement.executeUpdate(sql);
            connection.close();
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Book> getAll(){
        Connection connection=Database.getConnection();
        List<Book> bookList=new ArrayList<>();
        String sql=String.format("select*from book where visible=true");
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Book book=new Book();
            while (resultSet.next()){
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setAmount(resultSet.getInt("amount"));
                book.setPublisherYear(resultSet.getTimestamp("createdDate").toLocalDateTime().toLocalDate());
                book.setVisible(resultSet.getBoolean("visible"));
                bookList.add(book);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }
    public Book getBookByTitle(String title,String author){
        Connection connection=Database.getConnection();
        Book book;
        String sql=String.format("select*from book where title=? and author=? and visible=true");
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,author);
            ResultSet resultSet = statement.executeQuery();
            book=new Book();
            while (resultSet.next()){
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setAmount(resultSet.getInt("amount"));
                book.setPublisherYear(resultSet.getTimestamp("createdDate").toLocalDateTime().toLocalDate());
                book.setVisible(resultSet.getBoolean("visible"));
                return book;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }
    public Book getBookById(Integer id){
        Connection connection=Database.getConnection();
        Book book;
        String sql=String.format("select*from book where id=? and visible=true");
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            book=new Book();
            while (resultSet.next()){
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setAmount(resultSet.getInt("amount"));
                book.setPublisherYear(resultSet.getTimestamp("createdDate").toLocalDateTime().toLocalDate());
                book.setVisible(resultSet.getBoolean("visible"));
                return book;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }
    public Boolean changeBookByTitle(String title,String author,Integer amount){
        Connection connection=Database.getConnection();
        Book book;
        String sql=String.format("update book set amount=amount+? where title=? and author=? and visible=true");
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,amount);
            statement.setString(2,title);
            statement.setString(3,author);
            int resultSet = statement.executeUpdate();
            connection.close();
            if(resultSet == 1){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteBook(int id) {
        Connection connection=Database.getConnection();
        Book book;
        String sql=String.format("delete from book where id=? and visible=true");
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            int resultSet = statement.executeUpdate();
            connection.close();
            if(resultSet == 1){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
