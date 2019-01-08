package com.example.mat.novusnoteapp.updatenote;

import android.support.v4.app.Fragment;

import com.example.mat.novusnoteapp.note.entity.Note;
import com.example.mat.novusnoteapp.updatenote.ui.UpdateNoteView;

public class UpdateNotePresenterImpl implements UpdateNotePresenter, UpdateNoteInteractor.onNoteUpdatedListener {

    private UpdateNoteView updateNoteView;
    private UpdateNoteInteractor updateNoteInteractor;

    public UpdateNotePresenterImpl(UpdateNoteView updateNoteView) {
        this.updateNoteView = updateNoteView;
        this.updateNoteInteractor = new UpdateNoteInteractorImpl(this);
    }

    @Override
    public void updateNote(Fragment fragment, Note note) {
        updateNoteInteractor.doUpdateNote(fragment, note);
    }

    @Override
    public void backScreen() {
        updateNoteView.onBackPressed();
    }

    @Override
    public void onDestroy() {
        this.updateNoteView = null;
        this.updateNoteInteractor.destroyNoteUpdateListener();
    }


    @Override
    public void onUpdateSuccess(String message) {
        if (updateNoteView != null) {
            updateNoteView.onUpdateSuccess(message);
        }
    }

    @Override
    public void onUpdateError(String error) {
        if (updateNoteView != null) {
            updateNoteView.onUpdateError(error);
        }
    }
}
