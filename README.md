# *Plato*

Plato is a study application for Android phones, with the objective of providing a one-stop solution for studying, be it academic or professional. Java is used for the application's back end with XML being used for the frontend. Plato was developed with the Android Studio IDE, and is built with Gradle.

The application is divided into 5 segments, each with their own specific purpose:

### Calendar
The Calendar page downloads, parses, and formats events from a user-provided .ical link from the Calendar tab on Canvas, and stores the event data in the app's preferences using SharedPreferences. The user is able to select a specific date on the CalendarView, and the events on that day are formmated into event items and displayed in a RecyclerView situated below the CalendarView.
### Notes
The notes page allows the user to create, edit, and delete notes. Each individual notes has a title, body, and creation date attached, and note data is stored
### Cards

### Pomodoro

### Noise

---

## Dependencies
JSR107 API and SPI » 1.1.1  
Apache Commons Codec » 1.15  
Apache Commons Collections » 4.4  
Apache Commons Lang » 3.11  
Gson » 2.8.6  
Ical4j » 3.0.0  
SLF4J API Module » 1.7.30  
