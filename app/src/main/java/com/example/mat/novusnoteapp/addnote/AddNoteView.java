package com.example.mat.novusnoteapp.addnote;

public interface AddNoteView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void noteAdded();
    void noteNotAdded();
}
