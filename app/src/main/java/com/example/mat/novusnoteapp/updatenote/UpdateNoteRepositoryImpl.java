package com.example.mat.novusnoteapp.updatenote;

import android.support.v4.app.Fragment;

import com.example.mat.novusnoteapp.domain.FireBaseHelper;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateNoteRepositoryImpl implements UpdateNoteRepository {

    private UpdateNoteInteractor.onNoteUpdatedListener listener;
    private FireBaseHelper fireBaseHelper;

    public UpdateNoteRepositoryImpl(UpdateNoteInteractor.onNoteUpdatedListener listener) {
        this.listener = listener;
        this.fireBaseHelper = new FireBaseHelper();
    }

    @Override
    public void updateNote(Fragment fragment, Note updatedNote) {
        try {
            String emailRef = fireBaseHelper.getAuthUserEmail().replace(".", "_");

            DatabaseReference myRef = fireBaseHelper.getUserNoteReference(emailRef);
            int updateFlag = 0;

            if (!getCategoryFromBundle(fragment).equals(updatedNote.getTitle())) {
                myRef.child(getIdFromBundle(fragment)).child("title").setValue(updatedNote.getTitle());
                updateFlag = 1;
            }
            if (!getSubjectFromBundle(fragment).equals(updatedNote.getDescription())) {
                myRef.child(getIdFromBundle(fragment)).child("description").setValue(updatedNote.getDescription());
                updateFlag = 1;
            }

            if (updateFlag == 1) {
                listener.onUpdateSuccess("Successfully Updated Note");
                myRef.child(getIdFromBundle(fragment)).child("date").setValue(getCurrentDate());
                fragment.getFragmentManager().popBackStack();
            } else {
                fragment.getFragmentManager().popBackStack();
            }
        } catch (DatabaseException e) {
            listener.onUpdateError(e.getMessage());
        } catch (Exception e) {
            listener.onUpdateError("Error Updating Note");
        }
    }

    @Override
    public void destroyNoteUpdateListener() {
        this.listener = null;
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("M/d/y");
        DateFormat hourFormat = new SimpleDateFormat("h:mm a");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String time = hourFormat.format(Calendar.getInstance().getTime());
        String calendar = "\t" + time + "\n" + date;
        return calendar;
    }

    private String getIdFromBundle(Fragment fragment) {
        return fragment.getArguments().getString("id");
    }

    private String getCategoryFromBundle(Fragment fragment) {
        return fragment.getArguments().getString("title");
    }

    private String getSubjectFromBundle(Fragment fragment) {
        return fragment.getArguments().getString("description");
    }
}
