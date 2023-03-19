package com.fullsail.mitchellantoine_dank_tank.object;

import android.net.Uri;

import java.io.Serializable;

public class Person implements Serializable {

    private String first_name;
    private String last_name;

    private Uri imageUri;

    public Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Person(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    @Override
    public String toString() {
        return "Person{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
