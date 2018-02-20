package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserStatus;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes("user")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/logout")
    public String logout(@SessionAttribute("user")User user,ModelMap map) {
        User one = userRepository.getOne(user.getId());
        one.setUserStatus(UserStatus.OFFLINE);
        userRepository.save(one);
        map.addAttribute("user",new User());
        return "redirect:/";

    }

    @GetMapping(value = "/getResource")
    public void getResource(HttpServletResponse response, @RequestParam("filename") String filename) throws IOException {
        try (InputStream inputStream = new FileInputStream("/home/intern/Desktop/nk@r/" + filename)) {
            response.setContentType(MediaType.ALL_VALUE);
            IOUtils.copy(inputStream, response.getOutputStream());
        }

    }


    @GetMapping(value = "/searchUser")
    public String search(@RequestParam("userNameForSearch") String searchName, ModelMap map, @SessionAttribute("user")User user) {

        List<User> customFindUsersbyNameOrSurname = new LinkedList<>();
        String[] nameStrArr = searchName.split(" ");
        if (user.getUserType().equals(UserType.USER)) {
            if (nameStrArr.length == 1) {
                List<User> users = userRepository.customFindUsersbyNameOrSurname(nameStrArr[0], " ");
                for (User user1 : users) {
                    if (user1.getActiveStatus().equals(ActiveStatus.ACTIVE)) {
                        customFindUsersbyNameOrSurname.add(user1);
                    }
                }
            } else if (nameStrArr.length == 2) {
                List<User> users = userRepository.customFindUsersbyNameOrSurname(nameStrArr[0], nameStrArr[1]);

                for (User user1 : users) {
                    if (user1.getActiveStatus().equals(ActiveStatus.ACTIVE)) {
                        customFindUsersbyNameOrSurname.add(user1);
                    }
                }
            }
        } else {
            if (nameStrArr.length == 1) {
                customFindUsersbyNameOrSurname = userRepository.customFindUsersbyNameOrSurname(nameStrArr[0], " l");
            } else if (nameStrArr.length == 2) {
                customFindUsersbyNameOrSurname = userRepository.customFindUsersbyNameOrSurname(nameStrArr[0], nameStrArr[1]);
            }
        }

        map.addAttribute("search", customFindUsersbyNameOrSurname);
        return "searchUsers";

    }

}
