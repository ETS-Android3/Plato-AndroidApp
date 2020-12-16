package com.example.plato;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pomodoro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pomodoro extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //variables for the timer
    private TextView countdownText;
    private Button countdownButton;
    private CountDownTimer countDownTimer;
    private long timeLeft = (60000 * 25);
    private boolean isTimerRunning;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pomodoro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pomodoro.
     */
    // TODO: Rename and change types and number of parameters
    public static Pomodoro newInstance(String param1, String param2) {
        Pomodoro fragment = new Pomodoro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pomodoro, container, false);
        countdownText = (TextView) view.findViewById(R.id.countdown_text);
        countdownButton = (Button) view.findViewById(R.id.start_button);
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();

            }
        });
        updateTimer();
        // Inflate the layout for this fragment
        return view;
    }

    public void startStop() {
        if (isTimerRunning) {
            stopTimer();
        } else
            startTimer();
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer();
//                String sDuration=String.format(Locale.ENGLISH, "%02d: %02d"
//                , TimeUnit.MICROSECONDS.toMinutes(1)
//                , TimeUnit.MILLISECONDS.toSeconds(1)-
//                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
//                countdownText.setText(sDuration);

            }

            @Override
            public void onFinish() {
                countdownText.setText("Work time Ended, break starts now :D");
                updateTimer2();

//                Toast.makeText(getApplicationContext(), "Countdown Timer has ended", Toast.LENGTH_LONG).show();

            }
        }.start();
        countdownButton.setText("Pause");
        isTimerRunning = true;

    }

    public void stopTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        countdownButton.setText("Stop");
    }

    public void updateTimer() {
        int mins = (int) (timeLeft / 60000);
        int seconds = (int) (timeLeft % 60000) / 1000;
        String timeLeftText = mins + ":";
        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);
    }

    public void updateTimer2() {
        int mins = (int) (timeLeft / 60000);
        int seconds = (int) (timeLeft % 60000) / 1000;
        String timeLeftText = mins + ":";
        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);
    }
}