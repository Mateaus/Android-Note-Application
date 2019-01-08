package com.example.mat.novusnoteapp.updatenote.ui;

public interface UpdateNoteView {

    void onUpdateSuccess(String message);

    void onUpdateError(String error);

    void onBackPressed();
}
