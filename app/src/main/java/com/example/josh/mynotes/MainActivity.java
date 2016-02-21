package com.example.josh.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    DBHandler dbHandler;
    NoteLists noteListsAdapter;
    ListView notesListView;

    //
    // Builds the app and starts mainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //These four lines of code set up the dbHandler to display
        // all DB entries in the myNotesListView.
        //It does this by calling the dbHandler method getNotes which returns
        //a cursor for the notes' data table.
        dbHandler = new DBHandler(this, null);

        notesListView = (ListView) this.findViewById(R.id.myNotesListView);

        noteListsAdapter = new NoteLists(this, dbHandler.getNotes(), 0);

        notesListView.setAdapter(noteListsAdapter);

        //This line of code displays the number of notes in the active DB by
        //printing the return of the dbHandler method getNumberOfNotes().
        toolbar.setSubtitle("Notes: " + dbHandler.getNumberOfNotes());

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this, ViewNote.class);
                intent.putExtra("_id", id);
                startActivity(intent);
            }
        });

    }



    //This begins the create note activity
    public void openCreateNote(View view){
        intent = new Intent(this, AddNote.class);
        startActivity(intent);
    }

    //populates overflow menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //deteremines which option was selected from the menu
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