package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Post;
import com.javaprogrammer.userchatspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post,Integer> {
    List<Post> findAllByUserId(int id);






}
