package com.example.game2048.Pages.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GamePage {

    @FXML
    private HeaderController headerController;

    public void testAction(ActionEvent actionEvent) {
        System.out.println("Test Button was pressed!");
        headerController.setScore(1);
        headerController.setBest(1);
    }
}
