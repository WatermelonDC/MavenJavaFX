package com.watermelondc.app.controllers;
import com.watermelondc.app.dbnote.DBConnection;
import com.watermelondc.app.objects.Note;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

/*
Контроллер для второго окна
 */
public class EditDialogController {

    public TextField tfName;
    public TextArea tfTask;
    public DatePicker dtToDo;
    public Button btnOk;
    public Button btnCancel;

    private Note note;

    //Вариант работы с БД
    DBConnection dbConnection = new DBConnection();

    public void actionClose(ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void setNote( Note note){

        this.note = note;

        tfName.setText(note.getName());
        tfTask.setText(note.getTask());
        dtToDo.setValue(note.getLocalDate());

    }


    public Note getNote(){return note;}

    public void actionSave(ActionEvent actionEvent){
        if(!checkValues()){return;}
        if(!checkLength()){return;}

        note.setName(tfName.getText());
        note.setTask(tfTask.getText());
        note.setLocalDate(dtToDo.getValue());
        note.setStrDate(note.getLocalDate());

        //Вариант работы с БД
        updateDateDB(note.getName(),note.getTask(),note.getStrDate(), note.getIdNote());

        actionClose(actionEvent);
    }

    private boolean checkValues(){
        if(tfName.getText().trim().length()==0 || tfTask.getText().trim().length()==0){
            System.out.println("Ошибка. Заполните все поля");
            //DialogManager.showInfoDialog("Ошибка","Заполните все поля");
            return false;
        }
        return true;

    }

    private boolean checkLength(){
        if(tfTask.getText().length() >= 100){
            System.out.println("Слишком много символов. Укоротите запись");
            //DialogManager.showInfoDialog("Ошибка","Укоротите запись");
            return false;
        }
        return true;
    }


        public void updateDate(ActionEvent actionEvent) {
            note.setStrDate(note.getLocalDate());
        }

        //Обновление данных  в БД
        public void updateDateDB(String name, String task, String date, int id){

            String query = "UPDATE notes SET name='"+name+"','"+task+"','"+date+"' WHERE id="+id+";";

            dbConnection.executeQuery(query);
        }
    }
