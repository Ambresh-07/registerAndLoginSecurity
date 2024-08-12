package com.Tech.Register_Login_Security.controller;

import com.Tech.Register_Login_Security.entity.User;
import com.Tech.Register_Login_Security.repository.UserRepository;
import com.Tech.Register_Login_Security.service.UserService;
import com.Tech.Register_Login_Security.serviceImpl.UserServiceImple;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @ModelAttribute
    public void commanPeops(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepository.findByEmail(email);
            m.addAttribute("user", user);
        }

    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/user/home")
    public String home() {
        return "home";
    }

    @GetMapping("/user/profile")
    public String profile(Principal p, Model m) {
        String email = p.getName();

        System.out.println("email is  : " + email);
        User user = userRepository.findByEmail(email);


        m.addAttribute("user", user);

        return "profile";
    }


    @PostMapping("/saveuser")
    public String createUser(@ModelAttribute User user, HttpSession session) {
        User newUser = userService.saveUser(user);
        if (newUser != null) {
            session.setAttribute("msg", "user saved successfully done..");

        } else {
            session.setAttribute("msg", "something is wrong in server");

        }
        return "redirect:/signin";

    }

    @PostMapping("/loginuser")
    public String loginUser(@ModelAttribute User user, HttpSession seesion) {
        return "login successfully done..." + "redirect:/profile";

    }

}
