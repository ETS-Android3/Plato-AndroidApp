package com.example.plato;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navbar = findViewById(R.id.navbar);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.navbar_cards:
                        openFragment(new Flashcards());
                        return true;

                    case R.id.navbar_calendar:
                        openFragment(new Calendar());
                        return true;

                    case R.id.navbar_pomodoro:
                        openFragment(new Pomodoro());
                        return true;

                    case R.id.navbar_notes:
                        openFragment(new Notes());
                        return true;

                    case R.id.navbar_noise:
                        openFragment(new Noise());
                        return true;
                }
                return false;
            }
        });
        ArrayList<TaskItem> exampleList = new ArrayList<>();
        exampleList.add(new TaskItem(R.drawable.ic_baseline_event_note_24, "Title 1", "Date 1"));
        exampleList.add(new TaskItem(R.drawable.ic_baseline_event_note_24, "Title 2", "Date 2"));
        exampleList.add(new TaskItem(R.drawable.ic_baseline_event_note_24, "Title 3", "Date 3"));
    }
    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}