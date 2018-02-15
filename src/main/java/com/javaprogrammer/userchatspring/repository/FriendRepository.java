package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend,Integer> {
}
