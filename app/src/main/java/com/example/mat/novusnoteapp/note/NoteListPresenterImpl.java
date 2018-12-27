package com.example.mat.novusnoteapp.note;

import com.example.mat.novusnoteapp.note.entity.Note;
import com.example.mat.novusnoteapp.note.ui.NoteListView;

public class NoteListPresenterImpl implements NoteListPresenter, NoteListInteractor.onNoteListFinishedListener{

    private NoteListInteractor noteListInteractor;
    private NoteListView noteListView;

    public NoteListPresenterImpl(NoteListView view){
        this.noteListView = view;
        this.noteListInteractor = new NoteListInteractorImpl(this);
    }

    @Override
    public void onDestroy(){
        noteListView = null;
    }

    @Override
    public void signOff() {
        noteListInteractor.doSignOff();
    }

    @Override
    public void subscribeForNoteEvents() {
        noteListInteractor.doSubscribeForNoteEvents();
    }

    @Override
    public void onNoteAdded(Note note) {
        if(noteListView != null){
            noteListView.onNoteAdded(note);
        }
    }

    @Override
    public void onNoteUpdated(Note note) {
        if(noteListView != null){
            noteListView.onNoteChanged(note);
        }
    }

    @Override
    public void onNoteRemoved(Note note) {
        if(noteListView != null){
            noteListView.onNoteRemoved(note);
        }
    }
}
