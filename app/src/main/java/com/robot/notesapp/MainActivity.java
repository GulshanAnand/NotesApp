package com.robot.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static notesAdapter adapter;
    public static RecyclerView recyclerView;
    EditText newTitle;
    EditText newNote;
    Button saveButton;
    public static notesDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        newTitle = findViewById(R.id.new_title_tv);
        newNote = findViewById(R.id.new_notes_content);
        saveButton = findViewById(R.id.save_button);

        db = Room.databaseBuilder(getApplicationContext(),
                notesDatabase.class, "notes_db").allowMainThreadQueries().build();
        notesDao noteDao = db.notesDao();
        List<notes> allnotes = noteDao.getAllNotes();
        startAdapter(allnotes);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notes new_note = new notes();
                String tempTitle = newTitle.getText().toString();
                if(tempTitle.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter a Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                new_note.title = tempTitle;
                new_note.note = newNote.getText().toString();
                noteDao.insert(new_note);

                newTitle.setText("");
                newNote.setText("");
                newTitle.clearFocus();
                newNote.clearFocus();
                hidekeyboard(view);
                List<notes> allnotes = noteDao.getAllNotes();
                startAdapter(allnotes);
            }
        });
    }

    private void hidekeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    private void startAdapter(List<notes> allnotes) {
        adapter = new notesAdapter(allnotes);
        recyclerView.setAdapter(adapter);
    }
}