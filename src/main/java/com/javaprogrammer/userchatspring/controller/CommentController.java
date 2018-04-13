package com.javaprogrammer.userchatspring.controller;


import com.javaprogrammer.userchatspring.model.Comment;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class CommentController {


    @Autowired
    private CommentRepository commentRepository;
    @Value("${pic.path}")
    private String nkar;
    @Value("${file.path}")
    private String filePath;

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("emptyComment") Comment comment,
                             @RequestParam("commentFile") MultipartFile multipartFile,
                             @RequestParam("commentImage") MultipartFile multipartPic,
                             @RequestParam("po") int postId,
                             @SessionAttribute("user") User user, ModelMap map) throws IOException {
        boolean fileBool = multipartFile.getOriginalFilename().equals("");
        boolean picBool = multipartPic.getOriginalFilename().equals("");

        if (!fileBool || !picBool) {
            if (!fileBool) {
                if (!multipartFile.getOriginalFilename().endsWith(".jpg")
                        && !multipartFile.getOriginalFilename().endsWith(".jpeg")
                        && !multipartFile.getOriginalFilename().endsWith(".png")) {
                    String filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                    File file = new File(filePath + filename);
                    multipartFile.transferTo(file);
                    comment.setFile(filename);
                    comment.setUser(user);
                    comment.setPostId(postId);
                    commentRepository.save(comment);
                }
            } else if (multipartPic.getOriginalFilename().endsWith(".jpg")

                    ||multipartPic.getOriginalFilename().endsWith(".jpeg")
                    || multipartPic.getOriginalFilename().endsWith(".png")) {
                String picName = System.currentTimeMillis() + "_" + multipartPic.getOriginalFilename();
                File file = new File(nkar + picName);
                multipartPic.transferTo(file);

                comment.setPicture(picName);
                comment.setUser(user);
                comment.setPostId(postId);
                commentRepository.save(comment);
            }
        } else if (!comment.getText().equals("")) {
            comment.setUser(user);
            comment.setPostId(postId);
            commentRepository.save(comment);
        }
       map.addAttribute("postId",postId);

        return "redirect:/seePost";
    }


}
