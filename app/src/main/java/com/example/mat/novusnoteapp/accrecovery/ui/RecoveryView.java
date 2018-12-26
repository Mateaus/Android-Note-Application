package com.example.mat.novusnoteapp.accrecovery.ui;

public interface RecoveryView {
    void onRecoverySuccess(String message);
    void onRecoveryError(String error);
    void navigateToLoginScreen();

    void enableInputs();
    void disableInputs();

    void showProgress();
    void hideProgress();
}
