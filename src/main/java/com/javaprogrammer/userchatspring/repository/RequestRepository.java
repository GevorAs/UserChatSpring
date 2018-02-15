package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Integer> {
}
