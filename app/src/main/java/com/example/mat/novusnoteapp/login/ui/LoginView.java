package com.example.mat.novusnoteapp.login.ui;

public interface LoginView {
    void onLoginSuccess(String message);
    void onLoginError(String error);
    void navigateToAppScreen();

    void enableInputs();
    void disableInputs();

    void showProgress();
    void hideProgress();
}
