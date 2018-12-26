package com.example.mat.novusnoteapp.note.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.note.NoteListPresenter;
import com.example.mat.novusnoteapp.note.adapters.NoteListAdapter;

import butterknife.ButterKnife;

public class NoteListActivity extends AppCompatActivity {

    /*@BindView(R.id.recyclerViewNotes)
    RecyclerView recyclerView;*/

    private NoteListAdapter noteListAdapter;
    private NoteListPresenter noteListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);

        FragmentManager fm = getSupportFragmentManager();
        NoteListFragment noteListFragment = (NoteListFragment)fm.findFragmentById(R.id.note_container);

        if(noteListFragment == null){
            noteListFragment = new NoteListFragment();
            fm.beginTransaction().add(R.id.note_container, noteListFragment).commit();
        }
/*
        noteListPresenter = new NoteListPresenterImpl(this);
        noteListPresenter.subscribeForNoteEvents();
        setupAdapter();
        setupRecyclerView();*/
    }

    /*@OnClick(R.id.fab)
    public void addContact(){
        AddNoteFragment frag = new AddNoteFragment();
        frag.show(getSupportFragmentManager(), "");
    }

    private void setupAdapter(){
        noteListAdapter = new NoteListAdapter(new ArrayList<Note>(), this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteListAdapter);
    }

    @Override
    public void onNoteAdded(Note note) {
        noteListAdapter.add(note);
    }

    @Override
    public void onNoteChanged(Note note) {
        noteListAdapter.update(note);
    }

    @Override
    public void onNoteRemoved(Note note) {
        noteListAdapter.remove(note);
    }

    @Override
    public void onItemClick(Note note) {/*
        /**
         * Make new fragment here passing the data
         */
/*
        FragmentManager fm = getSupportFragmentManager();
        ReadNoteFragment frag = (ReadNoteFragment) fm.findFragmentById(R.id.note_container);

        if(frag == null) {
            ReadNoteFragment readNoteFragment = new ReadNoteFragment();

            Bundle bundle = new Bundle();
            bundle.putString("category", note.getCategory());
            bundle.putString("subject", note.getSubject());
            bundle.putString("description", note.getDescription());

            readNoteFragment.setArguments(bundle);
            fm.beginTransaction().add(R.id.note_container, readNoteFragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onItemLongClick(Note note) {
       noteListPresenter.removeNote(note);
    }

    @Override
    protected void onDestroy(){
        // Logs us out when this activity is destroyed.
        noteListPresenter.signOff();
        super.onDestroy();
    }*/
}
