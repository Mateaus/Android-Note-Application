package com.example.mat.novusnoteapp.note;


public interface NoteListPresenter {

    void subscribeForNoteEvents();

    void signOff();

    void onDestroy();
}
