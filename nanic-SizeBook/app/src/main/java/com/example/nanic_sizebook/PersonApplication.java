package com.example.nanic_sizebook;


import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


/**
 * Created by anicn on 2017-02-04.
 * This class makes personList and FILENAME global
 * so that the whole application can easily
 * access them
 */

//taken from http://stackoverflow.com/questions/11932178/in-android-how-to-make-array-list-available-to-one-or-more-activities

public class PersonApplication extends Application {
    public ArrayList<Person> personsList = null;
    public static final String FILENAME = "file.sav";

    public PersonApplication() {
        personsList = new ArrayList<Person>();
    }
}
