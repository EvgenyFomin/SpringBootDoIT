package ru.study.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.study.dao.interfaces.UserDAO;
import ru.study.model.User;

@Component
public class UserValidator implements Validator {
    @Autowired
    @Qualifier("hibernateUserDAO")
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userDAO.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already used");
        }
    }
}
