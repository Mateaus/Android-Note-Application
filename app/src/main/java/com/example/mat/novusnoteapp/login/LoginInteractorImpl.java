package com.example.mat.novusnoteapp.login;

public class LoginInteractorImpl implements LoginInteractor{

    private LoginRepository loginRepository;

    public LoginInteractorImpl(LoginInteractor.onLoginFinishedListener listener){
        this.loginRepository = new LoginRepositoryImpl(listener);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }

    @Override
    public void checkAlreadyAuthenticated() {
        loginRepository.checkAlreadyAuthenticated();
    }
}
