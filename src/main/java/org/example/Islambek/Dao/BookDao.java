package org.example.Islambek.Dao;


import org.example.Islambek.model.Book;
import org.example.Islambek.model.Issued;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAll(){
        return jdbcTemplate.query("SELECT * FROM Books ORDER BY id", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showOne(Long id){
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public List<Issued> selectAll(Long id){
        return jdbcTemplate.query("SELECT * FROM IssuedBooks WHERE user_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Issued.class));
    }

    public void issueBook(Long userId, Long bookId){
        jdbcTemplate.update("INSERT INTO IssuedBooks(book_id, user_id) VALUES (?,?)", bookId, userId);
    }

    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO Books(title, author) VALUES (?,?)", book.getTitle(), book.getAuthor());
    }

    public void updateBook(Long id, Book book){
        jdbcTemplate.update("UPDATE Books SET title=?, author=? WHERE  id=?", book.getTitle(), book.getAuthor(), id);
    }

    public void deleteBook(Long id){
        jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }




}
