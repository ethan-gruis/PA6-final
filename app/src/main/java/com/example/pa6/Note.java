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

import androidx.annotation.NonNull;

public class Note {
    // Fields
    private String title;
    private String content;
    private int type;


    // DVC for when nothing is passed in
    Note(){
        this.title = "NONE";
        this.content= "NONE";
        this.type = -1;
    }

    //EVC if there are title, content, and type
    Note(String title, String content, int type){
        this.title = title;
        this.content = content;
        this.type = type;
    }
    /**
     * returns back the title of a Note object
     *
     * @return String title
     */
    public String getTitle() {
        return title;
    }
    /**
     * sets the title of a Note object
     *
     * @param title the title the user inputted
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * returns back the Type of a Note object
     *
     * @return int type
     */
    public int getType() {
        return type;
    }
    /**
     * sets the type of a Note object
     *
     * @param type the type the user selected
     */
    public void setType(int type) {
        this.type = type;
    }
    /**
     * returns back the content of a Note object
     *
     * @return String content
     */
    public String getContent() {
        return content;
    }
    /**
     * sets the content of a Note object
     *
     * @param content the content the user inputted
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * returns the title of the Note object
     *
     * @return the title of the Note Object
     */
    @NonNull
    @Override
    public String toString() {
        return this.title;
    }
}
