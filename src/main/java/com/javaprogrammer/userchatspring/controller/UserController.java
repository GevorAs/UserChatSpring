package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public String login(@RequestParam("email") String email, @RequestParam("password")String password, HttpSession session) {
        
        User user=userRepository.getByEmailAndPassword(email,password);
        if (user!=null){
            session.setAttribute("user",user);
             if (user.getUserType().equals(UserType.ADMIN)){
                return "redirect:/admin";
            }else if (user.getUserType().equals(UserType.MODERATOR)){
                return "redirect:/moderator";
            } else return "redirect:/user";
        }
        return "index";


    }
    @GetMapping("/loginPage")
    public String loginPage(){
        return "index";
    }
}
