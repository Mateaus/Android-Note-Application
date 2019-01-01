package com.example.mat.novusnoteapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mat.novusnoteapp.login.ui.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentManager fm = getSupportFragmentManager();
        loginFragment = (LoginFragment)fm.findFragmentById(R.id.container_main);

        if(loginFragment == null){
            LoginFragment lf = new LoginFragment();
            fm.beginTransaction().add(R.id.container_main, lf).commit();
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        FirebaseAuth.getInstance().signOut();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loginFragment != null){
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.container_main)).commit();
            loginFragment = null;
        }
    }
}
