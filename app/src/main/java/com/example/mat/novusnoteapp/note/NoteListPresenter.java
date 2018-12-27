package com.example.mat.novusnoteapp.note;

import com.example.mat.novusnoteapp.note.entity.Note;

public interface NoteListPresenter {

    void subscribeForNoteEvents();
    void signOff();
    void onDestroy();
}
