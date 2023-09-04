package com.example.trgs.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GesserApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GesserApp.class.getResource("/com/example/trgs/mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        scene.getStylesheets().add(
                Objects.requireNonNull(this.getClass().getResource("/com/example/trgs/css/main.css"))
                        .toExternalForm());
        stage.setTitle("TRGS");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.getFullScreenExitKeyCombination();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}