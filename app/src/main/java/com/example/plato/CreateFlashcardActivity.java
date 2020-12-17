package com.example.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.*;

public class CreateFlashcardActivity extends AppCompatActivity {
    Button saveBtn;
    public static ArrayList<String> questions = new ArrayList<String>();
    public static ArrayList<String> answers= new ArrayList<String>();
    EditText que;
    EditText ans;
    ListView show, show2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard);
        saveBtn = (Button)findViewById(R.id.save);
//        show = (ListView) findViewById(R.id.questionView);
//        show2 = (ListView) findViewById(R.id.answerView);
        que = (EditText) findViewById(R.id.question);
        ans = (EditText) findViewById(R.id.answer);

        saveBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String questionInput = que.getText().toString();
                String answerInput = ans.getText().toString();

                questions.add(questionInput);
                answers.add(answerInput);

                ArrayAdapter<String> qAdapter = new ArrayAdapter<>(CreateFlashcardActivity.this, android.R.layout.simple_list_item_1, questions);
                ArrayAdapter<String> aAdapter = new ArrayAdapter<>(CreateFlashcardActivity.this, android.R.layout.simple_list_item_1, answers);
//                show.setAdapter(qAdapter);
//                ((EditText)findViewById(R.id.question)).setText(" ");
//
//                show2.setAdapter(aAdapter);
//                ((EditText)findViewById(R.id.answer)).setText(" ");


            }
        });
    }
    public static ArrayList<String> getQuestions(){
        for(int i = 0; i < questions.size(); i++){
            System.out.println(questions.get(i));
        }
        return questions;
    }
    public static ArrayList<String> getAnswers(){
        for(int i = 0; i < answers.size(); i++){
            System.out.println(answers.get(i));
        }
        return answers;
    }

    public static void setQuestions(ArrayList<String> questions) {
        CreateFlashcardActivity.questions = questions;
    }

    public static void setAnswers(ArrayList<String> answers) {
        CreateFlashcardActivity.answers = answers;
    }
}
