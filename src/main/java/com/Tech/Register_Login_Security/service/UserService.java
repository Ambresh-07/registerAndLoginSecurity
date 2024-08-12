package com.Tech.Register_Login_Security.service;

import com.Tech.Register_Login_Security.entity.User;
import org.springframework.context.annotation.Bean;


public interface UserService {

    public User saveUser(User user);
    public void removeSessionMessage();
}
