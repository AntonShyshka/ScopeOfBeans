package com.kronus.scopeofbeans.controllers;

import com.kronus.scopeofbeans.model.LoginProcessor;
import com.kronus.scopeofbeans.services.LoggedUserManagementService;
import com.kronus.scopeofbeans.services.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final LoginProcessor loginProcessor;
    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public LoginController(LoginProcessor loginProcessor,
                           LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
        this.loginProcessor = loginProcessor;
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/")
    public String getLogin() {
        return "login.html";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        boolean loggedIn = loginProcessor.isLogin();

        if (loggedIn) {
            model.addAttribute("message", "You are now logged in");
            return "redirect:/home";
        }
        return "login.html";
    }

    @GetMapping("/home")
    public String home(@RequestParam(required = false) String logout,
                       Model model) {

        long count = loginCountService.getCount();

        if (logout != null)
            loggedUserManagementService.setUsername(null);

        String username = loggedUserManagementService.getUsername();

        if (username == null)
            return "redirect:/";

        model.addAttribute("username", username);
        model.addAttribute("count", count);

        return "home.html";
    }
}