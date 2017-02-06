package com.example.nanic_sizebook;

/**
 * Created by anicn on 2017-01-28.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddPerson extends AppCompatActivity {

    private EditText nameBody;
    private EditText dateBody;
    private EditText neckBody;
    private EditText bustBody;
    private EditText chestBody;
    private EditText waistBody;
    private EditText hipBody;
    private EditText inseamBody;
    private EditText commentBody;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                nameBody = (EditText) findViewById(R.id.nameField);
                String name = nameBody.getText().toString();
                // name field is required
                if (name.matches("")) {
                    Toast.makeText(getApplicationContext(), "Name Field is Required.", Toast.LENGTH_LONG).show();
                    return;
                }



                Person person = null;
                person = new Person(name);

                //taken from http://stackoverflow.com/questions/6290531/check-if-edittext-is-empty
                //Feb 1, 2017, 16:18
                //sets attributes of record to whatever is in the EditText
                //if EditText is empty, sets attribute to NULL
                dateBody = (EditText) findViewById(R.id.dateField);
                String date = dateBody.getText().toString();
                if (date.matches("")) {
                    person.setDate("NULL");
                } else {
                    person.setDate(date);
                }

                neckBody = (EditText) findViewById(R.id.neckSizeField);
                String neckText = neckBody.getText().toString();
                if (neckText.matches("")) {
                    person.setNeckSize("NULL");
                } else {
                    neckText = String.format("%.1f", Float.parseFloat(neckText));
                    person.setNeckSize(neckText);
                }

                bustBody = (EditText) findViewById(R.id.bustSizeField);
                String bustText = bustBody.getText().toString();
                if (bustText.matches("")) {
                    person.setBustSize("NULL");
                } else {
                    bustText = String.format("%.1f", Float.parseFloat(bustText));
                    person.setBustSize(bustText);
                }

                chestBody = (EditText) findViewById(R.id.chestSizeField);
                String chestText = chestBody.getText().toString();
                if (chestText.matches("")) {
                    person.setChestSize("NULL");
                } else {
                    chestText = String.format("%.1f", Float.parseFloat(chestText));
                    person.setChestSize(chestText);
                }

                waistBody = (EditText) findViewById(R.id.waistSizeField);
                String waistText = waistBody.getText().toString();
                if (waistText.matches("")) {
                    person.setWaistSize("NULL");
                } else {
                    waistText = String.format("%.1f", Float.parseFloat(waistText));
                    person.setWaistSize(waistText);
                }

                hipBody = (EditText) findViewById(R.id.hipSizeField);
                String hipText = hipBody.getText().toString();
                if (hipText.matches("")) {
                    person.setHipSize("NULL");
                } else {
                    hipText = String.format("%.1f", Float.parseFloat(hipText));
                    person.setHipSize(hipText);
                }

                inseamBody = (EditText) findViewById(R.id.inseamSizeField);
                String inseamText = inseamBody.getText().toString();
                if (inseamText.matches("")) {
                    person.setInseamSize("NULL");
                } else {
                    inseamText = String.format("%.1f", Float.parseFloat(inseamText));
                    person.setInseamSize(inseamText);
                }

                commentBody = (EditText) findViewById(R.id.commentField);
                String commentText = commentBody.getText().toString();
                if (commentText.matches("")) {
                    person.setComment("NULL");
                } else {
                    person.setComment(commentText);
                }

                ((MyApplication)getApplicationContext()).personsList.add(person);
                saveInFile();



            }
        });

    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(((MyApplication)getApplicationContext()).FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(((MyApplication)getApplicationContext()).personsList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

