package com.example.mat.novusnoteapp.addnote;

public interface AddNotePresenter {

    void onDestroy();

    void addNote(String title, String description);
}
