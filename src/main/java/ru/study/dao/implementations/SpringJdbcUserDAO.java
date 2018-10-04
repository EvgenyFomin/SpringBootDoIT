package ru.study.dao.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.study.dao.interfaces.UserDAO;
import ru.study.model.User;
import ru.study.validators.UserValidator;

import java.util.List;

@Component
public class SpringJdbcUserDAO implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserValidator userValidator;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", (resultSet, i) -> {
            User user = new User();
            user.setName(resultSet.getString(1));
            user.setSurname(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            return user;
        });
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into users values(?, ?, ?)",
                user.getName(), user.getSurname(), user.getEmail());
    }

    @Override
    public User findUserByEmail(String email) {
        return jdbcTemplate.query("select * from users where email = ?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }
}
