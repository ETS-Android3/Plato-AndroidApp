package com.example.plato;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.CompatibilityHints;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calendar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendar extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calendar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendar.
     */
    // TODO: Rename and change types and number of parameters
    public static Calendar newInstance(String param1, String param2) {
        Calendar fragment = new Calendar();
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
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        final FloatingActionButton button = rootView.findViewById(R.id.taskAddButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Fragment fragment = new TaskEdit();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment).commit();
            }
        });
        CalendarView calendarView = rootView.findViewById(R.id.taskCalendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                String eventsString = sharedPref.getString("eventsString", "default");
                Gson gson = new Gson();
                ArrayList<String[]> eventsArray = new ArrayList<>();
                ArrayList<TaskItem> eventTaskArray = new ArrayList<>();
                ArrayList<String> event;
                String name;
                String date;
                SimpleDateFormat dateFormat;
                java.util.Calendar cal;
                if(!eventsString.equals("default")) {
                    eventsArray = gson.fromJson(eventsString, ArrayList.class);
                    for(Object o : eventsArray) {
                        event = (ArrayList<String>) o;
                        name = event.get(0).substring(8);
                        name = name.substring(0,  name.length()-2);
                        date =  event.get(1).substring(8);
                        date = date.substring(0,  date.length()-2);
                        if(date.contains("VALUE=DATE:")){
                            date = date.substring(11) + "T000000";
                        }
                        if (date.substring(date.length()-1).equals("Z")){
                            dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
                            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                            cal = java.util.Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                        }
                        else {
                            dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                            dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
                            cal = java.util.Calendar.getInstance(TimeZone.getTimeZone("EST"));
                        }

                        Date dateobj = null;
                        try {
                            dateobj = dateFormat.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        //Should look like 11/9 @ 11:59pm
                        if (dateobj.getHours() == 0 && dateobj.getMinutes() == 0){
                            dateFormat = new SimpleDateFormat("MM/dd '@' '11:59pm'");
                            date = dateFormat.format(dateobj);
                        }
                        else if (dateobj.getHours() > 12){
                            dateFormat = new SimpleDateFormat("MM/dd '@' HH':'mm");
                            dateobj.setHours((dateobj.getHours()-12));
                            date = dateFormat.format(dateobj);
                            date = date + "pm";
                        }
                        else {
                            dateFormat = new SimpleDateFormat("MM/dd '@' HH':'mm");
                            date = dateFormat.format(dateobj);
                            date = date + "am";
                        }
                        cal.setTime(dateobj);
                        int eventyear = cal.get(java.util.Calendar.YEAR);
                        int eventmonth = cal.get(java.util.Calendar.MONTH);
                        int eventday = cal.get(java.util.Calendar.DAY_OF_MONTH);

//                        int eventyear = dateobj.getYear();
//                        int eventmonth = dateobj.getMonth();
//                        int eventday = dateobj.getDay();

                        if(eventyear == year && eventmonth == month && eventday == dayOfMonth){
                            eventTaskArray.add(new TaskItem(R.drawable.ic_baseline_event_note_24, name, date));
                        }
                    }
                }
                mAdapter = new TaskAdapter(eventTaskArray);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });

        ArrayList<TaskItem> eventTaskArray = new ArrayList<>();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.taskList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new TaskAdapter(eventTaskArray);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }
}