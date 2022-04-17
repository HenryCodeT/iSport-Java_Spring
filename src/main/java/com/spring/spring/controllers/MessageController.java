package com.spring.spring.controllers;

import com.spring.spring.models.Event;
import com.spring.spring.models.Message;
import com.spring.spring.models.User;
import com.spring.spring.services.EventService;
import com.spring.spring.services.MessageService;
import com.spring.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MessageController {
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;
    @Autowired
    MessageService messageService;

    @PostMapping("/create-message")
    public String newMessage(@Valid @ModelAttribute("message") Message message, BindingResult result, HttpSession session, Model model){
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/login";
        }
        System.out.println("*********** POST MESSAGE **********");
        System.out.println("message content: "+message.getContent());
        System.out.println("message user: "+message.getAuthor());
        System.out.println("message event: "+message.getEvent());
        if (result.hasErrors()) {
            System.out.println("------------- POST MESSAGE ERROR ------------");
            User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
            Event event = eventService.findServiceById(message.getEvent().getId());
            model.addAttribute("loggedInUser",loggedInUser);
            model.addAttribute("event",event);

            return "eventshow";
        }
        System.out.println("------------- POST MESSAGE SUCCESS ------------");
        messageService.createNewMessage(message);
        return "redirect:/event/"+message.getEvent().getId();
    }
}
