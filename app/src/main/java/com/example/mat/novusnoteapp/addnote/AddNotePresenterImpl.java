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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNotePresenterImpl implements AddNotePresenter {

    private AddNoteView addNoteView;
    private DatabaseReference dataReference;

    public AddNotePresenterImpl(AddNoteView view) {
        this.addNoteView = view;
        this.dataReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onDestroy() {
        addNoteView = null;
    }

    @Override
    public void addNote(String title, String description) {
        addNoteView.hideInput();
        addNoteView.showProgress();

        addNote(title, description, getCurrentDate());
    }

    private void addNote(final String title, final String description,
                         final String date) {
        final DatabaseReference userReference = getUserReference(getAuthUserEmail());
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    String emailKey = user.getEmail().replace(".", "_");
                    String noteKey = dataReference.child("notes").child(emailKey).push().getKey();
                    dataReference.child("notes").child(emailKey).child(noteKey).setValue(new Note(noteKey, title, description, date));
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

    private DatabaseReference getUserReference(String email) {
        DatabaseReference userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child("users").child(emailKey);
        }
        return userReference;
    }

    public DatabaseReference getNotesReference(String email) {
        return getUserReference(email).child("notes");
    }

    public DatabaseReference getMyNotesReference() {
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

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("M/d/y");
        DateFormat hourFormat = new SimpleDateFormat("h:mm a");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String time = hourFormat.format(Calendar.getInstance().getTime());
        String calendar = "\t" + time + "\n" + date;
        return calendar;
    }
}
