package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.MessageStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserType;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private FriendRepository friendRepository;
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

    //-----chi ashxati----
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
        userRepository.save(user);
        return response;
    }

    @GetMapping("/removeUser")
    public String removeUser(@RequestParam("userId") int userId,HttpServletResponse response){

        userRepository.delete(userId);
        return "redirect:/admin";
    }
}
