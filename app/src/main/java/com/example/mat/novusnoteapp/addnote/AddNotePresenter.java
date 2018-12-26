package com.example.mat.novusnoteapp.addnote;

public interface AddNotePresenter {

    void onShow();
    void onDestroy();

    void addNote(String title, String subject, String description);
}
