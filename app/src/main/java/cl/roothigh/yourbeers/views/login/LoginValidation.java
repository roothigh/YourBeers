package cl.roothigh.yourbeers.views.login;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by SebastianVP on 27-11-2016.
 */

public class LoginValidation {
    private LoginCallback loginCallback;

    public LoginValidation(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }
    public void init () {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            loginCallback.loged();
        } else {
            loginCallback.singUp();
        }
    }
}
