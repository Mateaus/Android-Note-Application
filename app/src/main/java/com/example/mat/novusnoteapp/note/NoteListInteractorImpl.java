package com.example.mat.novusnoteapp.note;

import com.example.mat.novusnoteapp.note.entity.Note;

public class NoteListInteractorImpl implements NoteListInteractor {

    private NoteListRepository noteListRepository;

    public NoteListInteractorImpl(NoteListInteractor.onNoteListFinishedListener listener){
        this.noteListRepository = new NoteListRepositoryImpl(listener);
    }

    @Override
    public void doSubscribeForNoteEvents() {
        noteListRepository.subscribeForNoteEvents();
    }

    @Override
    public void doAddNote(Note note) {
        noteListRepository.addNote(note);
    }

    @Override
    public void doUpdateNote(Note note) {
        noteListRepository.updateNote(note);
    }

    @Override
    public void doRemoveNote(Note note) {
        noteListRepository.removeNote(note);
    }

    @Override
    public void doSignOff() {
        noteListRepository.signOff();
    }
}
