package com.robot.notesapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface notesDao {
    @Query("SELECT * FROM notes")
    List<notes> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(notes note);

    @Delete
    void delete(notes note);

}
