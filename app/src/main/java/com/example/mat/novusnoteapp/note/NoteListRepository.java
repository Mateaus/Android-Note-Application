package com.example.mat.novusnoteapp.note;

import com.example.mat.novusnoteapp.note.entity.Note;

public interface NoteListRepository {

    void subscribeForNoteEvents();
    void addNote(Note note);
    void updateNote(Note note);
    void removeNote(Note note);
    void signOff();
}
