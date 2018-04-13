package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserStatus;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    @Autowired
    PasswordEncoder passwordEncoder;


    @Value("${pic.path}")
    private String nkar;
    @Value("${file.path}")
    private String filePath;
    @Value("${user.pictures}")
    private String userPicPath;



    @GetMapping("/")
    public String loginPage(ModelMap map, @RequestParam(value = "message", required = false) String message) {


        map.addAttribute("userRegister", new User());
        map.addAttribute("message", message != null ? message : "");
        return "index";

    }

    @GetMapping(value = "/logout")
    public String logout(@SessionAttribute("user")User user,ModelMap map) {
        User one = userRepository.getOne(user.getId());
        one.setUserStatus(UserStatus.OFFLINE);
        userRepository.save(one);
        map.addAttribute("user",new User());
        return "redirect:/";

    }

    @GetMapping(value = "/getPic")
    public void getPic(HttpServletResponse response, @RequestParam("filename") String filename) {
        try (InputStream inputStream = new FileInputStream(nkar + filename)) {
            response.setContentType(MediaType.ALL_VALUE);
            IOUtils.copy(inputStream, response.getOutputStream());
        }catch (IOException ignored){

        }

    }

    @GetMapping(value = "/getUserPic")
    public void getUserPic(HttpServletResponse response, @RequestParam("filename") String filename) {
        try (InputStream inputStream = new FileInputStream(userPicPath + filename)) {
            response.setContentType(MediaType.ALL_VALUE);
            IOUtils.copy(inputStream, response.getOutputStream());
        }catch (IOException ignored){

        }

    }

    @GetMapping(value = "/getFile")
    public void getFile(HttpServletResponse response, @RequestParam("filename") String filename) {
        try (InputStream inputStream = new FileInputStream(filePath + filename)) {
            response.setContentType(MediaType.ALL_VALUE);
            IOUtils.copy(inputStream, response.getOutputStream());
        }catch (IOException ignored){

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
                    if (user1.getActiveStatus().equals(ActiveStatus.ACTIVE) && user1.getUserType() != UserType.ADMIN) {
                        if (!user1 .equals(user)) {
                            customFindUsersbyNameOrSurname.add(user1);
                        }
                    }
                }
            } else if (nameStrArr.length == 2) {
                List<User> users = userRepository.customFindUsersbyNameOrSurname(nameStrArr[0], nameStrArr[1]);

                for (User user1 : users) {
                    if (user1.getActiveStatus().equals(ActiveStatus.ACTIVE)) {
                        if (!user1.equals(user)) {
                            customFindUsersbyNameOrSurname.add(user1);
                        }
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

        if (user.getUserType() == UserType.ADMIN) {
            return "searchForAdmin";
        }
        return "searchUsers";

    }

}
