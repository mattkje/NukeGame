package com.example.trgs.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Button singlePlayerButton;

    public void singlePlayer() {
        FXMLLoader fxmlLoader = new FXMLLoader(GesserApp.class.getResource("/com/example/trgs/gameMenu.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) singlePlayerButton.getScene().getWindow(); // Assuming singlePlayerButton is part of the current scene
            Scene scene = new Scene(root, 1920, 1080);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
