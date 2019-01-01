package com.example.mat.novusnoteapp.accountrecovery.ui;

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
import com.example.mat.novusnoteapp.accountrecovery.RecoveryPresenter;
import com.example.mat.novusnoteapp.accountrecovery.RecoveryPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecoveryFragment extends Fragment implements RecoveryView {

    @BindView(R.id.retrievemailET)      EditText retrievemailET;
    @BindView(R.id.progressBar)         ProgressBar progressBar;
    @BindView(R.id.retrievepasswordBtn) Button retrievepassBtn;
    @BindView(R.id.cancelBtn)           Button cancelBtn;

    private RecoveryPresenter recoveryPresenter;

    public RecoveryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recovery, container, false);
        ButterKnife.bind(this, v);

        recoveryPresenter = new RecoveryPresenterImpl(this);

        return v;
    }

    @OnClick(R.id.retrievepasswordBtn)
    public void handleRetrievalButton(){
        recoveryPresenter.recoveryAccount(retrievemailET.getText().toString());
    }

    @OnClick(R.id.cancelBtn)
    public void handleReturnButton(){
        getFragmentManager().popBackStack();
    }

    @Override
    public void onRecoverySuccess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecoveryError(String error) {
        retrievemailET.setText("");
        String msgError = String.format(getString(R.string.recovery_error_message_retrieval), error);
        retrievemailET.requestFocus();
        retrievemailET.setError(msgError);
    }

    @Override
    public void navigateToLoginScreen() {
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
        retrievepassBtn.setEnabled(option);
        cancelBtn.setEnabled(option);
    }
}
