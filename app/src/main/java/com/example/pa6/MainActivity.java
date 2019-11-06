/**
 * This app is a note taking app allowing user's to create, edit, and delete notes from their phone
 * CPSC 312-01, Fall 2019
 * Programming Assignment #6
 * No sources to cite.
 *
 * @author Jerry Xue & Ethan Gruis
 * @version v1.05 11/06/19
 */
package com.example.pa6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Handles the logic behind MainActivity
 *
 */
public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private Button noteButton;
    private ListView listView;
    private List<Note> NoteList;
    private ArrayAdapter<Note> arrayAdapter;
    final private Context context = this;

    /**
     * Handles logic for when MainActivity is first launched
     *
     * @param savedInstanceState handles savedInstanceState if app is paused(?)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, NoteActivity.class);
        MainActivityLayout mainActivityLayout = new MainActivityLayout(this);

        noteButton = mainActivityLayout.returnButton();
        noteButton.setOnClickListener(new NoteButtonClicker());

        NoteList = new ArrayList<>();

        // set up an array adapter to be the middleman between our data source
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, NoteList);
        listView = mainActivityLayout.returnListView();
        listView.setOnItemClickListener(new listViewItemListener());
        listView.setOnItemLongClickListener(new listViewLongClickListener());
        listView.setAdapter(arrayAdapter);

        setContentView(mainActivityLayout);
    }

    /**
     * Custom clicker class for when new note button is clicked
     *
     */
    private class NoteButtonClicker implements View.OnClickListener {

        /**
         * Handles launching activity when button is clicked
         *
         * @param v item (presumably a button) being clicked
         * @return new Activity launched
         */
        @Override
        public void onClick(View v) {

            int noteActivityRequest = 0;
            intent.putExtra("type", "regular");
            startActivityForResult(intent, noteActivityRequest);
        }
    }

    /**
     * Custom Listener class for when a Note object is clicked
     *
     */
    private class listViewItemListener implements AdapterView.OnItemClickListener {

        /**
         * Handles editing notes when a note is clicked
         *
         * @param parent adapter item connecting interface to backend
         * @param view item (note) being clicked
         * @param position number indicating location of note in the ListView
         * @param id number identifier of item being clicked (?)
         * @return new Activity launched
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int editNoteActivityRequest = 1;
            String content;
            String title;
            int spinner;
            int notePosition;

            Note noteSelectedItem = (Note) parent.getItemAtPosition(position);
            title = noteSelectedItem.getTitle();
            content = noteSelectedItem.getContent();
            spinner = noteSelectedItem.getType();
            notePosition = NoteList.indexOf(noteSelectedItem);

            intent.putExtra("spinner", spinner);
            intent.putExtra("content", content);
            intent.putExtra("title", title);
            intent.putExtra("position",notePosition);
            intent.putExtra("type", "edit");

            startActivityForResult(intent, editNoteActivityRequest);
        }
    }

    /**
     * Custom Listener class for when a Note object is long clicked
     *
     */
    private class listViewLongClickListener implements AdapterView.OnItemLongClickListener {

        /**
         * Handles removing notes when a note is long clicked
         *
         * @param parent adapter item connecting interface to backend
         * @param view item (note) being clicked
         * @param position number indicating location of note in the ListView
         * @param id number identifier of item being clicked (?)
         * @return AlertDialog created
         */
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            //Toast.makeText(getApplicationContext(),"Loooooong Click",Toast.LENGTH_SHORT).show();
            final Note noteSelectedItem = (Note) parent.getItemAtPosition(position);
            String title = noteSelectedItem.getTitle();
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            alertBuilder.setTitle("Delete a Note");

            alertBuilder
                    .setMessage(getResources().getString(R.string.delete_note) + title + getResources().getString(R.string.note))
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.yes),new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            NoteList.remove(position);
                            arrayAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.no),new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertBuilder.create();

            alertDialog.show();
            return true;
        }
    }

    /**
     * Handles launching activity when button is clicked
     *
     * @param requestCode number indicating whether activity was launched as create new Note or edit Note
     * @param resultCode number indicating what result was reached in previous activity
     * @param data intent loaded with data
     * @return data from previous activity unpacked and utilized within MainActivity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String noteItemTitle;
        String noteItemContent;
        int noteItemSpinner;

        noteItemTitle = data.getStringExtra("title");
        noteItemContent = data.getStringExtra("content");
        noteItemSpinner = data.getIntExtra("spinnerLocation",-1);

        if (requestCode == 0) {
            // Comes in here if requestcode was for creating new item
            NoteList.add(new Note(noteItemTitle, noteItemContent, noteItemSpinner));
        } if(requestCode == 1){
            // Comes in here if requestcode was for editing existing Note Item
            int indexPosition = data.getIntExtra("notePosition",-1);
            NoteList.get(indexPosition).setTitle(noteItemTitle);
            NoteList.get(indexPosition).setContent(noteItemContent);
            NoteList.get(indexPosition).setType(noteItemSpinner);
        }
        arrayAdapter.notifyDataSetChanged();
    }
}
