package com.watermelondc.app.interfaces.impls;

import com.watermelondc.app.interfaces.AllNotes;
import com.watermelondc.app.objects.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


    /*
    Создание списка заметок с методами для работы с ними
     */

public class CollectionAllNotes implements AllNotes{

    private ObservableList<Note> noteList = FXCollections.observableArrayList();

    public void add(Note note){
        noteList.add(note);
    }

    public void update(Note note){

    }

    public void delete (Note note){
        noteList.remove(note);
    }

    public ObservableList<Note> getNoteList(){
        return noteList;
    }

    public void print(){
        int number = 0;
        System.out.println();
        for(Note note: noteList){
            number++;
            System.out.println(number+") name = "+note.getName()+"; task = "+note.getTask());
        }
    }

    public void fillTestData(){
        noteList.add(new Note("Igor", "ssssss"));
        noteList.add(new Note("Eva", "qqq"));
        noteList.add(new Note("MadMax", "wwwwwwwwwwww"));
        noteList.add(new Note("Saty", "eeeeeee"));
        noteList.add(new Note("Jax", "zzzzzzzzzzz"));
        noteList.add(new Note("Kostya", "xx"));
        noteList.add(new Note("Arcadiy", "c"));
        noteList.add(new Note("Aren", "b"));
        noteList.add(new Note("Petr", "b"));
        noteList.add(new Note("DC", "n"));
    }
}
