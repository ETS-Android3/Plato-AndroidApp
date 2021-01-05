# *Plato*

Plato is a study application for Android phones, with the objective of providing a one-stop solution for studying, be it academic or professional. Java is used for the application's back end, with XML being used for the front end. Plato was developed with the Android Studio IDE, and is built with Gradle.

The application is divided into 5 segments, each with their own specific purpose:

### Calendar (Nathan Adams)
The Calendar page downloads, parses, and formats events from a user-provided .ical link from the user's Calendar tab on Canvas, and stores the event data in the app's preferences using SharedPreferences. The user is able to select a specific date on the CalendarView, and the events on that day are formatted into event items and are displayed in a RecyclerView situated below the CalendarView.
### Notes (Kruti Patel)
The Notes page allows the user to view, create, edit, delete, and search notes. Each individual note has a title, body, creation date, and color, with title, body, and color being editable after the note's creation. Note data is stored and retrieved using the Room library, and the library can be searched via the search bar above the note display area.
### Cards (Aditya Yadav)
The Cards page enables the user to view, create, and edit flashcards, with users being able to set terms and definitions for each card added. The cards can be viewed, with the definition being displayed when the corresponding term is tapped.
### Pomodoro (Anusha Reddy/Nathan Adams)
The Pomodoro page provides the user with a pomodoro technique work timer that alternates between counting down from 25 and 5 minutes. The user is notified when the 25 minute study sessions and 5 minute break sessions are completed, and the timer can be paused and resumed at any point.
### Noise (Fozil Elbekov)
The Noise page plays background noise when the play button is pressed, with the user being able to skip forwards and backwards to any point in the track, raise or lower the volume, and pause or resume the background noise.

---

## Dependencies
JSR107 API and SPI » 1.1.1  
Apache Commons Codec » 1.15  
Apache Commons Collections » 4.4  
Apache Commons Lang » 3.11  
Gson » 2.8.6  
Ical4j » 3.0.0  
SLF4J API Module » 1.7.30  
