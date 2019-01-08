package com.example.mat.novusnoteapp.register;

public class RegisterInteractorImpl implements RegisterInteractor {

    private RegisterRepository registerRepository;

    public RegisterInteractorImpl(RegisterInteractor.onRegisterFinishedListener listener) {
        this.registerRepository = new RegisterRepositoryImpl(listener);
    }

    @Override
    public void doSignUp(String email, String password) {
        registerRepository.signUp(email, password);
    }

}
