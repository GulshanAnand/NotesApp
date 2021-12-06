package com.robot.notesapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public String note;
}
