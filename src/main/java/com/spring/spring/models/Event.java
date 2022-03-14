package com.spring.spring.models;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="events")
public class Event {
    // //// VARIABLES ////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Se requiere nombre de evento")
    @Size(min = 3, max = 30, message = "nombre de evento invalido")
    private String eventName;

    @NotEmpty(message = "Se requiere nombre del lugar")
    @Size(min = 3, max = 100, message = "nombre del lugar invalido")
    private String locationName;

    @NotNull(message = "Seleccione la cantidad de asistentes")
    @Range(min = 1, max = 100)
    private Integer attendees;

    @Future
    @NotNull(message = "Seleccione la fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date eventDate;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date createdAt;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // many to one: an event can be created by one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private User creator;
    // many to many : event can assist many users
    @ManyToMany
    @JoinTable(name = "users_events", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> eventUsers;

    // many to many : event can like many users
    @ManyToMany
    @JoinTable(name = "events_likes", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> likeUsers;

    // Event & Messages
    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    private List<Message> messages;

    public Event(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getAttendees() {
        return attendees;
    }

    public void setAttendees(Integer attendees) {
        this.attendees = attendees;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(List<User> eventUsers) {
        this.eventUsers = eventUsers;
    }

    public List<User> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<User> likeUsers) {
        this.likeUsers = likeUsers;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
