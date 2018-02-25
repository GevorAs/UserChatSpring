package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    private RequestRepository requestRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Value("${user.pictures}")
    private String imagePath;

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
    public String goToOtherUserPage(@RequestParam("otherUserId") int id, ModelMap map, @SessionAttribute("user") User user) {
        if (!(id == user.getId())) {

            List<Post> allPostOtherUser = postRepository.findAllByUserId(id);
            List<Friend> friends = friendRepository.serchAllFriends(id);
            List<User> otherUsersFriends = new LinkedList<>();
            for (Friend friend : friends) {
                if(friend.getFriendId()==user.getId()||friend.getUserId()==user.getId()){
                    map.addAttribute("infoFriend","myFriend");
                }
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
            if (user.getUserType() == UserType.ADMIN) {
                return "userForAdmin";
            }
            return "user";
        }
        return "redirect:/userPage";
    }

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

    @GetMapping("/acceptRequest")
    public String acceptRequest(@RequestParam("friendId") int fromId, HttpServletResponse response, @SessionAttribute("user") User user, ModelMap map) {
        Friend friend = new Friend();
        friend.setUserId(user.getId());
        friend.setFriendId(fromId);
        friendRepository.save(friend);
        Request request = requestRepository.findByFromIdAndToId(fromId, user.getId());
        requestRepository.delete(request.getId());
        List<Request> allByToId = requestRepository.findAllByToId(user.getId());
        List<User> friendRequests = new ArrayList<>();
        for (Request request1 : allByToId) {

            friendRequests.add(userRepository.getOne(request1.getFromId()));
        }
        map.addAttribute("friendRequests", friendRequests);
        return "request";
    }

    @GetMapping("/rejectRequest")
    public String rejectRequest(@RequestParam("friendId") int fromId, HttpServletResponse response, @SessionAttribute("user") User user, ModelMap map) {

        Request request = requestRepository.findByFromIdAndToId(fromId, user.getId());
        requestRepository.delete(request.getId());
        List<Request> allByToId = requestRepository.findAllByToId(user.getId());
        List<User> friendRequests = new ArrayList<>();
        for (Request request1 : allByToId) {

            friendRequests.add(userRepository.getOne(request1.getFromId()));
        }
        map.addAttribute("friendRequests", friendRequests);
        return "request";
    }


    @GetMapping("/removeFriend")
    public HttpServletResponse removeFriend(@RequestParam("friendForRemove")int id, HttpServletResponse response,@SessionAttribute("user")User user){

//        friendRepository.deleteByFriechchfghfndIdAndUserId(user.getId(),id);

        return response;
    }

}
