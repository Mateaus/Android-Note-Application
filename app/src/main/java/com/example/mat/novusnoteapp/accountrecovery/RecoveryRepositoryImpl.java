package com.example.mat.novusnoteapp.accountrecovery;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryRepositoryImpl implements RecoveryRepository {

    private RecoveryInteractor.onRecoveryFinishedListener listener;
    private FirebaseAuth mAuth;

    public RecoveryRepositoryImpl(RecoveryInteractor.onRecoveryFinishedListener listener) {
        this.listener = listener;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void recoverAccount(String email) {
        if (TextUtils.isEmpty(email)) {
            listener.onRecoveryError("Please fill up the blank space.");
        } else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        listener.onRecoverySuccess("Successfully sent email to reset password!");
                    } else {
                        listener.onRecoveryError(task.getException().getMessage());
                    }
                }
            });
        }
    }
}
