package com.example.mat.novusnoteapp.login;

public interface LoginRepository {

    void signIn(String email, String password);

    void checkAlreadyAuthenticated();
}
