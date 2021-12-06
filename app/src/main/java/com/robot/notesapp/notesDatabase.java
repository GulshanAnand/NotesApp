package com.robot.notesapp;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {notes.class}, version = 1)
public abstract class notesDatabase extends RoomDatabase {
    public abstract notesDao notesDao();
}
