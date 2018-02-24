package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String adminPage() {

        return "admin";
    }

    @GetMapping("/activateUser")
    public String activateUserButton(@RequestParam("deletedUserId") int id) {
        User one = userRepository.getOne(id);
        one.setActiveStatus(ActiveStatus.ACTIVE);
        userRepository.save(one);
        return "redirect:/admin";
    }

    //-----chi ashxati----
    @GetMapping("/getDeletedUser")
    public String getDeletedUser(ModelMap map) {
        List<User> allByActiveStatusDeleted = userRepository.findAllByActiveStatus(ActiveStatus.DELETED);


        map.addAttribute("deleted", allByActiveStatusDeleted);


        return "adminDeletedAjax";
    }
}
