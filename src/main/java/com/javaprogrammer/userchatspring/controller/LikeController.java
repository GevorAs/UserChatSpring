package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.Like;
import com.javaprogrammer.userchatspring.model.LikeStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class LikeController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LikeRepository likeRepository;


    @GetMapping("/likeForImage")
    public String like(@RequestParam("imageId") int imageId, @SessionAttribute("user") User user, ModelMap map, @RequestParam("otherUser") int otherUser) {

        Like byUserIdAndImageId = likeRepository.getByUserIdAndImageId(user.getId(), imageId);
        if (byUserIdAndImageId != null) {
            byUserIdAndImageId.setLikeStatus(LikeStatus.LIKE);
            likeRepository.save(byUserIdAndImageId);
        } else {
            Like like = new Like();
            like.setUserId(user.getId());
            like.setLikeStatus(LikeStatus.LIKE);
            like.setImageId(imageId);
            likeRepository.save(like);
        }

        map.addAttribute("otherUser", otherUser);
        return "redirect:/guestImage";

    }

    @GetMapping("/dislikeForImage")
    public String disLike(@RequestParam("imageId") int imageId, @SessionAttribute("user") User user, ModelMap map, @RequestParam("otherUser") int otherUser) {
        Like byUserIdAndImageId = likeRepository.getByUserIdAndImageId(user.getId(), imageId);
        if (byUserIdAndImageId != null) {
            byUserIdAndImageId.setLikeStatus(LikeStatus.DISLIKE);
            likeRepository.save(byUserIdAndImageId);
        } else {
            Like like = new Like();
            like.setUserId(user.getId());
            like.setLikeStatus(LikeStatus.DISLIKE);
            like.setImageId(imageId);
            likeRepository.save(like);
        }
        map.addAttribute("otherUser", otherUser);
        return "redirect:/guestImage";

    }


    @GetMapping("/likeForPost")
    public String postLike(@RequestParam("postId") int postId, @SessionAttribute("user") User user, ModelMap map, @RequestParam("postUserId") int postUserId) {

        Like byUserIdAndPostId = likeRepository.getByUserIdAndPostId(user.getId(), postId);


        if (byUserIdAndPostId != null) {
            byUserIdAndPostId.setLikeStatus(LikeStatus.LIKE);
            likeRepository.save(byUserIdAndPostId);
        } else {
            Like like = new Like();
            like.setUserId(user.getId());
            like.setLikeStatus(LikeStatus.LIKE);
            like.setPostId(postId);
            likeRepository.save(like);
        }

        map.addAttribute("postId", postId);
        return "redirect:/seePost";

    }

    @GetMapping("/dislikeForPost")
    public String postDisLike(@RequestParam("postId") int postId, @SessionAttribute("user") User user, ModelMap map, @RequestParam("postUserId") int postUserId) {
        Like byUserIdAndPostIdId = likeRepository.getByUserIdAndPostId(user.getId(), postId);
        if (byUserIdAndPostIdId != null) {
            byUserIdAndPostIdId.setLikeStatus(LikeStatus.DISLIKE);
            likeRepository.save(byUserIdAndPostIdId);
        } else {
            Like like = new Like();
            like.setUserId(user.getId());
            like.setLikeStatus(LikeStatus.DISLIKE);
            like.setPostId(postId);
            likeRepository.save(like);
        }
        map.addAttribute("postId", postId);
        return "redirect:/seePost";

    }


}
