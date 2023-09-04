package com.example.trgs.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuController implements Initializable {

    private int timeInSeconds = 20;

    @FXML
    private Label timerLabel;


    public void startTimer() {
        // Create a Timeline for the countdown
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely

        // Add KeyFrame to update the timer label every second
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (timeInSeconds > 0) {
                    timeInSeconds--;
                    updateTimerLabel();
                } else {
                    timeline.stop(); // Stop the timer when it reaches 0
                }
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    // Update the timer label with the current time
    private void updateTimerLabel() {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startTimer();
    }
}