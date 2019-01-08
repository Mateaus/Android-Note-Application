package com.example.mat.novusnoteapp.register;

public interface RegisterInteractor {

    interface onRegisterFinishedListener {
        void onSignUpSuccess(String message);

        void onSignUpError(String error);
    }

    void doSignUp(String email, String password);
}
