package com.example.josh.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ViewNote extends AppCompatActivity {

    Bundle bundle;
    long id;
    DBHandler dbHandler;
    EditText titleEditText;
    EditText noteEditText;
    EditText dateEditText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bundle = this.getIntent().getExtras();

        id = bundle.getLong("_id");

        dbHandler = new DBHandler(this,null);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        noteEditText = (EditText) findViewById(R.id.noteEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        UserNoteActual userNoteActual = dbHandler.getUserNoteActual((int) id);


        titleEditText.setText(userNoteActual.getTitle().toString());
        noteEditText.setText(userNoteActual.getNote().toString());
        dateEditText.setText(userNoteActual.getDate().toString());

    }

    //public void deleteNote(MenuItem menuItem){

    //}
    public void deleteNote(MenuItem menuItem){

        dbHandler.deleteNote((int) id);

        Toast.makeText(this, "Note Deleted!", Toast.LENGTH_LONG).show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_view_note, menu);
        return true;
    }

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
