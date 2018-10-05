package ru.study.services;

import org.springframework.stereotype.Component;
import ru.study.model.User;

import java.util.List;

@Component
public interface UserService {
    List<User> findAll();

    User findUserByEmail(String email);

    void save(User user);
}
