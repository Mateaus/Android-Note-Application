package com.example.mat.novusnoteapp.register.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.register.RegisterPresenter;
import com.example.mat.novusnoteapp.register.RegisterPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements RegisterView{

    @BindView(R.id.registerBtn)     Button registerBtn;
    @BindView(R.id.backBtn)         Button backBtn;
    @BindView(R.id.emailregET)      EditText emailregET;
    @BindView(R.id.passwordregET)   EditText passregET;
    @BindView(R.id.progressBar)     ProgressBar progressBar;

    private RegisterPresenter registerPresenter;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, v);

        registerPresenter = new RegisterPresenterImpl(this);
        return v;
    }

    @OnClick(R.id.registerBtn)
    public void handleRegisterButton(){
        registerPresenter.registerNewUser(emailregET.getText().toString(),
                passregET.getText().toString());

    }

    @OnClick(R.id.backBtn)
    public void handleReturnButton(){
        getFragmentManager().popBackStack();
    }

    @Override
    public void onRegistrationSuccess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegistrationError(String error) {
        passregET.setText("");
        String msgError = String.format(getString(R.string.register_error_message_creation), error);
        passregET.requestFocus();
        passregET.setError(msgError);
    }

    @Override
    public void navigateToLoginScreen(){
        getFragmentManager().popBackStack();
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
        registerBtn.setEnabled(option);
        backBtn.setEnabled(option);
    }
}
