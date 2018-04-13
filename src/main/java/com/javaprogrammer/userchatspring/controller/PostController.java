package com.javaprogrammer.userchatspring.controller;


import com.javaprogrammer.userchatspring.model.*;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class PostController {

   @Autowired
    private PostRepository postRepository;
   @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    PostVisitRepo postVisitRepo;

    @Value("${pic.path}")
    private String nkar;
    @Value("${file.path}")
    private String filePath;


    @GetMapping("/addPost")
    public String AddpostPage(ModelMap map) {

        map.addAttribute("addPost", new Post());
        return "addPost";

    }


    @PostMapping("/savePost")
    public String sendpost(@ModelAttribute("addPost") Post post,
                           @RequestParam("postFile") MultipartFile multipartFile,
                           @RequestParam("postImage") MultipartFile multipartPic,
                           @SessionAttribute("user") User user,
                           ModelMap map) throws IOException {

        boolean fileBool = multipartFile.getOriginalFilename().equals("");
        boolean picBool = multipartPic.getOriginalFilename().equals("");

        if (!fileBool || !picBool) {
            if (!fileBool) {


                if (!multipartFile.getOriginalFilename().endsWith(".jpg") && !multipartFile.getOriginalFilename().endsWith(".jpeg") && !multipartFile.getOriginalFilename().endsWith(".png")) {
                    String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                    File file = new File(filePath + filename);
                    multipartFile.transferTo(file);
                    post.setFile(filename);
                    post.setActiveStatus(ActiveStatus.ACTIVE);
                    post.setUser(user);
                    post.setDislikeCount(0);
                    post.setLikeCount(0);
                    postRepository.save(post);
                }
            } else {
                if (multipartPic.getOriginalFilename().endsWith(".jpg") || multipartPic.getOriginalFilename().endsWith(".jpeg") || multipartPic.getOriginalFilename().endsWith(".png")) {
                    String filename = System.currentTimeMillis() + "_" + multipartPic.getOriginalFilename();
                    File file = new File(nkar + filename);
                    multipartPic.transferTo(file);
                    post.setPicture(filename);
                    post.setActiveStatus(ActiveStatus.ACTIVE);
                    post.setUser(user);
                    post.setDislikeCount(0);
                    post.setLikeCount(0);
                    postRepository.save(post);
                }
            }
        } else if (!post.getText().equals("")) {
            post.setActiveStatus(ActiveStatus.ACTIVE);
            post.setUser(user);
            post.setDislikeCount(0);
            post.setLikeCount(0);
            postRepository.save(post);
        }


        return "redirect:/userPage";

    }

    @GetMapping("/seePost")
    public String seePost(@RequestParam("postId") int id, ModelMap map, @SessionAttribute("user") User user) {

        Post post = postRepository.findOne(id);

        if (user.getId() != post.getUser().getId()) {
            postVisitRepo.save(new PostVisit(post));
        }
        Integer like = likeRepository.countByPostAndLikeStatus(post, LikeStatus.LIKE);
        Integer disLike = likeRepository.countByPostAndLikeStatus(post, LikeStatus.DISLIKE);
        post.setDislikeCount(disLike);
        post.setLikeCount(like);
        map.addAttribute("onePost", post);
        map.addAttribute("postComements", commentRepository.findAllByPostId(id));
        map.addAttribute("commentCount", commentRepository.countByPostId(id));
        map.addAttribute("emptyComment", new Comment());
        map.addAttribute("postVisits", postVisitRepo.countByPost(post));

        return "postPage";

    }


    @GetMapping("/sharePostToUserPage")

    public String sharePost(@RequestParam("sharePostId") int postId, @SessionAttribute("user") User user, ModelMap map) {
        Post sharePost = postRepository.findOne(postId);

        postRepository.save(new Post(user,sharePost.getText(),sharePost.getFile(),sharePost.getPicture(),ActiveStatus.ACTIVE,0,0));
        map.addAttribute("postId", postId);
        return "redirect:/seePost";
    }


}
