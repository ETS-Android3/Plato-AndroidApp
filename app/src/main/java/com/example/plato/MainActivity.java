package com.example.plato;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;

import com.example.plato.activities.Notes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static MediaPlayer mp = new MediaPlayer();

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
                        mp.reset();
                        openFragment(new Flashcards());
                        return true;

                    case R.id.navbar_calendar:
                        mp.reset();
                        // This code wipes the shared preferences on Calendar start for testing purposes
                        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.clear();
                        openFragment(new Calendar());
                        return true;

                    case R.id.navbar_pomodoro:
                        mp.reset();
                        openFragment(new Pomodoro());
                        return true;

                    case R.id.navbar_notes:
                        mp.reset();
                        openFragment(new Notes());
                        return true;

                    case R.id.navbar_noise:
                        mp.reset();
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