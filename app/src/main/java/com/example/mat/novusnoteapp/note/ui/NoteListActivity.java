package com.example.mat.novusnoteapp.note.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.example.mat.novusnoteapp.readnote.ReadNoteFragment;

import butterknife.ButterKnife;

public class NoteListActivity extends AppCompatActivity {

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);

        fm = getSupportFragmentManager();
        NoteListFragment noteListFragment = (NoteListFragment) fm.findFragmentById(R.id.note_container);

        if (noteListFragment == null) {
            noteListFragment = new NoteListFragment();
            fm.beginTransaction().add(R.id.note_container, noteListFragment).commit();
        }
    }

    public void loadReadNoteScreen(Note note){
        ReadNoteFragment readNoteFragment = new ReadNoteFragment();

        Bundle bundle = new Bundle();
        bundle.putString("category", note.getCategory());
        bundle.putString("subject", note.getSubject());
        bundle.putString("description", note.getDescription());

        readNoteFragment.setArguments(bundle);

        fm.beginTransaction().replace(R.id.note_container, readNoteFragment).addToBackStack(null).commit();
    }

}
