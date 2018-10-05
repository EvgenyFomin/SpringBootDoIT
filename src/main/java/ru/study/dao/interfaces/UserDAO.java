package ru.study.dao.interfaces;

import ru.study.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

    User findUserByEmail(String email);

    void save(User user);
}
