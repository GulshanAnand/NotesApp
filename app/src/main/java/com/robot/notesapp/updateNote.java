package com.robot.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class updateNote extends AppCompatActivity {

    EditText editTitle;
    EditText editNote;
    Button updateButton;

    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        editTitle = findViewById(R.id.edit_title_tv);
        editNote = findViewById(R.id.edit_note_tv);
        updateButton = findViewById(R.id.update_button);

        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String note = intent.getStringExtra("NOTE");
        id = intent.getIntExtra("ID", 0);
        editTitle.setText(title);
        editNote.setText(note);

        editNote.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(updateNote.this.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        //for update button:
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upTitle = editTitle.getText().toString();
                String upNote = editNote.getText().toString();
                MainActivity.db.notesDao().updateNotes(upTitle, upNote, id);
                List<notes> allnotes = MainActivity.db.notesDao().getAllNotes();
                MainActivity.adapter = new notesAdapter(allnotes);
                MainActivity.recyclerView.setAdapter(MainActivity.adapter);
                hidekeyboard(view);
            }
        });
    }
    private void hidekeyboard(View v) {
        editNote.clearFocus();
        editTitle.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}