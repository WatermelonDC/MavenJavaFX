package com.watermelondc.app.objects;

import com.watermelondc.app.dbnote.DBConnection;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

    /*
    Класс заметок: создание полей заметок, гетеров/сетеров
    */

public class Note {
    //Для работы с БД
    DBConnection dbConnection = new DBConnection();

    //подсчет количества заметок
    private static int count=0;

    //Поля заметок: id, name, task, localDate, strDate - для отображения в таблице
    private int idNote = 0;
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty task = new SimpleStringProperty("");
    private SimpleStringProperty strDate = new SimpleStringProperty("");
    private LocalDate localDate;


    //Несколько конструкторов для заметок
    public Note(){
        this.idNote = ++count;
        this.localDate= LocalDate.now();
        //Вариант с БД
        addNewNoteDB();

    }

    public Note(String name, String task) {
        this.name = new SimpleStringProperty(name);
        this.task = new SimpleStringProperty(task);
        this.idNote = ++count;
        this.localDate= LocalDate.now();
        setStrDate(this.localDate);
        //Вариант с БД
        addNewNoteDB();

    }

    public Note(String name, String task, LocalDate localDate) {
        this.name = new SimpleStringProperty(name);
        this.task = new SimpleStringProperty(task);
        this.idNote = ++count;
        this.localDate = localDate;
        setStrDate(this.localDate);

        //Вариант с БД
        addNewNoteDB();

    }


    //Геттеры и сетеры для переменных
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTask() {
        return task.get();
    }

    public void setTask(String task) {
        this.task.set(task);
    }


    public String getStrDate(){
    strDate.set(localDate.toString());
    return strDate.get();
    }

    public void setStrDate(LocalDate localDate){
        strDate.set(localDate.toString());
    }

    public LocalDate getLocalDate(){
        return this.localDate;
    }

    public void setLocalDate(LocalDate localDate){
        this.localDate = localDate;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }


    //Метод добавления новой записи в БД
    private void addNewNoteDB(){
        String query = "INSERT INTO note(name,task,date) VALUES ('"+getName()+"','"+getTask()+"','"+getStrDate()+"');";
        dbConnection.executeQuery(query);

    }

    //Метод для удобства проверки работы программы
    @Override
    public String toString() {
        return "Note ("+
                "Name = "+name+"\'"+
                ", Task = "+ task+"\'"+
                ")";
    }
}
