package com.spring.spring.controllers;

import com.spring.spring.models.Event;
import com.spring.spring.models.PasswordUser;
import com.spring.spring.models.User;
import com.spring.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    // //// EDIT USER FIELDS //////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////
    // ********************* GET ******************************************
    @GetMapping("/user/edit/profile")
    public String editUserProfile(HttpSession session, Model model){
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("editUser",loggedInUser);

        return "useredit";
    }
    // ********************* POST ****************************************************
    @PostMapping("/user/edit")
    public String editUserFormCon(@Valid @ModelAttribute("editUser") User editUser, BindingResult result, HttpSession session){
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        System.out.println("**************** EDIT USER ****************");
        System.out.println("value created session: " + session.getAttribute("user_id"));
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        User userOld = new User();
        userOld.setEmail(loggedInUser.getEmail());

        loggedInUser.setEmail(editUser.getEmail());
        loggedInUser.setUserName(editUser.getUserName());
        loggedInUser.setUserLastName(editUser.getUserLastName());
        loggedInUser.setPassword(loggedInUser.getPassword());
        loggedInUser.setConfirm(loggedInUser.getPassword());

        User newUser = userService.updateUserFields(loggedInUser,userOld,result);
        System.out.println("error"+result.getAllErrors());
        if (result.hasErrors()){
            return "useredit";
        }
        return "redirect:/home";
    }
    // //// EDIT PASSWORD /////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ********************* GET ************************************************
    @GetMapping("/user/edit/password")
    public String editUserPassword(Model model, HttpSession session){
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("passwordUser",new PasswordUser());
        return "usereditpassword";
    }
    // ****************************** POST ********************************************
    @PostMapping("/edit/password")
    public String changeUserPassword(@Valid @ModelAttribute("passwordUser") PasswordUser passwordUser,
                                     BindingResult result,
                                     HttpSession session){
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        Long id = (Long) session.getAttribute("user_id");
        User user = userService.updatePassword(id,passwordUser,result);
        if (result.hasErrors()) {
            return "usereditpassword";
        }
        return "redirect:/user/edit/password";
    }

    // //// USER DETAILS ////////
    // //////////////////////////
    @GetMapping("/user/{id}")
    public String showUserDetails(@PathVariable("id") Long userId, HttpSession session,Model model){
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("loggedInUser", loggedInUser);

        User user = userService.retrieveUser(userId);
        model.addAttribute("user",user);

        return "showuser";
    }
}
