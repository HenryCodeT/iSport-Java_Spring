package com.spring.spring.services;

import com.spring.spring.models.LoginUser;
import com.spring.spring.models.PasswordUser;
import com.spring.spring.models.User;
import com.spring.spring.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // //// REGISTER NEW USER /////////////////////////////

    public User register(User newUser, BindingResult result) {

        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) { // Check if E-mail is already in database
            result.rejectValue("email", "Unique", "¡El email ya existe agregue otro!");
        }
        if (!newUser.getPassword().equals(newUser.getConfirm())) { // Check to make sure password matches confirm
            // password
            result.rejectValue("confirm", "Matches", "¡Su contraseña de confirmacion no es correcta!");
        }
        if (result.hasErrors()) { // Check if there are errors on the form
            return null;
        } else { // BCrypt hash the password then create a new User
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            System.out.println("---------------------- CREATED NEW USER DB ----------------------");
            return userRepository.save(newUser);
        }
    }

    // //// RETRIEVE USER //////////////////////////////////////////

    public User login(LoginUser newLogin, BindingResult result) {
        if (result.hasErrors()) { // Check form for errors
            return null;
        }

        // Find the User in the database by their email
        Optional<User> potentialUser = userRepository.findByEmail(newLogin.getEmail());
        if (!potentialUser.isPresent()) { // If User not found, do NOT log in User
            result.rejectValue("email", "Unique", "¡Email no encontrado!");
            return null;
        }
        User user = potentialUser.get();
        // Check the password given in the form vs the password hash in the database
        if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "¡Contraseña invalida!");
        }
        if (result.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }

    // //// RETRIEVE USER BY ID //////////////////////////

    public User retrieveUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if ( optUser.isPresent() ) {
            return optUser.get();
        } else {
            return null;
        }
    }
    // //// UPDATE USER ////////////////////
    // ////////////////////////////////////
    public User updateUser(User editUser) {
        return userRepository.save(editUser);
    }
    // //// UPDATE USER FIELDS //////////////
    // //////////////////////////////////////
    public  User updateUserFields(User editUser, User oldUser,BindingResult result){
        System.out.println("edir User"+editUser.getEmail());
        System.out.println("old User"+oldUser.getEmail());
        System.out.println(userRepository.findByEmail(editUser.getEmail()).isPresent() && !(editUser.getEmail().equals(oldUser.getEmail())));
        if (userRepository.findByEmail(editUser.getEmail()).isPresent() && !(editUser.getEmail().equals(oldUser.getEmail()))) { // Check if E-mail is already in database
            result.rejectValue("email", "Unique", "Este email ya existe!");
        }
        if (result.hasErrors()) { // Check if there are errors on the form
            System.out.println("CANT UPDATE");
            return null;
        }else {
            System.out.println("--------------- USER EDITED -----------------");
            return userRepository.save(editUser);
        }
    }
    public User updatePassword(Long id,PasswordUser passwordUser, BindingResult result) {
        User user = retrieveUser(id);
        user.setConfirm(user.getPassword());
        if (!BCrypt.checkpw(passwordUser.getOldPassword(), user.getPassword())) {
            result.rejectValue("oldPassword", "Matches", "!Contraseña invalida¡");
        }
        if (result.hasErrors()) {
            return null;
        }
        String hashed = BCrypt.hashpw(passwordUser.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);

    }
}
