package com.example.mat.novusnoteapp.note.ui;

import com.example.mat.novusnoteapp.note.entity.Note;

public interface NoteListView {
    void onNoteAdded(Note note);
    void onNoteChanged(Note note);
    void onNoteRemoved(Note note);
}
