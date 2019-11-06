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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the logic behind NoteActivity
 *
 */
public class NoteActivity extends AppCompatActivity {
    private Button doneButton;
    private EditText titleET;
    private EditText contentET;
    private Spinner spinner;
    private int notePosition;
    final private Context CONTEXT = this;

    /**
     * Handles logic for when NoteActivity is first launched
     *
     * @param savedInstanceState handles savedInstanceState if app is paused(?)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        NoteActivityLayout noteActivityLayout = new NoteActivityLayout(this);
        doneButton = noteActivityLayout.getDoneButton();
        titleET = noteActivityLayout.returnTitleET();
        contentET = noteActivityLayout.returnContentET();
        spinner = noteActivityLayout.returnSpinner();

        List<String> spinnerArray = new ArrayList<String>();
        populateSpinner(spinnerArray);

        final ArrayAdapter<String> SPINNER_ADAPTER = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,spinnerArray);
        SPINNER_ADAPTER.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(SPINNER_ADAPTER);

        Bundle extras = getIntent().getExtras();
        String activityType = extras.getString("type");
        doneButton.setText(getResources().getString(R.string.done));
        // Checks to see what kind of Activity start it is
        if(activityType.equals("edit")){
            // comes in here if its a edit existing item
            String existingTitle = extras.getString("title");
            String existingContent= extras.getString("content");
            int existingSpinner = extras.getInt("spinner");

            notePosition = extras.getInt("position");;
            titleET.setText(existingTitle);
            contentET.setText(existingContent);
            spinner.setSelection(existingSpinner);
        }

        doneButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles logic for when done button is clicked
             *
             * @param v view of item being clicked
             * @return finishes activity if title is not empty
             */
            @Override
            public void onClick(View v) {
                if(!titleET.getText().toString().matches("")) {
                    Intent output = new Intent();
                    output.putExtra("title", titleET.getText().toString());
                    output.putExtra("content", contentET.getText().toString());
                    output.putExtra("spinnerLocation", spinner.getSelectedItemPosition());
                    output.putExtra("notePosition", notePosition);
                    setResult(RESULT_OK, output);
                    finish();
                } else {
                    Toast.makeText(CONTEXT, getResources().getString(R.string.title_prompt), Toast.LENGTH_SHORT).show();

                }
            }
        });

        setContentView(noteActivityLayout);
    }
    /**
     * populates a spinner with a set of strings from res/values/strings.xml
     *
     * @param spinnerArray a presumably empty list of Strings
     * @return populated SpinnerArray
     */
    public List<String> populateSpinner(List<String> spinnerArray) {
        spinnerArray.add(getResources().getString(R.string.personal));
        spinnerArray.add(getResources().getString(R.string.work));
        spinnerArray.add(getResources().getString(R.string.school));
        spinnerArray.add(getResources().getString(R.string.other));

        return spinnerArray;
    }
}



