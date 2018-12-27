package com.example.mat.novusnoteapp.note.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mat.novusnoteapp.R;

import butterknife.ButterKnife;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);

        FragmentManager fm = getSupportFragmentManager();
        NoteListFragment noteListFragment = (NoteListFragment) fm.findFragmentById(R.id.note_container);

        if (noteListFragment == null) {
            noteListFragment = new NoteListFragment();
            fm.beginTransaction().add(R.id.note_container, noteListFragment).commit();
        }
    }

}
