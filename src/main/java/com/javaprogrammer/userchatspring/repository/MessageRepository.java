package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
