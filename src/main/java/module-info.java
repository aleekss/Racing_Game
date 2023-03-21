module com.example.racing_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.racing_game to javafx.fxml;
    exports com.example.racing_game;
}