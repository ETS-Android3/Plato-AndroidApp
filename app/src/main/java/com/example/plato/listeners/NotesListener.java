package com.example.plato.listeners;

import com.example.plato.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
