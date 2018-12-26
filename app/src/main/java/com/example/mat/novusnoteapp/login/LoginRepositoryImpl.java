package com.example.mat.novusnoteapp.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.mat.novusnoteapp.domain.FireBaseHelper;
import com.example.mat.novusnoteapp.login.entity.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class LoginRepositoryImpl implements LoginRepository{

    private FireBaseHelper helper;
    private DatabaseReference myUserReference;
    private LoginInteractor.onLoginFinishedListener listener;

    public LoginRepositoryImpl(LoginInteractor.onLoginFinishedListener listener){
        this.helper = new FireBaseHelper();
        this.myUserReference = helper.getMyUserReference(); // user reference tree
        this.listener = listener;
    }

    @Override
    public void signIn(String email, String password) {
        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            listener.onSignInError("Please fill the blank areas.");
        }
        else if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            if(TextUtils.isEmpty(email)){
                listener.onSignInError("Email cannot be empty.");
            } else {
                listener.onSignInError("Password cannot be empty.");
            }
        } else {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    myUserReference = helper.getMyUserReference();
                    myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            listener.onSignInSuccess("Successfully logged in!");
                            initSignIn(dataSnapshot);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            listener.onSignInError(e.getMessage());
                        }
                    });
        }
    }

    @Override
    public void checkAlreadyAuthenticated() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            myUserReference = helper.getMyUserReference();
            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listener.onAuthSuccess();
                    initSignIn(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    listener.onSignInError(databaseError.getMessage());
                }
            });
        } else {
            listener.onFailedAuthSession();
        }
    }

    private void initSignIn(DataSnapshot snapshot){
        User currentUser = snapshot.getValue(User.class);

        if (currentUser == null) {
            registerNewUserToDatabase();
        }
    }

    private void registerNewUserToDatabase() {
        String email = helper.getAuthUserEmail();
        if (email != null) {
            User currentUser = new User(email);
            myUserReference.setValue(currentUser);
        }
    }
}
