package com.example.plato;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    private CountDownTimer workTimer;

    private boolean startwork = true;
    private boolean timerRunning = false;

    private long workTime = 60000 * 25;
    private long breakTime = 60000 * 5;

//    private long workTime = 8000;
//    private long breakTime = 4000;

    private long countTime = workTime;

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
                if(startwork == true && timerRunning == false) {
                    timerRunning = true;
                    countdownButton.setText("Pause");
                    startTimer();
                }
                else if(startwork == false && timerRunning == false) {
                    timerRunning = true;
                    countdownButton.setText("Pause");
                    startTimer();
                }
                else if (timerRunning = true){
                    workTimer.cancel();
                    timerRunning = false;
                    countdownButton.setText("Resume");
                }
            }
        });
        updateTimer();
        // Inflate the layout for this fragment
        return view;
    }

    public void startTimer() {
        workTimer = new CountDownTimer(countTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTime = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                countdownText.setTextSize(50);
                if (startwork) {
                    countdownText.setText("Work Time Ended.");
                    countdownButton.setText("Start");
                    timerRunning = false;
                    countTime = breakTime;
                    final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.ding);
                    mp.start();
                }
                else if (!startwork){
                    countdownText.setText("Break Time Ended.");
                    countdownButton.setText("Start");
                    timerRunning = false;
                    countTime = workTime;
                    final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.ding);
                    mp.start();
                }
                startwork = !startwork;
            }
        }.start();
    }

    public void updateTimer() {
        int mins = (int) (countTime / 60000);
        int seconds = (int) (countTime % 60000) / 1000;
        String timeLeftText = mins + ":";
        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;
        countdownText.setTextSize(105);
        countdownText.setText(timeLeftText);
    }
}