package com.example.mat.novusnoteapp.register;

import com.example.mat.novusnoteapp.register.ui.RegisterView;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteractor.onRegisterFinishedListener{

    private FirebaseAuth mAuth;
    private RegisterView registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(RegisterView view){
        this.registerView = view;
        this.registerInteractor = new RegisterInteractorImpl(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void registerNewUser(String email, String password) {

        if(registerView != null){
            registerView.showProgress();
            registerView.disableInputs();
        }
        registerInteractor.doSignUp(email, password);
    }

    @Override
    public void onSignUpSuccess(String message) {
        if(registerView != null){
            registerView.onRegistrationSuccess(message);
            registerView.hideProgress();
            registerView.navigateToLoginScreen();
        }
    }

    @Override
    public void onSignUpError(String error) {
        if(registerView != null) {
            registerView.onRegistrationError(error);
            registerView.hideProgress();
            registerView.enableInputs();
        }
    }
}
