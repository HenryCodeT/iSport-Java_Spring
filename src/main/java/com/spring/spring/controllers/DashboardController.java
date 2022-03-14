package com.spring.spring.controllers;

import com.spring.spring.models.Event;
import com.spring.spring.models.User;
import com.spring.spring.services.EventService;
import com.spring.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;
    // ////// HOME //////////////////////////////////////////////////////

    @GetMapping("/home")
    public String dashboard(Model model, HttpSession session) {
        System.out.println("************** HOME *********** ");
        System.out.println("value created session: " + session.getAttribute("user_id"));
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";

        } else {

            User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
            model.addAttribute("loggedInUser", loggedInUser);

            System.out.println("eventos creados por el usuario: "+loggedInUser.getCreatorEvents());

            List<Event> eventList = eventService.findAllEvents();
            model.addAttribute("events",eventList);

            System.out.println("----------- HOME ------------- ");
            return "home";
        }
    }
}
