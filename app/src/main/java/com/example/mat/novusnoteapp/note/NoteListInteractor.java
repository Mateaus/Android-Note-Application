package com.example.mat.novusnoteapp.note;

import com.example.mat.novusnoteapp.note.entity.Note;

public interface NoteListInteractor {

    interface onNoteListFinishedListener {
        void onNoteAdded(Note note);

        void onNoteUpdated(Note note);

        void onNoteRemoved(Note note);
    }

    void doSubscribeForNoteEvents();

    void doSignOff();
}
