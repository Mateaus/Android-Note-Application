package com.example.mat.novusnoteapp.login;

public interface LoginInteractor {

    interface onLoginFinishedListener{
        void onSignInSuccess(String message);
        void onSignInError(String error);
        void onAuthSuccess();
        void onFailedAuthSession();
    }

    void doSignIn(String email, String password);
    void checkAlreadyAuthenticated();
}
