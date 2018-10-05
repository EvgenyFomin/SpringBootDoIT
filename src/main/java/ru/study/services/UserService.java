package ru.study.services;

import ru.study.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUserByEmail(String email);

    void save(User user);
}
