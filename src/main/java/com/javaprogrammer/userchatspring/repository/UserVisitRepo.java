package com.javaprogrammer.userchatspring.repository;

import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.model.UserVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVisitRepo extends JpaRepository<UserVisit,Integer> {
    int countByUser(User user);
    UserVisit findByToId(int id);
}
