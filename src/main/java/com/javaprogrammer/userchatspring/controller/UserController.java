package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.mail.EmailServiceImpl;
import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
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
    UserVisitRepo userVisitRepo;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Value("${user.pictures}")
    private String imagePath;
    @Value("${pic.path}")
    private String nkar;
    @Value("${adminEmail}")
    private String mailAdmin;
    @Value("${file.path}")
    private String filePath;

    @GetMapping("/userPage")
    public String loginPageControler(ModelMap map, @SessionAttribute("user") User user) {
        String newRequest = "";
        String newMessage = "";
        if (requestRepository.countByToId(user.getId()) != 0) {
            newRequest = "" + requestRepository.countByToId(user.getId());

        }
        if (messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW) != 0) {
            newMessage = "" + messageRepository.countByToIdAndMessageStatus(user.getId(), MessageStatus.NEW);
        }
        int likeCount = 0;
        int dislikeCount = 0;
        List<Post> postList = postRepository.findAllByUserId(user.getId());
        List<Image> imageList = imageRepository.findAllByUser(user);
        for (Post post : postList) {
            likeCount += likeRepository.countByPostAndLikeStatus(post, LikeStatus.LIKE);
            dislikeCount += likeRepository.countByPostAndLikeStatus(post, LikeStatus.DISLIKE);
        }
        for (Image image : imageList) {
            likeCount += likeRepository.countByImageAndLikeStatus(image, LikeStatus.LIKE);
            dislikeCount += likeRepository.countByImageAndLikeStatus(image, LikeStatus.DISLIKE);
        }

        map.addAttribute("rating", (likeCount - dislikeCount));
        map.addAttribute("userVisit", userVisitRepo.countByUser(user));
        map.addAttribute("posts", postList);
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
                if (friend.getFriendId() == user.getId() || friend.getUserId() == user.getId()) {
                    map.addAttribute("infoFriend", "myFriend");
                }
                if (friend.getFriendId() == id) {

                    User one = userRepository.getOne(friend.getUserId());
                    otherUsersFriends.add(one);
                } else {

                    User one = userRepository.getOne(friend.getFriendId());
                    otherUsersFriends.add(one);
                }

            }
            User otherUser = userRepository.findOne(id);
            if (userVisitRepo.findByToId(user.getId()) == null) {
                userVisitRepo.save(new UserVisit(user.getId(), otherUser));
            }
            int likeCount = 0;
            int dislikeCount = 0;
            List<Post> postList = postRepository.findAllByUserId(id);
            List<Image> imageList = imageRepository.findAllByUser(otherUser);
            for (Post post : postList) {
                likeCount += likeRepository.countByPostAndLikeStatus(post, LikeStatus.LIKE);
                dislikeCount += likeRepository.countByPostAndLikeStatus(post, LikeStatus.DISLIKE);
            }
            for (Image image : imageList) {
                likeCount += likeRepository.countByImageAndLikeStatus(image, LikeStatus.LIKE);
                dislikeCount += likeRepository.countByImageAndLikeStatus(image, LikeStatus.DISLIKE);
            }

            map.addAttribute("rating", (likeCount - dislikeCount));
            map.addAttribute("userVisit", userVisitRepo.countByUser(otherUser));
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
    public HttpServletResponse removeFriend(@RequestParam("friendForRemove") int id, HttpServletResponse response, @SessionAttribute("user") User user) {

        Friend friend = friendRepository.customGetFriend(user.getId(), id);
        if (friend != null) {

            friendRepository.delete(friend);
        }
        return response;
    }

    @GetMapping("/removeFriends")
    public String removeFriends(@RequestParam("friendForRemove") int id, @SessionAttribute("user") User user) {

        Friend friend = friendRepository.customGetFriend(user.getId(), id);
        if (friend != null) {

            friendRepository.delete(friend);
        }
        return "redirect:/allFriends";
    }

    @GetMapping("/settingPage")
    public String goToSettingPage(ModelMap map, @RequestParam(value = "message", required = false) String message) {
        map.addAttribute("message", message != null ? message : "");
        return "settings";
    }


    @PostMapping("/updateUser")
    public String registerUser(@ModelAttribute("user") User user,

                               @RequestParam("pic") MultipartFile multipartFile,
                               ModelMap map,
                               @RequestParam("oldPassword") String oldPassword, @SessionAttribute("user") User user1) throws IOException {


        StringBuilder sb = new StringBuilder();

        if (multipartFile.getOriginalFilename().endsWith(".jpg")) {
            String petqChe;
            String picname = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(nkar + picname);
            multipartFile.transferTo(file);
            user.setPicture(picname);
        }
        if (user.getPicture() != null) {
            user.setPicture(user.getPicture());
        }


        if ((oldPassword.equals("") && !user.getPassword().equals("")) || (!oldPassword.equals("") && user.getPassword().equals(""))) {
            sb.append("Please input old psssword or new password");
            return "redirect:/settingPage?message=" + sb.toString();

        }
        if (user.getPassword() != null && !user.getPassword().equals("")
                && oldPassword != null && !oldPassword.equals("")) {
            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

            if (passwordEncoder.matches(oldPassword, userRepository.findOne(user.getId()).getPassword())) {


                if (!user.getPassword().matches(pattern)) {

                    String passwordValidStr;
                    passwordValidStr = "Password will be<br>  -a digit must occur at least once<br>" +
                            "  - a lower case letter must occur at least once<br>" +
                            "  - an upper case letter must occur at least once<br>" +
                            "  - a special character must occur at least once<br>" +
                            "  - no whitespace allowed in the entire string<br>" +
                            "  - anything, at least eight places though<br>";
                    sb.append(passwordValidStr);
                    return "redirect:/settingPage?message=" + sb.toString();
                }

                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                sb.append("Old password is not valid");
                return "redirect:/settingPage?message=" + sb.toString();

            }
        }


        if (user.getPassword().equals("")) {

            user.setPassword(userRepository.findOne(user.getId()).getPassword());

        }
        userRepository.save(user);

        return "redirect:/userPage";

    }

    @GetMapping("/allFriends")
    public String allFriendsPage(ModelMap map, @SessionAttribute("user") User user) {
        List<Friend> friends = friendRepository.serchAllFriends(user.getId());
        List<User> userFriends = new LinkedList<>();

        for (Friend friend : friends) {
            if (friend.getFriendId() == user.getId()) {
                User one = userRepository.getOne(friend.getId());
                userFriends.add(one);
            } else {
                User one = userRepository.getOne(friend.getFriendId());
                userFriends.add(one);
            }
        }

        map.addAttribute("userFriends", userFriends);


        return "friend";
    }

    @PostMapping("/contactTheAdmin")
    public String contactTheAdmin(@RequestParam("sendFile") MultipartFile multipartFile,
                                  @RequestParam("description") String description,
                                  @SessionAttribute("user") User user) {

        File file = new File(filePath + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);

            emailService.sendMessageWithAttachment(mailAdmin, user.getEmail(), description, filePath + multipartFile.getOriginalFilename());

        } catch (MessagingException | IOException e) {
            return "redirect:http://www.designernews.co/404";
        }
        return "contactTheAdmin";

    }

    @GetMapping("/contactTheAdminPage")
    public String contactTheAdminPage() {
        return "contactTheAdmin";
    }

}
