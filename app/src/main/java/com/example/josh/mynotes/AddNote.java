package com.example.josh.mynotes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import android.view.MenuItem;

import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Josh on 1/30/2016.
 */
public class AddNote extends AppCompatActivity{

    Intent intent;
    EditText titleEditText;
    EditText noteEditText;

    Calendar calendar;
    String date = "";
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        noteEditText = (EditText) findViewById(R.id.noteEditText);


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        calendar = Calendar.getInstance();

        date = (dateFormat.format(calendar.getTime()));

        dbHandler = new DBHandler(this, null);

    }

    public void addNote (MenuItem menuItem){

        String title = titleEditText.getText().toString();
        String note = noteEditText.getText().toString();

        if(title.trim().equals("") || note.trim().equals("")){
            Toast.makeText(this, "Please enter a Title and a Note!", Toast.LENGTH_LONG).show();
        }

        else{
            dbHandler.addNote(title, note, date);
            Toast.makeText(this, "Note Created!", Toast.LENGTH_LONG).show();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_home :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_create_note :
                intent = new Intent(this, AddNote.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}

