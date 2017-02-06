package com.example.nanic_sizebook;


import android.content.Context;
import android.widget.ArrayAdapter;


import java.util.List;

/**
 * Created by nanic on 1/27/2017.
 */

public class PersonAdapter extends ArrayAdapter<Person> {
    private Context mContext;

    public PersonAdapter(Context mContext, int resource, List<Person> objects) {
        super(mContext, resource, objects);
    }


}