package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

     List<Comment> findAllByPostId(int id);
     int countByPostId(int id);
}
