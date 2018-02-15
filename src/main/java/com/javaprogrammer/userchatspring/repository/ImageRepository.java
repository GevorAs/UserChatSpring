package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository  extends JpaRepository<Image,Integer> {
}
