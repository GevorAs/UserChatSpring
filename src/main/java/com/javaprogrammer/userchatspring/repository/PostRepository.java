package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post,Integer> {
}
