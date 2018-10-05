package ru.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.study.dao.interfaces.UserDAO;
import ru.study.model.User;
import ru.study.validators.UserValidator;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class MainController {

    @Autowired
    @Qualifier("hibernateUserDAO")
    private UserDAO userDAO;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/view")
    public String view(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("name", Objects.requireNonNullElse(name, "stranger"));
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw() {
        return "SomeData";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userDAO.findAll());
        return "users";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }

    @GetMapping("/users/new")
    public String getSignup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/users/new")
    public String newUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userDAO.save(user);
        return "redirect:/users";
    }
}
