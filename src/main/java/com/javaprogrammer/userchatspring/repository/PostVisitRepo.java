package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Post;
import com.javaprogrammer.userchatspring.model.PostVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostVisitRepo extends JpaRepository<PostVisit, Integer> {

    int countByPost(Post post);
}
