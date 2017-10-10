package com.watermelondc.app.interfaces;

import com.watermelondc.app.objects.Note;

public interface AllNotes {

    //add new note
    void add(Note note);

    //edit note
    void update(Note note);

    //delete note
    void delete(Note note);
}
