package com.Tech.Register_Login_Security.config;

import com.Tech.Register_Login_Security.entity.User;
import com.Tech.Register_Login_Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User newUser= userRepository.findByEmail(email);

       if(newUser==null){
            throw new UsernameNotFoundException("user not found ...");
       }else{
           return new CustomUser(newUser);

       }

    }
}
