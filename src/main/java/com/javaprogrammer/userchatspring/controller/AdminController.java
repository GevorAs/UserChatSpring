package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CommentRepository commentRepository;


    @GetMapping("/admin")
    public String adminPage(ModelMap map, @SessionAttribute("user")User user) {

        String newMessage = "";
               if (messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW) != 0) {
            newMessage = "" + messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW);
        }
        map.addAttribute("newMessage", newMessage);
        return "admin";
    }

    @GetMapping("/activateUser")
    public String activateUserButton(@RequestParam("deletedUserId") int id) {
        User one = userRepository.getOne(id);
        one.setActiveStatus(ActiveStatus.ACTIVE);
        userRepository.save(one);
        return "redirect:/admin";
    }

    @GetMapping("/getDeletedUser")
    public String getDeletedUser(ModelMap map) {
        List<User> allByActiveStatusDeleted = userRepository.findAllByActiveStatus(ActiveStatus.DELETED);


        map.addAttribute("deleted", allByActiveStatusDeleted);


        return "adminDeletedAjax";
    }

    @GetMapping("/blockUser")
    public HttpServletResponse blockUser(HttpServletResponse response,@RequestParam("userId")int userId){
         User user = userRepository.getOne(userId);
        user.setActiveStatus(ActiveStatus.DELETED);
        user.setUserStatus(UserStatus.OFFLINE);
        userRepository.save(user);
               return response;
    }

    @GetMapping("/removeUser")
    public String removeUser(@RequestParam("userId") int userId,HttpServletResponse response){

        userRepository.delete(userId);
        return "redirect:/admin";
    }
}
