package com.example.mat.novusnoteapp.readnote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mat.novusnoteapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class ReadNoteFragment extends Fragment {

    @BindView(R.id.titleTV)         TextView titleTV;
    @BindView(R.id.descriptionTV)   TextView descriptionTV;
    @BindView(R.id.backBtn)         ImageButton backBtn;

    public ReadNoteFragment() {
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
    public void onDestroyView() {
        super.onDestroyView();
        getFragmentManager().popBackStack();
    }

    @OnClick(R.id.backBtn)
    public void backButtonHandler() {
        getFragmentManager().popBackStack();
    }

    private void setupNote() {
        String title = getArguments().getString("title");
        String description = getArguments().getString("description");

        titleTV.setText(title);
        descriptionTV.setText(description);
    }

}
