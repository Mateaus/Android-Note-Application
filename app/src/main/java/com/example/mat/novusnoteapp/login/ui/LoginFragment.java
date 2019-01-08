package com.example.mat.novusnoteapp.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.accountrecovery.ui.RecoveryFragment;
import com.example.mat.novusnoteapp.login.LoginPresenter;
import com.example.mat.novusnoteapp.login.LoginPresenterImpl;
import com.example.mat.novusnoteapp.note.ui.NoteListActivity;
import com.example.mat.novusnoteapp.register.ui.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginView{

    @BindView(R.id.emailLayout)     TextInputLayout emailLayout;
    @BindView(R.id.emailET)         EditText emailET;
    @BindView(R.id.passwordET)      EditText passwordET;
    @BindView(R.id.loginBtn)        Button loginBtn;
    @BindView(R.id.registerBtn)     Button registerBtn;
    @BindView(R.id.recoverpassBtn)  Button recoverpassBtn;
    @BindView(R.id.progressBar)     ProgressBar progressBar;

    private LoginPresenter loginPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, v);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.checkForAuthenticatedUser();


        return v;
    }

    @OnClick(R.id.loginBtn)
    public void handleLoginButton(){
        loginPresenter.validateLogin(emailET.getText().toString(),
                passwordET.getText().toString());
    }

    @OnClick(R.id.registerBtn)
    public void handleRegisterButton(){
        Fragment frag = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @OnClick(R.id.recoverpassBtn)
    public void handleRecoveryPassButton(){
        Fragment frag = new RecoveryFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onResume(){
        enableInputs();
        passwordET.setText("");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        loginPresenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginError(String error) {
        passwordET.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin),error);
        passwordET.requestFocus();
        //emailLayout.setError(msgError);
        passwordET.setError(msgError);
    }

    @Override
    public void navigateToAppScreen() {
        Intent intent = new Intent(getContext(), NoteListActivity.class);
        startActivity(intent);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void setInputs(boolean option){
        loginBtn.setEnabled(option);
        registerBtn.setEnabled(option);
        recoverpassBtn.setEnabled(option);
    }
}
