package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.Friend;
import com.javaprogrammer.userchatspring.model.Message;
import com.javaprogrammer.userchatspring.model.MessageStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller

public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private FriendRepository friendRepository;


    @GetMapping("/messages")
    public String messagesPage(ModelMap map, HttpSession session, @RequestParam(value = "friendIdStr", required = false) String friendIdStr) {
        User user = (User) session.getAttribute("user");
        List<Friend> friends = friendRepository.serchAllFriends(user.getId());
        List<User> userFriends = new LinkedList<>();

        for (Friend friend : friends) {
            if (friend.getFriendId() == user.getId()) {
                User one = userRepository.getOne(friend.getUserId());
                userFriends.add(one);
            } else {
                User one = userRepository.getOne(friend.getFriendId());
                userFriends.add(one);
            }

        }

        map.addAttribute("friendIdStr", friendIdStr != null ? friendIdStr : " ");


        map.addAttribute("userFriends", userFriends);
        return "message";
    }

    @GetMapping("/getMessages")
    public String getMessages(@RequestParam("id") String idStr, ModelMap map, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int friendId = Integer.parseInt(idStr);
        List<Message> chat = messageRepository.customGetMessagesByUserAndFriend(user.getId(), friendId);
        for (Message message : chat) {
            if ((message.getToId() == user.getId()) && message.getMessageStatus().equals(MessageStatus.NEW)) {
                message.setMessageStatus(MessageStatus.OLD);
                messageRepository.save(message);
            }
        }
        map.addAttribute("chat", chat);
        map.addAttribute("friend", userRepository.getOne(friendId));
        map.addAttribute("newUser", user);
        return "messageAjax";
    }


    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("friendId") String friendIdStr, @RequestParam("text") String text, ModelMap map, HttpSession session) {
        int friendId = Integer.parseInt(friendIdStr);
        User user = (User) session.getAttribute("user");
        Message message = new Message();
        message.setText(text);
        message.setFromId(user.getId());
        message.setToId(friendId);
        message.setMessageStatus(MessageStatus.NEW);
        messageRepository.save(message);
        map.addAttribute("friendIdStr", friendIdStr);
        return "redirect:/messages";
    }
}
