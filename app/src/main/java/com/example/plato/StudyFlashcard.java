package com.example.plato;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StudyFlashcard extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_flashcard);

        listView = (ListView)findViewById(R.id.listview);

        ArrayList<String> questions = CreateFlashcardActivity.getQuestions();
        ArrayList<String> answers = CreateFlashcardActivity.getAnswers();


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, questions);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(StudyFlashcard.this, "The answer is: " + answers.get(position).toString(), Toast.LENGTH_SHORT).show();

            }
        });
        Button shuffle = (Button)findViewById(R.id.shuffle);
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long seed = System.nanoTime();
                Collections.shuffle(questions, new Random(seed));
                Collections.shuffle(answers, new Random(seed));
            }
        });



    }
}