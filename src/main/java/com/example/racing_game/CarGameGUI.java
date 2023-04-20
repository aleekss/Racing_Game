package com.example.racing_game;

import javafx.animation.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CarGameGUI implements Initializable {

    @FXML
    private AnchorPane roadPane;

    @FXML
    private ImageView hindernis;
    @FXML
    private ImageView hindernis2;
    @FXML
    private ImageView hindernis1;


    VBox vBox = new VBox();

    public Button playButton;
    public Button creditsButton;
    public Button exitButton;
    private double roadPosition = 0.0;
    private double roadSpeed = 2.0;


    public void onPlayButtonClick(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("loading.fxml"));

        Stage window = (Stage) playButton.getScene().getWindow();
        Scene loadingScene = new Scene(fxmlLoader.load(), 1000, 800);

// Get the progress bar from the loading scene
        ProgressBar progressBar = (ProgressBar) loadingScene.lookup("#progressBar");

// Create a new Task to run the loading process
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
// Simulate loading the game
                for (double progress = 0; progress < 1; progress += 0.1) {
                    updateProgress(progress, 1);
                    Thread.sleep(100);
                }
                return null;
            }
        };


// Bind the progress bar to the task's progress
        progressBar.progressProperty().bind(task.progressProperty());

// Set the scene to the loading scene
        window.setScene(loadingScene);

/*
// Create the TranslateTransition for the roadPane
TranslateTransition roadTransition = new TranslateTransition(Duration.seconds(5), roadPane);
roadTransition.setByY(50);
roadTransition.setCycleCount(TranslateTransition.INDEFINITE);

// Start the animation before starting the task
roadTransition.playFromStart();

*/


// Start the task on a separate thread
        new Thread(task).start();

        task.setOnSucceeded(event -> {
            try {
                FXMLLoader gameLoader = new FXMLLoader(Driver.class.getResource("game.fxml"));
                Scene gameScene = new Scene(gameLoader.load(), 1000, 800);

// Set the scene to the game scene
                window.setScene(gameScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }


    public void onCreditsButtonClick(ActionEvent actionEvent) {
    }

    public void onExitButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("exit.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene loadingScene = new Scene(fxmlLoader.load(), 1000, 800);
        window.setScene(loadingScene);
        window.show();


        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> window.close());
        delay.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition transition = new TranslateTransition();
        TranslateTransition transition2 = new TranslateTransition();
        TranslateTransition transition3 = new TranslateTransition();


        transition2.setNode(hindernis2);
        transition2.setDuration(Duration.seconds(3));
        transition2.setCycleCount(TranslateTransition.INDEFINITE);
        transition2.setByY(1000);
        transition2.play();


        transition.setNode(hindernis);
        transition.setDuration(Duration.seconds(2.5));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setByY(1000);
        transition.play();




        transition3.setNode(hindernis1);

        transition3.setDuration(Duration.seconds(3));
        transition3.setCycleCount(TranslateTransition.INDEFINITE);
        transition3.setByY(1000);
        transition3.play();

    }
}