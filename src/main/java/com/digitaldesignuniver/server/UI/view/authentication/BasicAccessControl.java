package com.digitaldesignuniver.server.UI.view.authentication;

//import com.digitaldesignuniver.server.app.TaxiElector;
import com.digitaldesignuniver.server.app.CallCentre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BasicAccessControl implements AccessControl {

    @Autowired
    CallCentre callCentre;


    @Override
    public boolean signIn(String username, String password) {
        if(callCentre.isRegistrated(username,password)){
            CurrentUser.set(username);
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }



}
