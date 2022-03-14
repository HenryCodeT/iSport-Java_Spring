package com.spring.spring.services;

import com.spring.spring.models.Event;
import com.spring.spring.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    // //// CREATE NEW EVENT ////
    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    // //// GET ALL EVENTS ////
    public List<Event> findAllEvents(){
        return eventRepository.findAll();
    }

    public Event findServiceById(Long eventId) {
        Optional<Event> optional = eventRepository.findById(eventId);
        if (optional.isPresent()){
            return optional.get();
        }else {
            return null;
        }
    }
    // //// DELETE EVENT //////////////
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }
}
