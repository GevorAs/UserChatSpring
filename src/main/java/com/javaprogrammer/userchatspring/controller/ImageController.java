package com.javaprogrammer.userchatspring.controller;

import com.javaprogrammer.userchatspring.model.ActiveStatus;
import com.javaprogrammer.userchatspring.model.Image;
import com.javaprogrammer.userchatspring.model.LikeStatus;
import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ImageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Value("${user.pictures}")
    private String imagePath;


    @GetMapping("/myImages")
    public String myImages(@SessionAttribute("user") User user, ModelMap map) {
        List<Image> imageList = imageRepository.findAllByUser(user);


        List<Image> imageList1 = new LinkedList<>();
        for (Image image : imageList) {
            if (image.getActiveStatus() == ActiveStatus.ACTIVE) {
                Integer like = likeRepository.countByImageAndLikeStatus(image, LikeStatus.LIKE);


                image.setLikeCount(like);

                Integer disLike = likeRepository.countByImageAndLikeStatus(image, LikeStatus.DISLIKE);


                image.setDislikeCount(disLike);

                imageList1.add(image);


            }
        }


        map.addAttribute("images", imageList1);
        return "image";
    }


    @PostMapping("/saveImage")
    public String saveImage(@RequestParam("userImage") MultipartFile multipartFile, @SessionAttribute("user") User user) throws IOException {

        if (!multipartFile.getOriginalFilename().equals("") && multipartFile.getOriginalFilename().endsWith(".jpg")) {
            String picName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
            File file = new File(imagePath + picName);

            multipartFile.transferTo(file);
            Image image = new Image();
            image.setPicture(picName);
            image.setUser(user);
            image.setLikeCount(0);
            image.setDislikeCount(0);
            image.setActiveStatus(ActiveStatus.ACTIVE);
            imageRepository.save(image);
        }

        return "redirect:/myImages";

    }


    @GetMapping("/deleteImage")
    public String deleteImage(@RequestParam("id") int id) {
        Image one = imageRepository.getOne(id);
        one.setActiveStatus(ActiveStatus.DELETED);
        imageRepository.save(one);
        return "redirect:/myImages";

    }

    @GetMapping("/guestImage")
    public String guestImage(@RequestParam("otherUser")int id, ModelMap map) {
        List<Image> imageList = imageRepository.findAllByUser(userRepository.getOne(id));
        List<Image> imageList1 = new LinkedList<>();
        for (Image image : imageList) {
           Integer like = likeRepository.countByImageAndLikeStatus(image, LikeStatus.LIKE);


                image.setLikeCount(like);

            Integer disLike = likeRepository.countByImageAndLikeStatus(image, LikeStatus.DISLIKE);


            image.setDislikeCount(disLike);

            if (image.getActiveStatus() == ActiveStatus.ACTIVE) {
                imageList1.add(image);
            }
        }
        map.addAttribute("images", imageList1);
        return "guestImage";
    }



}
