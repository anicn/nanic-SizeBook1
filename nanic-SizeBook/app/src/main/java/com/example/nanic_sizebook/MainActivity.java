package com.example.nanic_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView oldPersonsList;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldPersonsList = (ListView) findViewById(R.id.oldPersonsList);
        // http://stackoverflow.com/questions/30711517/how-to-change-the-contents-of-listview-on-item-click
        oldPersonsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //mouse click position is passed thru bundle, string key "pos"
                //is retrieved later in EditPerson activity
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                editPerson(bundle);
                adapter.notifyDataSetChanged();
            }
        });

    }

    protected void onResume() {
        super.onResume();
        int numPersons = ((PersonApplication)getApplicationContext()).personsList.size();
        TextView textView = (TextView) this.findViewById(R.id.Persons);
        String personsCount = "Number of Entries: " + String.valueOf(numPersons);
        textView.setText(personsCount);
        textView.setTextColor(0xff0000ff);

    }
    // editPerson called whenever an item from oldPersonsList is clicked
    public void editPerson (Bundle bundle) {
        Intent intent = new Intent(this, EditPerson.class);
        intent.putExtras(bundle);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }
    // addPerson called when add person button clicked
    // no need for a listener, method only gets called when button is clicked
    // this happens through activity_main.xml
    public void addPerson(View view) {
        Intent intent = new Intent(this, AddPerson.class);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(((PersonApplication)getApplicationContext()).FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // Feb 1, 2017, 18:00
            Type listType = new TypeToken<ArrayList<Person>>() {}.getType();
            ((PersonApplication)getApplicationContext()).personsList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            ((PersonApplication)getApplicationContext()).personsList = new ArrayList<Person>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();

        adapter = new PersonAdapter(this, R.layout.record_list,((PersonApplication)getApplicationContext()).personsList);
        oldPersonsList.setAdapter(adapter);
    }
}
