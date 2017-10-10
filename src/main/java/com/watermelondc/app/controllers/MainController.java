package com.watermelondc.app.controllers;
import com.watermelondc.app.interfaces.impls.CollectionAllNotes;
import com.watermelondc.app.objects.Note;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;



import java.io.IOException;


    /*
    Контроллер для главного окна
     */

public class MainController{


    private CollectionAllNotes allNotes = new CollectionAllNotes();


    private Stage mainStage;

    /*
    Необходимые элементы управления из макета
     */
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableView tblNotes;

    public TableColumn<Note, String> colName;
    public TableColumn<Note, String> colTask;
    public TableColumn<Note, String> colLocalDate;

    @FXML
    public Label lblCountNotes;

    private Parent fxmlEdit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private EditDialogController editDialogController = new EditDialogController();

    private Stage editDialogStage;


    /*
    Инициализация таблицы с данными
     */
    @FXML
    private void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<Note, String>("name"));
        colTask.setCellValueFactory(new PropertyValueFactory<Note, String>("task"));
        colLocalDate.setCellValueFactory(new PropertyValueFactory<Note, String>("strDate"));


        initListener();

        allNotes.fillTestData();

        tblNotes.setItems(allNotes.getNoteList());


        initLoader();

    }

    //Инициализация слушателя отслеживающего изменение данных в таблице
    private void initListener(){
        allNotes.getNoteList().addListener(new ListChangeListener<Note>() {
            @Override
            public void onChanged(Change<? extends Note> c) {
                updateCountLabel();
            }
        });

        tblNotes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    editDialogController.setNote((Note)tblNotes.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    //Инициализация загрузчика

    private void initLoader(){
        try{
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Метод обновления счетчика подсчета строк
    private void updateCountLabel(){
        lblCountNotes.setText(String.valueOf(allNotes.getNoteList().size()));
    }

    //Метод обработки нажатия кнопок
    public void mainBtnAction(ActionEvent actionEvent){

            Object source = actionEvent.getSource();

            if(!(source instanceof Button)){return;}

            Button clickedButton = (Button) source;

            Note selectedNote = (Note)tblNotes.getSelectionModel().getSelectedItem();

            switch(clickedButton.getId()){
                case "btnAdd":
                    editDialogController.setNote(new Note());
                    //Вариант работы с БД
                    //editDialogController.setNote(new DBNote());

                    showDialog();
                    allNotes.add(editDialogController.getNote());
                    break;

                case "btnEdit":
                    if(!noteIsSelected(selectedNote)){
                        return;
                    }
                    editDialogController.setNote(selectedNote);
                    showDialog();
                    break;

                case "btnDelete":
                    if(!noteIsSelected(selectedNote)){
                        return;
                    }
                    allNotes.delete(selectedNote);
                    break;

            }
    }

    //Метод определяющий нажата ли строка (используется для действий с кнопками)
    private boolean noteIsSelected(Note selectedNote){
        if(selectedNote==null) {
            System.out.println("Ошибка. Выбирите надпись");
            //DialogManager.showInfoDialog("Ошибка", "Необходимо выбрать запись");
            return false;
        }
        return true;
    }

    //Modal window for editing information
    private void showDialog(){

        if(editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinWidth(450);
            editDialogStage.setMinHeight(300);
            editDialogStage.setResizable(false);

            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);//указывает что это модальное окно
        }

        editDialogStage.showAndWait(); //показывает модальное окно и ожидание его закрытия

    }


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
