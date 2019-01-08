package com.example.mat.novusnoteapp.updatenote;

import android.support.v4.app.Fragment;

import com.example.mat.novusnoteapp.note.entity.Note;

public interface UpdateNoteInteractor {

    interface onNoteUpdatedListener {
        void onUpdateSuccess(String message);

        void onUpdateError(String error);
    }

    void destroyNoteUpdateListener();

    void doUpdateNote(Fragment fragment, Note note);
}
