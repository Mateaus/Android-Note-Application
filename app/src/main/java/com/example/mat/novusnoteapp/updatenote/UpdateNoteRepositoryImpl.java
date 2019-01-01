package com.example.mat.novusnoteapp.updatenote;

import android.support.v4.app.Fragment;

import com.example.mat.novusnoteapp.domain.FireBaseHelper;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

public class UpdateNoteRepositoryImpl implements UpdateNoteRepository {

    private UpdateNoteInteractor.onNoteUpdatedListener listener;
    private FireBaseHelper fireBaseHelper;

    public UpdateNoteRepositoryImpl(UpdateNoteInteractor.onNoteUpdatedListener listener){
        this.listener = listener;
        this.fireBaseHelper = new FireBaseHelper();
    }

    @Override
    public void updateNote(Fragment fragment, Note updatedNote) {
        try{
            String emailRef = fireBaseHelper.getAuthUserEmail().replace(".","_");

            DatabaseReference myRef = fireBaseHelper.getNotesReference(emailRef);
            int updateFlag = 0;

            if(!getCategoryFromBundle(fragment).equals(updatedNote.getCategory())){
                myRef.child(getIdFromBundle(fragment)).child("category").setValue(updatedNote.getCategory());
                updateFlag = 1;
            }
            if(!getSubjectFromBundle(fragment).equals(updatedNote.getSubject())){
                myRef.child(getIdFromBundle(fragment)).child("subject").setValue(updatedNote.getSubject());
                updateFlag = 1;
            }
            if(!getDescriptionFromBundle(fragment).equals(updatedNote.getDescription())){
                myRef.child(getIdFromBundle(fragment)).child("description").setValue(updatedNote.getDescription());
                updateFlag = 1;
            }

            if(updateFlag == 1){
                listener.onUpdateSuccess("Successfully Updated Note");
                fragment.getFragmentManager().popBackStack();
            } else {
                fragment.getFragmentManager().popBackStack();
            }
        } catch(DatabaseException e){
            listener.onUpdateError(e.getMessage());
        } catch (Exception e){
            listener.onUpdateError("Error Updating Note");
        }
    }

    @Override
    public void destroyNoteUpdateListener() {
        this.listener = null;
    }

    private String getIdFromBundle(Fragment fragment){
        return fragment.getArguments().getString("id");
    }

    private String getCategoryFromBundle(Fragment fragment){
        return fragment.getArguments().getString("category");
    }

    private String getSubjectFromBundle(Fragment fragment){
        return fragment.getArguments().getString("subject");
    }

    private String getDescriptionFromBundle(Fragment fragment){
        return fragment.getArguments().getString("description");
    }
}
