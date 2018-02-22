package com.nacoda.kamus.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dicoding on 12/6/2016.
 */

public class Kamus implements Serializable {
    private int id;
    private String word;
    private String description;

    public Kamus(String word, String description) {
        this.word = word;
        this.description = description;
    }
    public Kamus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
