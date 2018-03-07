package com.javaprogrammer.userchatspring.security;

import com.javaprogrammer.userchatspring.model.User;
import com.javaprogrammer.userchatspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service

public class CurrentUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findUserByEmail(s);
        if (user==null){
            throw new UsernameNotFoundException("user not exist");
        }
  return new CurrentUser(user);

    }

}
