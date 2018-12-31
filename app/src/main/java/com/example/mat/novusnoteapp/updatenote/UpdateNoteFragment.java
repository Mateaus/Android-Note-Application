package com.example.mat.novusnoteapp.updatenote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.domain.FireBaseHelper;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateNoteFragment extends Fragment {

    @BindView(R.id.acceptBtn)       ImageButton acceptBtn;
    @BindView(R.id.categoryET)      EditText categoryET;
    @BindView(R.id.subjectET)       EditText subjectET;
    @BindView(R.id.descriptionET)   EditText descriptionET;

    private FireBaseHelper fireBaseHelper;

    public UpdateNoteFragment() {
        // Required empty public constructor
        fireBaseHelper = new FireBaseHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_update_note, container, false);
        ButterKnife.bind(this, v);

        String category = getArguments().getString("category");
        String subject = getArguments().getString("subject");
        String description = getArguments().getString("description");

        categoryET.setText(category);
        subjectET.setText(subject);
        descriptionET.setText(description);


        return v;
    }

    @OnClick(R.id.acceptBtn)
    public void acceptButtonHandle(){
        String emailRef = fireBaseHelper.getAuthUserEmail().replace(".","_");

        DatabaseReference myRef = fireBaseHelper.getNotesReference(emailRef);
        myRef.child(getArguments().getString("id")).child("category").setValue(categoryET.getText().toString());
        myRef.child(getArguments().getString("id")).child("subject").setValue(subjectET.getText().toString());
        myRef.child(getArguments().getString("id")).child("description").setValue(descriptionET.getText().toString());

        getFragmentManager().popBackStack();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        getFragmentManager().popBackStack();
    }

}
