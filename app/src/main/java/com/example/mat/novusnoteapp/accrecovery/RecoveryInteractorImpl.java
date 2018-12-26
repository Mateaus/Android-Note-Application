package com.example.mat.novusnoteapp.accrecovery;

public class RecoveryInteractorImpl implements RecoveryInteractor {

    private RecoveryRepository recoveryRepository;

    public RecoveryInteractorImpl(RecoveryInteractor.onRecoveryFinishedListener listener){
        this.recoveryRepository = new RecoveryRepositoryImpl(listener);
    }

    @Override
    public void doRecoveryAccount(String email) {
        recoveryRepository.recoverAccount(email);
    }
}
