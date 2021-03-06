package com.example.mat.novusnoteapp.addnote;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
public class AddNoteFragment extends DialogFragment implements AddNoteView, DialogInterface.OnShowListener {

    @BindView(R.id.titleTV)
    EditText titleTV;
    @BindView(R.id.descriptionTV)
    EditText descriptionTV;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private AddNotePresenter addNotePresenter;

    public AddNoteFragment() {
        this.addNotePresenter = new AddNotePresenterImpl(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        LayoutInflater i = getActivity().getLayoutInflater();
        View v = i.inflate(R.layout.add_note_title, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setCustomTitle(v)
                .setPositiveButton(R.string.addnote_message_add,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setNegativeButton(R.string.addnote_message_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

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
        if (dialog != null) {

            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setTextColor(Color.parseColor("#4b2c20"));
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);
            negativeButton.setTextColor(Color.parseColor("#4b2c20"));

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (titleTV.getText().toString().isEmpty() &&
                            descriptionTV.getText().toString().isEmpty()) {
                        dismiss();
                    } else {
                        addNotePresenter.addNote(titleTV.getText().toString(),
                                descriptionTV.getText().toString());
                    }
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addNotePresenter.onDestroy();
    }

    @Override
    public void showInput() {
        titleTV.setVisibility(View.VISIBLE);
        descriptionTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        titleTV.setVisibility(View.GONE);
        descriptionTV.setVisibility(View.GONE);
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
        Toast.makeText(getActivity(), R.string.addnote_message_contactadded, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void noteNotAdded() {
        descriptionTV.setError(getString(R.string.addnote_error_message));
    }
}
