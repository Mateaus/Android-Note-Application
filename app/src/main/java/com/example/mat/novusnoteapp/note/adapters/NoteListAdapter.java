package com.example.mat.novusnoteapp.note.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mat.novusnoteapp.R;
import com.example.mat.novusnoteapp.note.entity.Note;
import com.example.mat.novusnoteapp.note.ui.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {

    private List<Note> noteList;
    private OnItemClickListener clickListener;

    public NoteListAdapter(List<Note> noteList,
                           OnItemClickListener clickListener){
        this.noteList = noteList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.setClickListener(note, clickListener);

        String category = note.getCategory();
        String subject = note.getSubject();

        holder.titleTV.setText(category);
        holder.subjectTV.setText(subject);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void add(Note note){
        this.noteList.add(note);
        this.notifyDataSetChanged();
    }

    public void update(Note note){
        int pos = getPositionById(note);
        this.noteList.get(pos).setCategory(note.getCategory());
        this.noteList.get(pos).setSubject(note.getSubject());
        this.noteList.get(pos).setDescription(note.getDescription());
        this.notifyDataSetChanged();
    }

    public void remove(Note note){
        int pos = getPositionById(note);
        this.noteList.remove(pos);
        this.notifyDataSetChanged();
    }

    private int getPositionById(Note note){
        int position = 0;
        for(Note notes: noteList){
            if(note.getId().equals(notes.getId())){
                break;
            }
            position++;
        }

        return position;
    }

    public Note getIdByPosition(int position){
        return noteList.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titleTV)     TextView titleTV;
        @BindView(R.id.subjectTV)   TextView subjectTV;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setClickListener(final Note note,
                                     final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    listener.onItemClick(note);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(note);
                    return false;
                }
            });

        }
    }
}
