package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class LikeController {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private LikeRepository likeRepository;


    @GetMapping("/likeForImage")
    public String like(@RequestParam("imageId") int imageId, @SessionAttribute("user") User user, ModelMap map) {
        Image image = imageRepository.findOne(imageId);

        Like byUserIdAndImageId = likeRepository.getByUserAndImage(user, image);
        if (byUserIdAndImageId != null) {
            byUserIdAndImageId.setLikeStatus(LikeStatus.LIKE);
            likeRepository.save(byUserIdAndImageId);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setLikeStatus(LikeStatus.LIKE);
            like.setImage(image);
            likeRepository.save(like);
        }

        map.addAttribute("otherUser", image.getUser().getId());
        return "redirect:/guestImage";

    }

    @GetMapping("/dislikeForImage")
    public String disLike(@RequestParam("imageId") int imageId, @SessionAttribute("user") User user, ModelMap map) {
        Image image = imageRepository.findOne(imageId);
        Like byUserIdAndImageId = likeRepository.getByUserAndImage(user, image);
        if (byUserIdAndImageId != null) {
            byUserIdAndImageId.setLikeStatus(LikeStatus.DISLIKE);
            likeRepository.save(byUserIdAndImageId);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setLikeStatus(LikeStatus.DISLIKE);
            like.setImage(image);
            likeRepository.save(like);
        }
        map.addAttribute("otherUser", image.getUser().getId());
        return "redirect:/guestImage";

    }


    @GetMapping("/likeForPost")
    public String postLike(@RequestParam("postId") int postId, @SessionAttribute("user") User user, ModelMap map) {

        Post post = postRepository.findOne(postId);
        Like byUserIdAndPostId = likeRepository.getByUserAndPost(user, post);


        if (byUserIdAndPostId != null) {
            byUserIdAndPostId.setLikeStatus(LikeStatus.LIKE);
            likeRepository.save(byUserIdAndPostId);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setLikeStatus(LikeStatus.LIKE);
            like.setPost(post);
            likeRepository.save(like);
        }

        map.addAttribute("postId", post.getId());
        return "redirect:/seePost";

    }

    @GetMapping("/dislikeForPost")
    public String postDisLike(@RequestParam("postId") int postId, @SessionAttribute("user") User user, ModelMap map) {
        Post post = postRepository.findOne(postId);

        Like byUserIdAndPostIdId = likeRepository.getByUserAndPost(user, post);
        if (byUserIdAndPostIdId != null) {
            byUserIdAndPostIdId.setLikeStatus(LikeStatus.DISLIKE);
            likeRepository.save(byUserIdAndPostIdId);
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setLikeStatus(LikeStatus.DISLIKE);
            like.setPost(post);
            likeRepository.save(like);
        }
        map.addAttribute("postId", post.getId());
        return "redirect:/seePost";

    }


}
