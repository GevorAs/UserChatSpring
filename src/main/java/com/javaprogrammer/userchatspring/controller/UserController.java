package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.MessageStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    CommentRepository commentRepository;


    @GetMapping(value = "/userPage")
    public String loginPageControler(ModelMap map, HttpSession session) {
        User user = (User) session.getAttribute("user");

        StringBuilder stringBuilder=new StringBuilder();
        if (!requestRepository.findAllByToId(user.getId()).isEmpty()){
            String info="New Request<br>";
            stringBuilder.append(info);
        }
        if (!messageRepository.findAllByToIdAndMessageStatus(user.getId(), MessageStatus.NEW).isEmpty()) {
            String info = "New Message<br>";
            stringBuilder.append(info);
        }
        map.addAttribute("posts", postRepository.findAllByUserId(user.getId()));

        map.addAttribute("info", stringBuilder.toString());

        return "userPage";

    }

    @GetMapping("/friends")
    public String friends(ModelMap map) {

        return null;
    }

}
