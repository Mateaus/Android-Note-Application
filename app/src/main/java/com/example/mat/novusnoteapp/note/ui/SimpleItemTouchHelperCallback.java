package com.example.mat.novusnoteapp.note.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.mat.novusnoteapp.domain.FireBaseHelper;
import com.example.mat.novusnoteapp.note.adapters.NoteListAdapter;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.google.firebase.database.DatabaseReference;

import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;
import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private NoteListAdapter mAdapter;
    private FireBaseHelper helper;

    public SimpleItemTouchHelperCallback(NoteListAdapter adapter){
        this.helper = new FireBaseHelper();
        this.mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT| RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Note note = mAdapter.getIdByPosition(viewHolder.getAdapterPosition());

        String email = helper.getAuthUserEmail().replace(".", "_");
        // Gets the note reference, then the reference of the specific note's key and deletes it.
        // Instead of iterating through the entire database and wasting resources.
        // This way we directly access into the note key's we are trying to delete and remove it.
        DatabaseReference noteRef = helper.getUserNoteReference(email).child(note.getId());
        noteRef.removeValue();
    }
}
