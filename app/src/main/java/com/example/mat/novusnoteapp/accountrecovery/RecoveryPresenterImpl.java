package com.example.mat.novusnoteapp.accountrecovery;

import com.example.mat.novusnoteapp.accountrecovery.ui.RecoveryView;

public class RecoveryPresenterImpl implements RecoveryPresenter, RecoveryInteractor.onRecoveryFinishedListener{

    private RecoveryView recoveryView;
    private RecoveryInteractor recoveryInteractor;

    public RecoveryPresenterImpl(RecoveryView view){
        this.recoveryView = view;
        this.recoveryInteractor = new RecoveryInteractorImpl(this);
    }

    @Override
    public void recoveryAccount(String email) {
        if(recoveryView != null){
            recoveryView.showProgress();
            recoveryView.disableInputs();
        }
        recoveryInteractor.doRecoveryAccount(email);
    }

    @Override
    public void onRecoverySuccess(String message) {
        recoveryView.onRecoverySuccess(message);
        recoveryView.hideProgress();
        recoveryView.navigateToLoginScreen();
    }

    @Override
    public void onRecoveryError(String error) {
        recoveryView.onRecoveryError(error);
        recoveryView.hideProgress();
        recoveryView.enableInputs();
    }
}
