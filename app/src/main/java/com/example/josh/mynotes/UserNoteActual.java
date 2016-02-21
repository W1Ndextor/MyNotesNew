package com.example.josh.mynotes;

import java.util.Date;

/**
 * Created by Josh on 2/20/2016.
 */
public class UserNoteActual {
    private int id;
    private String title;
    private String note;
    private String date;

    public UserNoteActual(int id, String title, String note, String date) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
