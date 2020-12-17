package com.example.plato;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.MapTimeZoneCache;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskEdit extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_calendar_item.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskEdit newInstance(String param1, String param2) {
        TaskEdit fragment = new TaskEdit();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calendar_taskedit, container, false);

        final ImageButton backButton = rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Fragment fragment = new Calendar();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment).commit();
            }
        });

        final Button submitButton = rootView.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                EditText URLinput = rootView.findViewById(R.id.taskURLInput);
                String URLtext = URLinput.getText().toString();
                Context context = getActivity();
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("icalURL", URLtext);
                editor.apply();
                ArrayList<String[]> eventArray = new ArrayList<>();
                try {
                    URL myURL = new URL(URLtext);
                    BufferedInputStream icalStream = new BufferedInputStream(myURL.openStream());

                    System.setProperty("net.fortuna.ical4j.parser", "net.fortuna.ical4j.data.DefaultCalendarParserFactory");
                    System.setProperty("net.fortuna.ical4j.timezone.registry", "net.fortuna.ical4j.model.DefaultTimeZoneRegistryFactory");
                    System.setProperty("net.fortuna.ical4j.timezone.update.enabled", "true");
                    System.setProperty("net.fortuna.ical4j.factory.decoder", "net.fortuna.ical4j.util.DefaultDecoderFactory");
                    System.setProperty(" net.fortuna.ical4j.factory.encoder", "net.fortuna.ical4j.util.DefaultEncoderFactory");
                    System.setProperty("net.fortuna.ical4j.timezone.registry", "net.fortuna.ical4j.model.DefaultTimeZoneRegistryFactory");
                    System.setProperty("net.fortuna.ical4j.timezone.cache.impl", "net.fortuna.ical4j.util.MapTimeZoneCache");

                    System.setProperty("net.fortuna.ical4j.unfolding.relaxed", "true");
                    System.setProperty("net.fortuna.ical4j.parsing.relaxed", "true");
                    System.setProperty("net.fortuna.ical4j.validation.relaxed", "true");
                    System.setProperty("net.fortuna.ical4j.compatibility.outlook", "true");
                    System.setProperty("net.fortuna.ical4j.compatibility.notes", "true");
                    System.setProperty("net.fortuna.ical4j.timezone.date.floating", "true");

                    CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_RELAXED_PARSING, true);
                    CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_RELAXED_VALIDATION, true);
                    CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_RELAXED_UNFOLDING, true);
                    CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_OUTLOOK_COMPATIBILITY, true);
                    CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_NOTES_COMPATIBILITY, true);

                    CalendarBuilder builder = new CalendarBuilder();
                    net.fortuna.ical4j.model.Calendar calendar = builder.build(icalStream);
                    int eventcount=0;
                    VEvent event;
                    for (Object o : calendar.getComponents("VEVENT")) {
                        event = (VEvent) o;
                        String[] eventItem = new String[2];
                        eventItem[0] = event.getSummary().toString();
                        eventItem[1] = event.getStartDate().toString();
                        eventArray.add(eventItem);
                        eventcount++;
                    }
                } catch (IOException | ParserException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                String eventsString = gson.toJson(eventArray);
                editor.putString("eventsString", eventsString);
                editor.apply();
                Fragment fragment = new Calendar();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment).commit();
            }
        });
        return rootView;
    }
}