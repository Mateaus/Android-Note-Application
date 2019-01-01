package com.example.mat.novusnoteapp.updatenote.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.example.mat.novusnoteapp.updatenote.UpdateNotePresenter;
import com.example.mat.novusnoteapp.updatenote.UpdateNotePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateNoteFragment extends Fragment implements UpdateNoteView{

    @BindView(R.id.acceptBtn)       ImageButton acceptBtn;
    @BindView(R.id.backBtn)         ImageButton backBtn;
    @BindView(R.id.categoryET)      EditText categoryET;
    @BindView(R.id.subjectET)       EditText subjectET;
    @BindView(R.id.descriptionET)   EditText descriptionET;

    private UpdateNotePresenter updateNotePresenter;

    public UpdateNoteFragment() {
        this.updateNotePresenter = new UpdateNotePresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_note, container, false);
        ButterKnife.bind(this, v);

        setupNote();

        return v;
    }

    @OnClick(R.id.acceptBtn)
    public void acceptButtonHandler(){
        updateNotePresenter.updateNote(this,
                new Note(categoryET.getText().toString(),
                        subjectET.getText().toString(),
                        descriptionET.getText().toString()));
    }

    @OnClick(R.id.backBtn)
    public void backButtonHandler(){
        updateNotePresenter.backScreen();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        this.updateNotePresenter.onDestroy();
    }

    @Override
    public void onUpdateSuccess(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }

    private void setupNote(){
        String category = getArguments().getString("category");
        String subject = getArguments().getString("subject");
        String description = getArguments().getString("description");

        categoryET.setText(category);
        subjectET.setText(subject);
        descriptionET.setText(description);
    }
}
