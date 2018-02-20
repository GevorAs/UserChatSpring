package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes({"user", "newMessage", "newRequest", "friendIdForMessage", "friend"})

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
    public String loginPageControler(ModelMap map, @SessionAttribute("user") User user) {
        String newRequest = "";
        String newMessage = "";
        if (requestRepository.countByToId(user.getId()) != 0) {
            newRequest = "" + requestRepository.countByToId(user.getId());

        }
        if (messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW) != 0) {
            newMessage = "" + messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW);
        }
        map.addAttribute("posts", postRepository.findAllByUserId(user.getId()));
        map.addAttribute("newMessage", newMessage);
        map.addAttribute("newRequest", newRequest);
        map.addAttribute("friendIdForMessage", new User());


        return "userPage";
    }

    @GetMapping("/requests")
    public String requests(ModelMap map, @SessionAttribute("user") User user) {
        List<Request> allByToId = requestRepository.findAllByToId(user.getId());
        List<User> friendRequests = new ArrayList<>();
        for (Request request : allByToId) {

            friendRequests.add(userRepository.getOne(request.getFromId()));
        }
        map.addAttribute("friendRequests", friendRequests);
        return "request";
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
        map.addAttribute("friend", userRepository.getOne(id));
        return "user";


    }

    @GetMapping("/sendRequest")
    public HttpServletResponse sendRequest(HttpServletResponse response, @SessionAttribute("friend") User friend, @SessionAttribute("user") User user) {

        Request request = new Request();
        request.setToId(friend.getId());
        request.setFromId(user.getId());
        Request request1 = requestRepository.findAllByFromIdAndToId(user.getId(), friend.getId());
        Request request2 = requestRepository.findAllByToIdAndFromId(user.getId(), friend.getId());
        if (request1 == null && request2 == null) {
            requestRepository.save(request);
        }
        return response;
    }


}
