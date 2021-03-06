package com.example.game2048;

import com.example.game2048.controllers.RootController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("GamePage/Root.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 850);
        scene.getStylesheets().add(Application.class.getResource("GamePage/Stylesheets/main.css").toExternalForm());
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();

        RootController rootController = fxmlLoader.getController();
        scene.addEventHandler(KeyEvent.ANY, rootController::handleKeyEvent);
    }

    public static void main(String[] args) {
        launch();
    }
}