package com.example.josh.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Josh on 1/30/2016.
 */
public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "assign1notes.db";
    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_LIST_ID = "_id";
    public static final String COLUMN_LIST_TITLE = "list_title";
    public static final String COLUMN_LIST_NOTE = "list_note";
    public static final String COLUMN_LIST_DATE = "list_date";

    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Established DB schema
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NOTES + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LIST_TITLE + " TEXT," +
                COLUMN_LIST_NOTE + " TEXT," +
                COLUMN_LIST_DATE + " TEXT" +
                ");";

        db.execSQL(query);
    }

    //only gets called if oldVersion and newVersion are different
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    //adds created note to database
    public void addNote(String title, String note, String date){

        ContentValues values = new ContentValues();

        values.put(COLUMN_LIST_TITLE, title);
        values.put(COLUMN_LIST_NOTE, note);
        values.put(COLUMN_LIST_DATE, date);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_NOTES, null, values);

        db.close();

    }
    //
    //This method returns a cursor from the DB file.
    //it runs a query for the entire notes database, and returns
    //all of the table data in a Cursor.
    public Cursor getNotes(){

        SQLiteDatabase db = getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_NOTES, null);
    }
    //fdsa
    //This method returns the number of notes in the entire DB.
    //It does this by running a query for all notes in the database table,
    //then it stores the query results in a cursor.
    //From here it calls getCount on the cursor setting numNotesDB equal
    //to the returned int. The returned int gets put into a string and is returned.
    public String getNumberOfNotes(){
        String dbString = "";
        String query = "SELECT * FROM " + TABLE_NOTES;

        SQLiteDatabase  db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        int numNotesDB = c.getCount();
        dbString = String.valueOf(numNotesDB);
        db.close();
        return dbString;
    }
}

