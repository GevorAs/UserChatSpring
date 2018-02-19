package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

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
        String newRequest=null;
        String newMessage = null;
        if (requestRepository.countByToId(user.getId()) != 0) {
            newRequest = "" + requestRepository.countByToId(user.getId());

        }
        if (messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW) != 0) {
            newMessage = "" + messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW);
        }
        map.addAttribute("posts", postRepository.findAllByUserId(user.getId()));
        map.addAttribute("newMessage", newMessage);
        map.addAttribute("newRequest", newRequest);

        return "userPage";
    }

    @GetMapping("/friends")
    public String friends(ModelMap map) {
        return null;
    }

    @GetMapping(value = "/user")
    public String goToOtherUserPage(@RequestParam("otherUserId") int id, ModelMap map) {
        List<Post> allPostOtherUser = postRepository.findAllByUserId(id);

        List<Friend> friends = friendRepository.serchAllFriends(id);
        List<User> otherUsersFriends = new LinkedList<>();
        for (Friend friend : friends) {
            if (friend.getFriendId() == id) {
                User one = userRepository.getOne(friend.getUserId());
                otherUsersFriends.add(one);
            } else {
                User one = userRepository.getOne(friend.getFriendId());
                otherUsersFriends.add(one);
            }

        }
        map.addAttribute("otherUser", userRepository.getOne(id));
        map.addAttribute("allPostOtherUser", allPostOtherUser);
        map.addAttribute("otherUsersFriends", otherUsersFriends);
        return "user";


    }


}
