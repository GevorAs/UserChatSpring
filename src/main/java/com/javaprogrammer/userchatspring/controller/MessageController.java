package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.Friend;
import com.javaprogrammer.userchatspring.model.Message;
import com.javaprogrammer.userchatspring.model.MessageStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.repository.FriendRepository;
import com.javaprogrammer.userchatspring.repository.MessageRepository;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes("friendIdForMessage")
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private FriendRepository friendRepository;


    @GetMapping("/messages")
    public String messagesPage(ModelMap map, @SessionAttribute("user") User user) {
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
        map.addAttribute("emptyMessage", new Message());
        map.addAttribute("userFriends", userFriends);
        return "message";
    }

    @GetMapping("/getMessages")
    public String getMessages(@RequestParam("id") String idStr, ModelMap map, @SessionAttribute("user") User user) {
        int friendId = Integer.parseInt(idStr);
        List<Message> chat = messageRepository.customGetMessagesByUserAndFriend(user.getId(), friendId);
        for (Message message : chat) {
            if ((message.getToId() == user.getId()) && message.getMessageStatus().equals(MessageStatus.NEW)) {
                message.setMessageStatus(MessageStatus.OLD);
                messageRepository.save(message);
            }
        }
        map.addAttribute("chat", chat);
        map.addAttribute("friendIdForMessage", userRepository.getOne(friendId));
        return "messageAjax";
    }


    @PostMapping("/sendMessage")
    public HttpServletResponse sendMessage(@ModelAttribute("emptyMessage") Message message, @RequestParam("messageFile") MultipartFile multipartFile, @SessionAttribute("friendIdForMessage") User friend, HttpServletResponse response) throws IOException {
        if (!multipartFile.getOriginalFilename().equals("")) {
            String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("D:\\ADMIN\\picStringDemo\\" + filename);
            multipartFile.transferTo(file);
            message.setFile(filename);
            message.setToId(friend.getId());
            message.setMessageStatus(MessageStatus.NEW);
            messageRepository.save(message);
        }else if (!message.getText().equals("")){
            message.setToId(friend.getId());
            message.setMessageStatus(MessageStatus.NEW);
            messageRepository.save(message);
        }
        return response;
    }


}
