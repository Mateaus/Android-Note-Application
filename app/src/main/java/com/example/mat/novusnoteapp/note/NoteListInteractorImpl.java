package com.example.mat.novusnoteapp.note;

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
    public void doSignOff() {
        noteListRepository.signOff();
    }
}
