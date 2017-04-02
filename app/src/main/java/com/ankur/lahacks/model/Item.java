package com.ankur.lahacks.model;

/**
 * Created by Ankur on 01/04/17.
 */

public class Item {

    private int imageID;
    private String title;


    public Item(int imageID, String title) {
        this.imageID = imageID;
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
