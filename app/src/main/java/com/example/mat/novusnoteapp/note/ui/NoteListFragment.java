package com.example.mat.novusnoteapp.note.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mat.novusnoteapp.MainActivity;
import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.addnote.AddNoteFragment;
import com.example.mat.novusnoteapp.note.NoteListPresenter;
import com.example.mat.novusnoteapp.note.NoteListPresenterImpl;
import com.example.mat.novusnoteapp.note.adapters.NoteListAdapter;
import com.example.mat.novusnoteapp.note.entity.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListFragment extends Fragment implements NoteListView, OnItemClickListener{

    @BindView(R.id.recyclerViewNotes)   RecyclerView recyclerView;
    @BindView(R.id.toolbar)             Toolbar toolbar;

    private NoteListAdapter noteListAdapter;
    private NoteListPresenter noteListPresenter;

    public NoteListFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_list_notes, container, false);
        ButterKnife.bind(this, v);

        noteListPresenter = new NoteListPresenterImpl(this);
        noteListPresenter.subscribeForNoteEvents();

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        setupAdapter();
        setupRecyclerView();


        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            noteListPresenter.signOff();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Clears up the current view so when we return from either reading or updating
     * a note, it recreates it from 0 and doesn't overwrite over the previous existing one.
     */
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        noteListPresenter.onDestroy();
    }

    @OnClick(R.id.fab)
    public void addNote(){
        AddNoteFragment frag = new AddNoteFragment();
        frag.show(getFragmentManager(), "");
    }

    private void setupAdapter(){
        noteListAdapter = new NoteListAdapter(new ArrayList<Note>(), this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(noteListAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(noteListAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
    public void onItemClick(Note note) {
        NoteListActivity noteListActivity = (NoteListActivity)getActivity();
        noteListActivity.loadUpdateNoteScreen(note);
    }

    @Override
    public void onItemLongClick(Note note) {
        NoteListActivity noteListActivity = (NoteListActivity)getActivity();
        noteListActivity.loadReadNoteScreen(note);
    }

    @Override
    public void onDestroy(){
        // Logs us out when this activity is destroyed.
        super.onDestroy();
        noteListPresenter.signOff();
    }
}
