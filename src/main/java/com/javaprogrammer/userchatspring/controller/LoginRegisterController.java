package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserStatus;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class LoginRegisterController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public String login(@RequestParam("emailLogin") String email, @RequestParam("passwordLogin") String password, HttpSession session) {
        if (email.equalsIgnoreCase("admin@admin.com") && userRepository.existsByEmail("admin@admin.com")) {
            User user = new User();
            user.setName("Admin");
            user.setSurname("Admin");
            user.setEmail("admin@admin.com");
            user.setPassword("admin");
            user.setUserType(UserType.ADMIN);
            user.setActiveStatus(ActiveStatus.DELETED);
            user.setUserStatus(UserStatus.OFFLINE);
            userRepository.save(user);
        }
        User user = userRepository.getByEmailAndPassword(email, password);
        user.setPassword(null);
        if (user != null) {
            session.setAttribute("user", user);
            if (user.getUserType().equals(UserType.ADMIN)) {
                return "redirect:/admin";
            } else if (user.getUserType().equals(UserType.MODERATOR) && !user.getActiveStatus().equals(ActiveStatus.DELETED)) {
                return "redirect:/moderator";
            } else if (!user.getActiveStatus().equals(ActiveStatus.DELETED)) {
                return "redirect:/userPage";
            }
        }
        return "redirect:/loginPage?message=" + "please input valid login or password";


    }

    @GetMapping("/loginPage")
    public String loginPage(ModelMap map, @RequestParam(value = "message", required = false) String message) {

        map.addAttribute("userRegister", new User());
        map.addAttribute("message", message != null ? message : "");
        return "index";

    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRegister") User user, BindingResult result, @RequestParam("pic") MultipartFile multipartFile,HttpSession session) throws IOException {
        StringBuilder sb = new StringBuilder();
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

        if (result.hasErrors()) {
            for (ObjectError objectError : result.getAllErrors()) {
                sb.append(objectError.getDefaultMessage() + "<br>");
            }
            return "redirect:/loginPage?message=" + sb.toString();
        } else if (userRepository.existsByEmail(user.getEmail())) {
            sb.append("this email already exist<br>");

            return "redirect:/loginPage?message=" + sb.toString();
        }
        else     if (!user.getPassword().matches(pattern)) {
            String passwordValidStr;
            passwordValidStr="Password will be<br> \" a digit must occur at least once<br>" +
                    "  - a lower case letter must occur at least once<br>" +
                    "  - an upper case letter must occur at least once<br>" +
                    "  - a special character must occur at least once<br>" +
                    "  - no whitespace allowed in the entire string<br>" +
                    "  - anything, at least eight places though<br>" ;
            sb.append(passwordValidStr);
            return "redirect:/loginPage?message=" + sb.toString();
        }
        String picname = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File("D:\\ADMIN\\picStringDemo\\" + picname);
        multipartFile.transferTo(file);
        user.setUserStatus(UserStatus.ONLINE);
        user.setActiveStatus(ActiveStatus.ACTIVE);
        user.setUserType(UserType.USER);
        user.setPicture(picname);
        userRepository.save(user);
        user.setPassword(null);
        session.setAttribute("user",user);
        return "redirect:/userPage";

    }

}