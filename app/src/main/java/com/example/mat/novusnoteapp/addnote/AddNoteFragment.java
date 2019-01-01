package com.example.mat.novusnoteapp.addnote;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mat.novusnoteapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends DialogFragment implements AddNoteView, DialogInterface.OnShowListener{

    @BindView(R.id.editTxtTitle)
    EditText inputTitle;
    @BindView(R.id.editTxtSubject)
    EditText inputSubject;
    @BindView(R.id.editTextDescription)
    EditText inputDescription;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private AddNotePresenter addNotePresenter;

    public AddNoteFragment(){
        this.addNotePresenter = new AddNotePresenterImpl(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addnote_message_title)
                .setPositiveButton(R.string.addnote_message_add,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                .setNegativeButton(R.string.addnote_message_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });

        LayoutInflater i = getActivity().getLayoutInflater();
        View view = i.inflate(R.layout.fragment_add_note_dialog, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }


    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog) getDialog();
        if(dialog != null){

            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addNotePresenter.addNote(inputTitle.getText().toString(),
                            inputSubject.getText().toString(),
                            inputDescription.getText().toString());
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

        }
        addNotePresenter.onShow();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        addNotePresenter.onDestroy();
    }

    @Override
    public void showInput() {
        inputTitle.setVisibility(View.VISIBLE);
        inputSubject.setVisibility(View.VISIBLE);
        inputDescription.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        inputTitle.setVisibility(View.GONE);
        inputSubject.setVisibility(View.GONE);
        inputDescription.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void noteAdded() {
        Toast.makeText(getActivity(), R.string.addnote_message_contactadded,Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void noteNotAdded() {
        inputDescription.setError(getString(R.string.addnote_error_message));
    }
}
