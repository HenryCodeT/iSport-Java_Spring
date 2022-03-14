package com.spring.spring.controllers;

import com.spring.spring.models.LoginUser;
import com.spring.spring.models.User;
import com.spring.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginRegisterController {
    @Autowired
    private UserService userService;

    // ///////////// INIT ////////////////////////////

    @GetMapping("/")
    public String init(HttpSession session) {
        System.out.println("///////////////////////INIT////////////////////////");
        System.out.println("//////////////// INIT SESSION :"+ session.getAttribute("user_id"));
        return "redirect:/home";
    }

    // ///////////// USER LOGIN///////////////
    // ///////////////////////////////////////////////
    // ------------------- view -------------------
    @GetMapping("/login")
    public String loginView(Model model, HttpSession session) {
        // Enlazar objetos User y LoginUser vacíos al JSP
        // para capturar la entrada del formulariocopy
        System.out.println("*************** INDEX ************");
        System.out.println("login en session: " + session.getAttribute("user_id"));
        if (session.getAttribute("user_id") != null) { // If user is in session
            return "redirect:/home"; // re-route to dashboard
        }
        model.addAttribute("newLogin", new LoginUser());
        System.out.println("------------- INDEX --------------");
        return "login";
    }
    // ----------------- post -----------------
    @PostMapping("/login")
    public String loginPost(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
                        HttpSession session) {
        System.out.println("*************** LOGIN ************");
        // Añadir una vez implementado el servicio:
        // User user = userServ.login(newLogin, result);

        User user = userService.login(newLogin, result);

        if (result.hasErrors()) {
            return "login";
        }

        // ¡Sin errores!
        // PARA HACER después: Almacena sus ID de la base de datos en sesión,
        // en otras palabras, inicia sus sesiones.
        session.setAttribute("user_id", user.getId());
        System.out.println("---------------- LOGIN ------------------");
        return "redirect:/home";
    }

    // //// REGISTER USER /////////////////////////
    // ///////////////////////////////////////////
    // ---------- view --------------------------
    @GetMapping("/register")
    public String registerView(Model model, HttpSession session) {
        // Enlazar objetos User y LoginUser vacíos al JSP
        // para capturar la entrada del formulariocopy
        System.out.println("*************** INDEX ************");
        System.out.println("login en session: " + session.getAttribute("user_id"));
        if (session.getAttribute("user_id") != null) { // If user is in session
            return "redirect:/home"; // re-route to dashboard
        }
        model.addAttribute("newUser", new User());
        System.out.println("------------- INDEX --------------");
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
                           HttpSession session) {
        System.out.println("*************** POST REGISTER ************");

        // PARA HACER después: llamar a un método de registro en el servicio
        // ¡para hacer algunas validaciones adicionales y crear un nuevo usuario!

        userService.register(newUser, result);

        if (result.hasErrors()) {
            // Asegúrate de enviar el LoginUser vacío antes
            // de volver a renderizar la página
            return "register";
        }

        // ¡Sin errores!
        // PARA HACER después: Almacena sus ID de la base de datos en sesión,
        // en otras palabras, inicia sus sesiones.
        session.setAttribute("user_id", newUser.getId());
        System.out.println("------------------ POST REGISTER --------------------");
        return "redirect:/home";
    }
    // ----------------------- logout ---------------------------
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("************** LOGOUT *********** ");
        session.invalidate();
        System.out.println("----------------- LOGOUT ---------- ");
        return "redirect:/"; // then redirect to index
    }
}
