package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserStatus;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
    @GetMapping(value = "/getResource")
   public void getResource(HttpServletResponse response, @RequestParam("filename")String filename) throws IOException {
       try(InputStream inputStream = new FileInputStream("D:\\ADMIN\\picStringDemo\\"+filename)) {
         response.setContentType(MediaType.ALL_VALUE);
           IOUtils.copy(inputStream,response.getOutputStream());

       }

    }

}
