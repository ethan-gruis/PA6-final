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

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
/**
 * Builds the layout behind NoteActivity using GridLayout
 *
 */
public class NoteActivityLayout extends GridLayout {
    // fields
    private GridLayout.LayoutParams layoutParams;
    private EditText titleET;
    private EditText contentET;
    private Spinner spinner;
    private Button doneButton;

    public NoteActivityLayout(final Context context) {
        super(context);
        setColumnCount(2);
        // initialization of fields
        titleET = new EditText(context);
        contentET = new EditText(context);
        spinner = new Spinner(context);
        doneButton = new Button(context);

        // setting hint text in EditText views
        titleET.setHint(getResources().getString(R.string.title));
        contentET.setHint(getResources().getString(R.string.contents));

        // Laying out the various children of the GridLayout
        GridLayout.Spec rowSpec = GridLayout.spec(0,1,1);
        GridLayout.Spec colSpec = GridLayout.spec(0,1,4);
        layoutParams = new LayoutParams(rowSpec,colSpec);
        titleET.setLayoutParams(layoutParams);
        rowSpec = GridLayout.spec(0,1,1);
        colSpec = GridLayout.spec(1,1,1);
        layoutParams = new LayoutParams(rowSpec,colSpec);
        spinner.setLayoutParams(layoutParams);
        rowSpec = GridLayout.spec(1,1,15);
        colSpec = GridLayout.spec(0,2,2);
        layoutParams = new LayoutParams(rowSpec,colSpec);
        contentET.setLayoutParams(layoutParams);
        rowSpec = GridLayout.spec(2,1,1);
        colSpec = GridLayout.spec(0,2,1);
        layoutParams = new LayoutParams(rowSpec,colSpec);
        doneButton.setLayoutParams(layoutParams);

        // sets gravity on the text within the content view
        contentET.setGravity(Gravity.TOP|Gravity.LEFT);

        // adds the views into the layout
        addView(titleET);
        addView(spinner);
        addView(contentET);
        addView(doneButton);
    }
    /**
     * returns the doneButton to have its function defined in NoteActivity
     * @return doneButton
     */
    public Button getDoneButton(){
        return doneButton;
    }
    /**
     * returns the returnTitleET to have its function defined in NoteActivity
     * @return titleET
     */
    public EditText returnTitleET(){
        return titleET;
    }
    /**
     * returns the returnContentET to have its function defined in NoteActivity
     * @return contentET
     */
    public EditText returnContentET(){
        return contentET;
    }
    /**
     * returns the returnSpinner to have its function defined in NoteActivity
     * @return spinner
     */
    public Spinner returnSpinner(){
        return spinner;
    }

}
