package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Integer> {
}
