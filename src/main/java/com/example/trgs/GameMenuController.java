package com.example.trgs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}