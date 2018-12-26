package com.example.mat.novusnoteapp.addnote;

import android.support.annotation.NonNull;

import com.example.mat.novusnoteapp.login.entity.User;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddNotePresenterImpl implements AddNotePresenter{

    private AddNoteView addNoteView;
    private DatabaseReference dataReference;

    public AddNotePresenterImpl(AddNoteView view){
        this.addNoteView = view;
        this.dataReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {
        addNoteView = null;
    }

    @Override
    public void addNote(String title, String subject, String description) {
        addNoteView.hideInput();
        addNoteView.showProgress();

        addContact(title, subject, description);
    }

    private void addContact(final String title, final String subject,
                            final String description){
        final DatabaseReference userReference = getUserReference(getAuthUserEmail());
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null){
                    String emailKey = user.getEmail().replace(".","_");
                    String noteKey = dataReference.child("notes").child(emailKey).push().getKey();
                    dataReference.child("notes").child(emailKey).child(noteKey).setValue(new Note(noteKey,title,subject,description));
                    addNoteView.noteAdded();
                } else {
                    addNoteView.showInput();
                    addNoteView.hideProgress();
                    addNoteView.noteNotAdded();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child("users").child(emailKey);
        }
        return userReference;
    }

    public DatabaseReference getNotesReference(String email){
        return getUserReference(email).child("notes");
    }

    public DatabaseReference getMyNotesReference(){
        return getNotesReference(getAuthUserEmail());
    }

    public String getAuthUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if (user != null) {
            email = user.getEmail();
        }
        return email;
    }
}
