package com.watermelondc.app.start;

import com.watermelondc.app.controllers.MainController;
import com.watermelondc.app.interfaces.impls.CollectionAllNotes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class Main extends Application {
    //Создание главного окна

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);
        primaryStage.setTitle("Заметки");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);

        Scene scene = new Scene(fxmlMain, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //метод который используется для тестов данных в таблице
    private void testData(){
        CollectionAllNotes allNotes = new CollectionAllNotes();
        allNotes.fillTestData();
        allNotes.print();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
