package com.spring.spring.controllers;

import com.spring.spring.models.Event;
import com.spring.spring.models.LoginUser;
import com.spring.spring.models.Message;
import com.spring.spring.models.User;
import com.spring.spring.services.EventService;
import com.spring.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;
    // //// NEW EVENT //////////////
    // /////////////////////////////
    // ---------- view ------------
    @GetMapping("/create-event")
    public String createEventView(@ModelAttribute("newEvent") Event newEvent, HttpSession session, Model model){
        System.out.println("************** CREATE EVENT VIEW *********** ");
        System.out.println("value created session: " + session.getAttribute("user_id"));

        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("loggedInUser", loggedInUser);

        return "eventcreate";
    }
    // ---------- post -------------
    @PostMapping("/create-event")
    public String createEventPost(@Valid @ModelAttribute("newEvent") Event newEvent, BindingResult result, Model model,
                                  HttpSession session){
        System.out.println("**************** POST EVENT ****************");
        System.out.println("value created session: " + session.getAttribute("user_id"));
        if (result.hasErrors()) {
            return "eventcreate";
        }
        System.out.println("---------------    POST EVENT   ---------------");
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("loggedInUser", loggedInUser);
        newEvent.setCreator(loggedInUser);
        Event eventCreated = eventService.createEvent(newEvent);
        System.out.println("evento creado id: "+eventCreated.getId());
        loggedInUser.getUserEvents().add(eventCreated);
        userService.updateUser(loggedInUser);
        System.out.println("---- usuario incluyendose a su evento ----");
        return "redirect:/home";
    }
    // //// SEARCH EVENT //////////////
    // ///////////////////////////////
    // ---------- view ---------------
    @GetMapping("/search-event")
    public String seachEventView( Model model, HttpSession session){

        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        model.addAttribute("loggedInUser", loggedInUser);

        List<Event> eventList = eventService.findAllEvents();
        model.addAttribute("events",eventList);

        return "eventsearch";
    }
    // ------- add user to event---------
    @GetMapping("/events/add/{id}")
    public String addUserToEvent(@PathVariable("id") Long eventId, HttpSession session ){

        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        Event event = eventService.findServiceById(eventId);

        loggedInUser.getUserEvents().add(event);
        userService.updateUser(loggedInUser);

        return "redirect:/search-event";
    }
    // //// SHOW EVENT //////////////
    // ///////////////////////////////
    // ---------- view ---------------
    @GetMapping("/event/{id}")
    public String showEvent(@PathVariable("id") Long eventId, @ModelAttribute("message") Message message, HttpSession session, Model model){

        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        Event event = eventService.findServiceById(eventId);

        model.addAttribute("loggedInUser",loggedInUser);
        model.addAttribute("event",event);

        return "eventshow";
    }
    // //// EDIT EVENT //////////////
    // ///////////////////////////////
    // ---------- view ---------------
    @GetMapping("/event/edit/{id}")
    public String showEvent(@PathVariable("id") Long eventId, HttpSession session, Model model){

        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        Event event = eventService.findServiceById(eventId);

        model.addAttribute("loggedInUser",loggedInUser);
        model.addAttribute("newEvent",event);

        return "eventedit";
    }
    // //// REMOVE USER FROM EVENT //////////////
    // /////////////////////////////////////////
    // ---------- view ------------------------
    @GetMapping("/events/remove/{id}")
    public String removeUserFromEvent(@PathVariable("id") Long eventId, HttpSession session ){
        System.out.println("************ REMOVE USER FROM EVENTS ************");
        User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
        Event event = eventService.findServiceById(eventId);

        loggedInUser.getUserEvents().remove(event);
        userService.updateUser(loggedInUser);

        return "redirect:/home";
    }

    // //// DELETE EVENT //////////////
    // ///////////////////////////////
    // ---------- view ---------------
    @DeleteMapping("/event/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long eventId){
        System.out.println("********** DELETE EVENT *********** ");
        System.out.println("EVENTO ID: "+eventId);
        Event event = eventService.findServiceById(eventId);
        eventService.deleteEvent(event);
        return "redirect:/home";
    }
}
