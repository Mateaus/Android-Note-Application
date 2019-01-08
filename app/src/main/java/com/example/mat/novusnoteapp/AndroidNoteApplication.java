package com.example.mat.novusnoteapp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class AndroidNoteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
