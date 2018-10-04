package ru.study.dao.interfaces;

import ru.study.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    void save(User user);

    User findUserByEmail(String email);
}
