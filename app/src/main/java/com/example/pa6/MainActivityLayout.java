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
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
/**
 * Builds the layout behind MainActivity using GridLayout
 *
 */
public class MainActivityLayout extends GridLayout {
        // Fields
        private Button newNoteButton;
        private GridLayout.LayoutParams layoutParams;
        private ListView listView;


        public MainActivityLayout(final Context context) {
            super(context);
            setColumnCount(1);

            // sets up children of GridLayout
            layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            newNoteButton = new Button(context);
            newNoteButton.setLayoutParams(layoutParams);
            newNoteButton.setText(getResources().getString(R.string.addNewNote));
            listView = new ListView(context);

            // adds the views to the layout
            addView(newNoteButton);
            addView(listView);
        }
    /**
     * returns the returnButton to have its function defined in MainActivity
     * @return returnButton button with no function
     */
        public Button returnButton(){
            return newNoteButton;
        }
    /**
     * returns the ListView to have its function defined in MainActivity
     * @return listView empty listView that was created here
     */
        public ListView returnListView(){
            return listView;
        }
}
