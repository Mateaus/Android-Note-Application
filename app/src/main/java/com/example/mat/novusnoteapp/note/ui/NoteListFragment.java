package com.example.mat.novusnoteapp.note.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.addnote.AddNoteFragment;
import com.example.mat.novusnoteapp.note.NoteListPresenter;
import com.example.mat.novusnoteapp.note.NoteListPresenterImpl;
import com.example.mat.novusnoteapp.note.adapters.NoteListAdapter;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.example.mat.novusnoteapp.readnote.ReadNoteFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteListFragment extends Fragment implements NoteListView, OnItemClickListener{

    @BindView(R.id.recyclerViewNotes)   RecyclerView recyclerView;

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
        setupAdapter();
        setupRecyclerView();

        return v;
    }

    @OnClick(R.id.fab)
    public void addContact(){
        AddNoteFragment frag = new AddNoteFragment();
        frag.show(getFragmentManager(), "");
    }

    private void setupAdapter(){
        noteListAdapter = new NoteListAdapter(new ArrayList<Note>(), this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void onItemClick(Note note) {
        Fragment frag = new ReadNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", note.getCategory());
        bundle.putString("subject", note.getSubject());
        bundle.putString("description", note.getDescription());

        frag.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.note_container, frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onItemLongClick(Note note) {
        noteListPresenter.removeNote(note);
    }

    @Override
    public void onDestroy(){
        // Logs us out when this activity is destroyed.
        noteListPresenter.signOff();
        super.onDestroy();
    }
}
