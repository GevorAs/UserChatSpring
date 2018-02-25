package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Image;
import com.javaprogrammer.userchatspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface ImageRepository  extends JpaRepository<Image,Integer> {


    List<Image> findAllByUser(User user);
}
