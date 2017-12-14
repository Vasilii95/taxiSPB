package com.digitaldesignuniver.server.UI.view.authentication;


import org.springframework.stereotype.Component;

@Component
public interface AccessControl {
    boolean signIn(String username, String password);
    boolean isUserSignedIn();
}
