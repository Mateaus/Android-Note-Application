package com.example.mat.novusnoteapp.updatenote;

import android.support.v4.app.Fragment;

import com.example.mat.novusnoteapp.note.entity.Note;

public class UpdateNoteInteractorImpl implements UpdateNoteInteractor {

    private UpdateNoteRepository updateNoteRepository;

    public UpdateNoteInteractorImpl(UpdateNoteInteractor.onNoteUpdatedListener listener) {
        this.updateNoteRepository = new UpdateNoteRepositoryImpl(listener);
    }

    @Override
    public void destroyNoteUpdateListener() {
        updateNoteRepository.destroyNoteUpdateListener();
        this.updateNoteRepository = null;
    }

    @Override
    public void doUpdateNote(Fragment fragment, Note note) {
        updateNoteRepository.updateNote(fragment, note);
    }
}
