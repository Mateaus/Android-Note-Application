package com.example.mat.novusnoteapp.register.ui;

public interface RegisterView {
    void onRegistrationSuccess(String message);
    void onRegistrationError(String error);
    void navigateToLoginScreen();

    void enableInputs();
    void disableInputs();

    void showProgress();
    void hideProgress();
}
