package com.javaprogrammer.userchatspring.security;

import com.javaprogrammer.userchatspring.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(),user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }
   public User getUser(){
        return user;
    }
    public int getId(){
        return user.getId();
    }
}
