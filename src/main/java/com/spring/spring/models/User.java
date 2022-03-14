package com.spring.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    // ////  VARIABLES  /////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "¡Se requiere su nombre!")
    @Size(min = 3, max = 30, message = "nombre invalido")
    private String userName;

    @NotEmpty(message = "¡Se requiere su apellido!")
    @Size(min = 3, max = 30, message = "apellido invalido")
    private String userLastName;

    @NotEmpty(message = "¡Se requiere Email!")
    @Email(message = "¡Ingrese un Email válido!")
    private String email;

    @NotEmpty(message = "¡Se requiere contraseña!")
    @Size(min = 8, max = 128, message = "La contraseña debe tener entre 8 y 128 caracteres")
    private String password;

    @Transient
    @NotEmpty(message = "Se requiere confirmar la contraseña!")
    @Size(min = 8, max = 128, message = "La confirmacion de la contraseña debe tener entre 8 y 128 caracteres")
    private String confirm;

    // one to many : a user can create many events
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    private List<Event> creatorEvents;

    // many to many : user can assist many events
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_events", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> userEvents;

    // many to many : event can like many users
    @ManyToMany
    @JoinTable(name = "events_likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> likeEvents;

    //User & Message
    @OneToMany(mappedBy="author", fetch = FetchType.LAZY)
    private List<Message> comments;

    // //// CONTRUCTOR ////
    public User() {
    }

    // //// GETTERS AND SETTERS ////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public List<Event> getCreatorEvents() {
        return creatorEvents;
    }

    public void setCreatorEvents(List<Event> creatorEvents) {
        this.creatorEvents = creatorEvents;
    }

    public List<Event> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<Event> userEvents) {
        this.userEvents = userEvents;
    }

    public List<Event> getLikeEvents() {
        return likeEvents;
    }

    public void setLikeEvents(List<Event> likeEvents) {
        this.likeEvents = likeEvents;
    }
}

