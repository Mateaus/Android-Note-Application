package com.example.mat.novusnoteapp.updatenote;

import android.support.v4.app.Fragment;

import com.example.mat.novusnoteapp.note.entity.Note;

public interface UpdateNoteRepository {

    void updateNote(Fragment fragment, Note note);

    void destroyNoteUpdateListener();
}
