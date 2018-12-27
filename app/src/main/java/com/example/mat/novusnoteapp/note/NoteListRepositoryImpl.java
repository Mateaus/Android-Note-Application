package com.example.mat.novusnoteapp.note;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mat.novusnoteapp.domain.FireBaseHelper;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoteListRepositoryImpl implements NoteListRepository {

    private FireBaseHelper helper;
    private NoteListInteractor.onNoteListFinishedListener listener;

    public NoteListRepositoryImpl(NoteListInteractor.onNoteListFinishedListener listener){
        this.helper = new FireBaseHelper();
        this.listener = listener;
    }

    @Override
    public void subscribeForNoteEvents() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail().replace(".","_");
        DatabaseReference myRef = database.getReference().child("notes").child(email);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Note note = dataSnapshot.getValue(Note.class);
                listener.onNoteAdded(note);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Note note = dataSnapshot.getValue(Note.class);
                listener.onNoteUpdated(note);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Note note = dataSnapshot.getValue(Note.class);
                listener.onNoteRemoved(note);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void signOff() {
        FirebaseAuth.getInstance().signOut();
    }
}
