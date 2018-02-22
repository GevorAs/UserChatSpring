package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.FriendRepository;
import com.javaprogrammer.userchatspring.repository.MessageRepository;
import com.javaprogrammer.userchatspring.repository.RequestRepository;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    RequestRepository requestRepository;

    @Value("${pic.path}")
    private String nkar;
    @Value("${file.path}")
    private String filePath;




    @GetMapping("/sendRequest")
    public HttpServletResponse sendRequest(HttpServletResponse response, @SessionAttribute("friend") User friend, @SessionAttribute("user") User user) {
        if (!friend.equals(user)) {
            Request request = new Request();
            request.setToId(friend.getId());
            request.setFromId(user.getId());
            Request request1 = requestRepository.findByFromIdAndToId(user.getId(), friend.getId());
            Request request2 = requestRepository.findByToIdAndFromId(user.getId(), friend.getId());
            Friend byFriendIdAndUserId = friendRepository.findByFriendIdAndUserId(user.getId(), friend.getId());
            Friend byUserIdAndFriendId = friendRepository.findByUserIdAndFriendId(user.getId(), friend.getId());
            if (byFriendIdAndUserId == null && byUserIdAndFriendId == null) {
                if (request1 == null && request2 == null) {
                    requestRepository.save(request);
                }
            }
        }

        return response;
    }



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
    public String sendMessage(@ModelAttribute("emptyMessage") Message message, @RequestParam("messageFile") MultipartFile multipartFile,@RequestParam("messagePic")MultipartFile multipartPic,@SessionAttribute("user")User user, @SessionAttribute("friendIdForMessage") User friend,ModelMap map ) throws IOException {
       boolean fileBool=multipartFile.getOriginalFilename().equals("");
       boolean picBool=multipartPic.getOriginalFilename().equals("");

        if (!fileBool||!picBool) {
            if (!fileBool) {
                String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                File file = new File(filePath + filename);
                multipartFile.transferTo(file);
                message.setFile(filename);
                message.setToId(friend.getId());
                message.setMessageStatus(MessageStatus.NEW);
                messageRepository.save(message);
            }else {
                String filename = System.currentTimeMillis() + "_" + multipartPic.getOriginalFilename();
                File file = new File(nkar + filename);
                multipartPic.transferTo(file);
                message.setPicture(filename);
                message.setToId(friend.getId());
                message.setMessageStatus(MessageStatus.NEW);
                messageRepository.save(message);
            }
        }else if (!message.getText().equals("")){
            message.setToId(friend.getId());
            message.setMessageStatus(MessageStatus.NEW);
            messageRepository.save(message);
        }


        List<Message> chat = messageRepository.customGetMessagesByUserAndFriend(user.getId(), friend.getId());
        for (Message message111 : chat) {
            if ((message111.getToId() == user.getId()) && message111.getMessageStatus().equals(MessageStatus.NEW)) {
                message111.setMessageStatus(MessageStatus.OLD);
                messageRepository.save(message111);
            }
        }
        map.addAttribute("chat", chat);
        map.addAttribute("friendIdForMessage", userRepository.getOne(friend.getId()));
        return "messageAjax";

    }


}
