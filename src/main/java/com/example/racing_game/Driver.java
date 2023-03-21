package com.example.racing_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class Driver extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("HAllo");


        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500 , 500);


        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}

