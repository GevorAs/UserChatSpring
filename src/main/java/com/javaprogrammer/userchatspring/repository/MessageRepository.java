package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.Message;
import com.javaprogrammer.userchatspring.model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByToIdAndMessageStatus(int id,MessageStatus messageStatus);
    boolean existsByToIdAndMessageStatus(int id,MessageStatus messageStatus);
}
