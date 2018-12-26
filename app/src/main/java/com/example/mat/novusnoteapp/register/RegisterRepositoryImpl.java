package com.example.mat.novusnoteapp.register;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterRepositoryImpl implements RegisterRepository {

    private FirebaseAuth mAuth;
    private RegisterInteractor.onRegisterFinishedListener listener;

    public RegisterRepositoryImpl(RegisterInteractor.onRegisterFinishedListener listener){
        this.listener = listener;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(String email, String password) {
        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            listener.onSignUpError("Please fill the blank areas.");
        }
        else if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            if(TextUtils.isEmpty(email)){
                listener.onSignUpError("Email cannot be empty.");
            } else {
                listener.onSignUpError("Password cannot be empty.");
            }
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                listener.onSignUpSuccess("Successfully created an account!");
                            } else {
                                listener.onSignUpError(task.getException().getMessage());
                            }
                        }
                    });
        }
    }
}
