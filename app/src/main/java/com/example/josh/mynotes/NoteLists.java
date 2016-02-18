package com.example.josh.mynotes;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Josh on 2/14/2016.
 */

//The entire purpose of this class is to populate the listView in the main activity.
public class NoteLists extends CursorAdapter {

    //constructor
    public NoteLists (Context context, Cursor cursor, int flags){
        super(context, cursor, flags);
    }

    //This method inflates the li_notes_list layout resource file.//
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.li_notes_list, parent, false);
    }

    //This method populates the actual data for each textview within the listView.
    //This is done by setting text equal to a column value via the cursor.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ((TextView) view.findViewById(R.id.titleTextView)).setText(cursor.getString(cursor.getColumnIndex("list_title")));
        ((TextView) view.findViewById(R.id.dateTextView)).setText(cursor.getString(cursor.getColumnIndex("list_date")));

    }
}
