package com.example.mat.novusnoteapp.login;

import com.example.mat.novusnoteapp.login.ui.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginFinishedListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl(this);
    }
    
    @Override
    public void checkForAuthenticatedUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.checkAlreadyAuthenticated();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.showProgress();
            loginView.disableInputs();
        }

        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onSignInSuccess(String message) {
        if(loginView != null) {
            loginView.onLoginSuccess(message);
            loginView.hideProgress();
            loginView.navigateToAppScreen();
        }
    }

    @Override
    public void onSignInError(String error) {
        if(loginView != null) {
            loginView.onLoginError(error);
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }

    @Override
    public void onAuthSuccess() {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.navigateToAppScreen();
        }
    }

    @Override
    public void onFailedAuthSession() {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }


}
