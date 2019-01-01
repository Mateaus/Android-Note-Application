package com.example.mat.novusnoteapp.readnote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mat.novusnoteapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class ReadNoteFragment extends Fragment {

    @BindView(R.id.categoryTV)      TextView categoryTV;
    @BindView(R.id.subjectTV)       TextView subjectTV;
    @BindView(R.id.descriptionTV)   TextView descriptionTV;

    public ReadNoteFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_read_note, container, false);
        ButterKnife.bind(this, v);

        setupNote();

        return v;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        getFragmentManager().popBackStack();
    }

    private void setupNote(){
        String category = getArguments().getString("category");
        String subject = getArguments().getString("subject");
        String description = getArguments().getString("description");

        categoryTV.setText(category);
        subjectTV.setText(subject);
        descriptionTV.setText(description);
    }

}
