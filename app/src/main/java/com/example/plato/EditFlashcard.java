package com.example.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EditFlashcard extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);

        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> questions = CreateFlashcardActivity.getQuestions();
        ArrayList<String> answers = CreateFlashcardActivity.getAnswers();


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, questions);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(EditFlashcard.this, "The answer is: " + answers.get(position).toString(), Toast.LENGTH_SHORT).show();
                EditText que = (EditText) findViewById(R.id.newQuestion);
                EditText ans = (EditText) findViewById(R.id.newAnswer);

                int index = position;

                Button saveBtn = (Button)findViewById(R.id.save);
                saveBtn.setOnClickListener(new View.OnClickListener() {


                    public void onClick(View v) {
                        String questionInput = que.getText().toString();
                        String answerInput = ans.getText().toString();
                        questions.set(index, questionInput);
                        answers.set(index, answerInput);


                    }
                });
            }
        });
        CreateFlashcardActivity.setQuestions(questions);
        CreateFlashcardActivity.setAnswers(answers);

    }
}