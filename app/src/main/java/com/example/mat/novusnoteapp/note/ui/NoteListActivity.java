package com.example.mat.novusnoteapp.note.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.example.mat.novusnoteapp.readnote.ReadNoteFragment;
import com.example.mat.novusnoteapp.updatenote.ui.UpdateNoteFragment;

import butterknife.ButterKnife;

public class NoteListActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);

        fm = getSupportFragmentManager();
        Fragment fragment = (Fragment) fm.findFragmentById(R.id.note_container);

        if (fragment == null) {
            NoteListFragment noteListFragment = new NoteListFragment();
            fm.beginTransaction().add(R.id.note_container, noteListFragment).commit();
        }
    }

    public void loadReadNoteScreen(Note note) {
        ReadNoteFragment readNoteFragment = new ReadNoteFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title", note.getTitle());
        bundle.putString("description", note.getDescription());
        readNoteFragment.setArguments(bundle);

        fm.beginTransaction().replace(R.id.note_container, readNoteFragment).addToBackStack(null).commit();
    }

    public void loadUpdateNoteScreen(Note note) {
        UpdateNoteFragment updateNoteFragment = new UpdateNoteFragment();

        Bundle bundle = new Bundle();
        bundle.putString("id", note.getId());
        bundle.putString("title", note.getTitle());
        bundle.putString("description", note.getDescription());
        updateNoteFragment.setArguments(bundle);

        fm.beginTransaction().replace(R.id.note_container, updateNoteFragment).addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notelist, menu);
        return true;
    }
}
