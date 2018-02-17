package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserStatus;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        user.setUserStatus(UserStatus.OFFLINE);
        userRepository.save(user);
        session.invalidate();
        return "redirect:/loginPage";

    }


}
