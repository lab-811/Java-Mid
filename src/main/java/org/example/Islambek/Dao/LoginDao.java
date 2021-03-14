package org.example.Islambek.Dao;

import org.example.Islambek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User checkForUser(String name, String password){
        return  jdbcTemplate.query("SELECT * FROM Users WHERE name=? and password=?", new Object[]{name, password},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public User checkForAdmin(String name, String password){
        return jdbcTemplate.query("SELECT * FROM Admin WHERE name=? and password=?", new Object[]{name, password},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);

    }

    public void registerUser(String name, String password){ jdbcTemplate.update("INSERT INTO Users( name, password)  VALUES (?,?)", name, password); }
}
