package com.example.mat.novusnoteapp.accountrecovery;

public interface RecoveryInteractor {

    interface onRecoveryFinishedListener {
        void onRecoverySuccess(String message);

        void onRecoveryError(String error);
    }

    void doRecoveryAccount(String email);
}
