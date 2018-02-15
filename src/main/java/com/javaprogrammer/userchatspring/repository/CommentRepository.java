package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
