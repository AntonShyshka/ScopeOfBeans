package com.kronus.scopeofbeans.model;

import com.kronus.scopeofbeans.services.LoggedUserManagementService;
import com.kronus.scopeofbeans.services.LoginCountService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {
    private String username;
    private String password;

    private LoggedUserManagementService loggedUserManagementService;
    private LoginCountService loginCountService;

    LoginProcessor(LoggedUserManagementService loggedUserManagementService,
                   LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    public boolean isLogin() {
        String username = this.username;
        String password = this.password;

        loginCountService.counter();

        if ("admin".equals(username) && "12345678".equals(password)) {
            loggedUserManagementService.setUsername(username);
            return true;
        } else {
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
