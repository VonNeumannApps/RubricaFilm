package com.example.rubricafilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

public class MovieDetailActivity extends AppCompatActivity {

    DBManager dbManager;

    ContentValues movie;

    EditText nameET;
    EditText typeET;
    EditText yearET;
    EditText supportTypeET;


    void saveMovie() {

        if (movie == null) {

            movie = new ContentValues();
        }

        movie.put("nome", nameET.getText().toString());
        movie.put("genere", typeET.getText().toString());
        movie.put("anno_prod", yearET.getText().toString());
        movie.put("tipo_supp", supportTypeET.getText().toString());

        dbManager.insertMovie(movie);

        setResult(RESULT_OK);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        dbManager = new DBManager(this, DBManager.DATABASE_NAME, null, DBManager.DATABASE_VERSION);

        nameET = findViewById(R.id.nameEditText);
        typeET = findViewById(R.id.typeEditText);
        yearET = findViewById(R.id.yearEditText);
        supportTypeET = findViewById(R.id.supportTypeEditText);

        Button saveBtn = findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveMovie();
            }
        });
    }
}
