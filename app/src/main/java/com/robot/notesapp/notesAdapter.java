package com.robot.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class notesAdapter extends RecyclerView.Adapter<notesAdapter.ViewHolder> {

    private List<notes> localDataSet;
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView note;
        CardView card;
        ImageView del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            note = itemView.findViewById(R.id.notes_content);
            card = itemView.findViewById(R.id.card_view);
            del = itemView.findViewById(R.id.delete_note);
            //onclick delete
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int i = getAdapterPosition();
                    int i = getBindingAdapterPosition();
                    notes delNote = localDataSet.get(i);
                    MainActivity.db = Room.databaseBuilder(view.getContext().getApplicationContext(),
                            notesDatabase.class, "notes_db").allowMainThreadQueries().build();
                    MainActivity.db.notesDao().delete(delNote);
                    localDataSet.remove(i);
                    notifyItemRemoved(i);
                }
            });
        }
    }
    public notesAdapter(List<notes> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(localDataSet.get(position).title);
        holder.note.setText(localDataSet.get(position).note);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}
