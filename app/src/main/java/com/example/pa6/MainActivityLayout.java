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

public class MainActivityLayout extends GridLayout {
        private Button newNoteButton;
        private GridLayout.LayoutParams layoutParams;
        private ListView listView;
        public MainActivityLayout(final Context context) {
            super(context);

            setColumnCount(1);

            layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            newNoteButton = new Button(context); // this button needs to send us to a new activity
            newNoteButton.setLayoutParams(layoutParams);   // v v v v v v v v v v v v v v v v v v v v
            newNoteButton.setText("Add new note");
            listView = new ListView(context);
            //listView.setLayoutParams(layoutParams);

            addView(newNoteButton);
            addView(listView);
        }
        public Button returnButton(){
            return newNoteButton;
        }
        public ListView returnListView(){
            return listView;
        }
//        public GridLayout.LayoutParams returnLayout(){
//            return layoutParams;
//        }



}
