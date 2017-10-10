package com.watermelondc.app.dbnote;

import java.sql.*;

//Класс отвечающий за подключение к БД и выполнение запросов
public class DBConnection {

    private final String HOST="jdbc:mysql://localhost:3306/notes";
    private final String USERNAME="root";
    private final String PASSWORD="root";



    private Connection connection;

    //Конструктор класса
    public DBConnection(){

        try{
        connection = DriverManager.getConnection(HOST,USERNAME,PASSWORD);
        System.out.println("Connected success");
    }catch(SQLException e){
        e.printStackTrace();
    }
    }


    public Connection getConnection() {
        return connection;
    }

    //Метод для выполнения запросов в БД - Получает на вход строку запроса, а затем выполняет необходимые изменения
    public void executeQuery(String query){
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}