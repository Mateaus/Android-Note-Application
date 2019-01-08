package com.example.mat.novusnoteapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mat.novusnoteapp.login.ui.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fm = getSupportFragmentManager();
        fragment = (Fragment) fm.findFragmentById(R.id.container_main);

        if (fragment == null) {
            LoginFragment lf = new LoginFragment();
            fm.beginTransaction().add(R.id.container_main, lf).commit();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FirebaseAuth.getInstance().signOut();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
