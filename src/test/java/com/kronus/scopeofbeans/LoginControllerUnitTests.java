package com.kronus.scopeofbeans;

import com.kronus.scopeofbeans.controllers.LoginController;
import com.kronus.scopeofbeans.model.LoginProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoginControllerUnitTests {
    @Mock
    private Model model;

    @Mock
    private LoginProcessor loginProcessor;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginPostLoginSucceedsTest() {
        given(loginProcessor.isLogin())
                .willReturn(true);

        String result = loginController.postLogin("username", "password", model);

        assertEquals("redirect:/home", result);

        verify(model)
                .addAttribute("message", "You are now logged in");
    }

    @Test
    public void loginPostLoginFailedTest() {
        given(loginProcessor.isLogin())
                .willReturn(false);

        String result = loginController.postLogin("username", "password", model);

        assertEquals("login.html", result);
    }
}