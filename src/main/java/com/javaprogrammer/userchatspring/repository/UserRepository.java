package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User getByEmailAndPassword(String email,String password);
}
