package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Request;
import com.javaprogrammer.userchatspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Integer> {
  boolean existsByToId(int id);
  Integer countByToId(int id);
}
