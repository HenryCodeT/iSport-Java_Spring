package com.spring.spring.controllers;

import com.spring.spring.models.Event;
import com.spring.spring.models.User;
import com.spring.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/edit/profile")
    public String editUserProfile(HttpSession session, Model model){

        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("loggedInUser",loggedInUser);

        return "useredit";
    }
    @GetMapping("/user/edit/password")
    public String editUserPassword(HttpSession session, Model model){

        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("loggedInUser",loggedInUser);

        return "usereditpassword";
    }
    // //// USER DETAILS ////////
    // //////////////////////////
    @GetMapping("/user/{id}")
    public String showUserDetails(@PathVariable("id") Long userId, HttpSession session,Model model){
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("loggedInUser", loggedInUser);

        User user = userService.retrieveUser(userId);
        model.addAttribute("user",user);

        return "showuser";
    }
}
