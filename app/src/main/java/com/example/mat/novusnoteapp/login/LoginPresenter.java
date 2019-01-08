package com.example.mat.novusnoteapp.login;

public interface LoginPresenter {
    void checkForAuthenticatedUser();

    void validateLogin(String email, String password);

    void onDestroy();
}
