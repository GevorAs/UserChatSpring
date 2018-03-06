package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.Security.CurrentUser;
import com.javaprogrammer.userchatspring.mail.EmailServiceImpl;
import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserStatus;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes("user")
public class LoginRegisterController {
    @Autowired
    private UserRepository userRepository;

    @Value("${pic.path}")
    private String nkar;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailServiceImpl emailService;


    @GetMapping("/loginSuccess")
    public String loginSucces(ModelMap map) {
        CurrentUser principal = (CurrentUser)
                SecurityContextHolder.
                        getContext().
                        getAuthentication()
                        .getPrincipal();

        if (principal.getUser().getUserType() == UserType.ADMIN) {
            map.addAttribute("user", principal.getUser());

            return "redirect:/admin";

        } else if (principal.getUser().getUserType() == UserType.USER) {
            map.addAttribute("user", principal.getUser());
            return "redirect:/userPage";

        }
        return "redirect:/";

    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRegister") User user, BindingResult result, @RequestParam("pic") MultipartFile multipartFile, ModelMap map) throws IOException {
        StringBuilder sb = new StringBuilder();
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

        if (result.hasErrors()) {
            for (ObjectError objectError : result.getAllErrors()) {
                sb.append(objectError.getDefaultMessage() + "<br>");
            }
            return "redirect:/?message=" + sb.toString();
        } else if (userRepository.existsByEmail(user.getEmail())) {
            sb.append("this email already exist<br>");

            return "redirect:/?message=" + sb.toString();
        } else
//            if (!user.getPassword().matches(pattern))
            if (false) {
                String passwordValidStr;
                passwordValidStr = "Password will be<br>  -a digit must occur at least once<br>" +
                        "  - a lower case letter must occur at least once<br>" +
                        "  - an upper case letter must occur at least once<br>" +
                        "  - a special character must occur at least once<br>" +
                        "  - no whitespace allowed in the entire string<br>" +
                        "  - anything, at least eight places though<br>";
                sb.append(passwordValidStr);
                return "redirect:/?message=" + sb.toString();
            }
        if (multipartFile.getOriginalFilename().endsWith(".jpg")) {
            String picname = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(nkar + picname);
            multipartFile.transferTo(file);
            user.setPicture(picname);
        }
        user.setUserStatus(UserStatus.ONLINE);
        user.setActiveStatus(ActiveStatus.ACTIVE);
        user.setUserType(UserType.USER);
        user.setVerify(false);
        userRepository.save(user);
        emailService.sendSimpleMessage(user.getEmail(), "verification",
                String.format("http://localhost:8080/verification?email=%s", user.getEmail()));
        user.setPassword(null);
        map.addAttribute("user", user);
        return "redirect:/";

    }

    @GetMapping("/verification")
    public String getVerification(@RequestParam("email") String email,ModelMap modelMap) {
        User userByEmail = userRepository.findUserByEmail(email);
        userByEmail.setVerify(true);
        String pass=userByEmail.getPassword();
        userByEmail.setPassword(passwordEncoder.encode(userByEmail.getPassword()));
        userRepository.save(userByEmail);
        modelMap.addAttribute("vEmail",userByEmail.getEmail());
        modelMap.addAttribute("userRegister", new User());
//
        modelMap.addAttribute("vPassword",pass);
        return "index";
    }


}
