package com.example.plato;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.plato.activities.Notes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;

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

    }
    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}