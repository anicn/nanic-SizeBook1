package com.example.nanic_sizebook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class EditPerson extends AppCompatActivity {
    public Integer id;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        //gets extras from bundle, gets mouse click position
        //and corresponding item in recordsList
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("pos");
        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                //taken from http://stackoverflow.com/questions/3579981/why-is-my-arraylist-removeid-call-not-working
                //Feb 2, 2017, 19:19
                ((MyApplication)getApplicationContext()).personsList.remove(((MyApplication)getApplicationContext()).personsList.get(id));
                saveInFile();
            }
        });
        //sets instance variables of this record as hint text
        Person person = ((MyApplication)getApplicationContext()).personsList.get(id);
        EditText nameText = (EditText) findViewById(R.id.nameFieldEdit);
        nameText.setHint(person.getName());

        EditText dateText = (EditText) findViewById(R.id.dateFieldEdit);
        dateText.setHint(person.getDate());

        EditText neckText = (EditText) findViewById(R.id.neckSizeFieldEdit);
        neckText.setHint(person.getNeckSize());

        EditText bustText = (EditText) findViewById(R.id.bustSizeFieldEdit);
        bustText.setHint(person.getBustSize());

        EditText chestText = (EditText) findViewById(R.id.chestSizeFieldEdit);
        chestText.setHint(person.getChestSize());

        EditText waistText = (EditText) findViewById(R.id.waistSizeFieldEdit);
        waistText.setHint(person.getWaistSize());

        EditText hipText = (EditText) findViewById(R.id.hipSizeFieldEdit);
        hipText.setHint(person.getHipSize());

        EditText inseamText = (EditText) findViewById(R.id.inseamSizeFieldEdit);
        inseamText.setHint(person.getInseamSize());

        EditText commentText = (EditText) findViewById(R.id.commentFieldEdit);
        commentText.setHint(person.getComment());

        Button saveChangesButton = (Button) findViewById(R.id.saveChanges);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sets attributes of this record to what is in the EditText if the
                //EditText is not empty
                Person person = ((MyApplication)getApplicationContext()).personsList.get(id);
                dateBody = (EditText) findViewById(R.id.dateFieldEdit);
                String date = dateBody.getText().toString();
                //taken from http://stackoverflow.com/questions/14721397/checking-if-a-string-is-empty-or-null-in-java
                //Feb 2, 2017, 20:22
                if (date != null && !date.isEmpty()) {
                    person.setDate(date);
                }

                nameBody = (EditText) findViewById(R.id.nameFieldEdit);
                String name = nameBody.getText().toString();
                if(name != null && !name.isEmpty()) {
                    person.setName(name);
                }

                neckBody = (EditText) findViewById(R.id.neckSizeFieldEdit);
                String neck = neckBody.getText().toString();
                if(neck != null && !neck.isEmpty()) {
                    person.setNeckSize(neck);
                }

                bustBody = (EditText) findViewById(R.id.bustSizeFieldEdit);
                String bust = bustBody.getText().toString();
                if(bust != null && !bust.isEmpty()) {
                    person.setBustSize(bust);
                }

                chestBody = (EditText) findViewById(R.id.chestSizeFieldEdit);
                String chest = chestBody.getText().toString();
                if(chest != null && !chest.isEmpty()) {
                    person.setChestSize(chest);
                }

                waistBody = (EditText) findViewById(R.id.waistSizeFieldEdit);
                String waist = waistBody.getText().toString();
                if(waist != null && !waist.isEmpty()) {
                    person.setWaistSize(waist);
                }

                hipBody = (EditText) findViewById(R.id.hipSizeFieldEdit);
                String hip = hipBody.getText().toString();
                if(hip != null && !hip.isEmpty()) {
                    person.setHipSize(hip);
                }

                inseamBody = (EditText) findViewById(R.id.inseamSizeFieldEdit);
                String inseam = inseamBody.getText().toString();
                if(inseam != null && !inseam.isEmpty()) {
                    person.setInseamSize(inseam);
                }

                commentBody = (EditText) findViewById(R.id.commentFieldEdit);
                String comment = commentBody.getText().toString();
                if(comment != null && !comment.isEmpty()) {
                    person.setComment(comment);
                }

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