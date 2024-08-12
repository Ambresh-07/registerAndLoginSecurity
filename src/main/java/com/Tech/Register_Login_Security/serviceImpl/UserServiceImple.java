package com.Tech.Register_Login_Security.serviceImpl;

import com.Tech.Register_Login_Security.entity.User;
import com.Tech.Register_Login_Security.repository.UserRepository;
import com.Tech.Register_Login_Security.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.http.HttpRequest;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
       user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void removeSessionMessage() {
        HttpSession session = (
                (ServletRequestAttributes)
                        (RequestContextHolder.getRequestAttributes())
        ).getRequest().getSession();

        session.removeAttribute("msg");

    }
}
