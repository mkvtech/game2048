package com.example.game2048.Pages.Game;

import com.example.game2048.Game.TileMatrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GamePage {

    @FXML
    private Button testButton;

    @FXML
    private HeaderController headerController;

    @FXML
    private BoardController boardController;

    public void testAction(ActionEvent actionEvent) {
        System.out.println("Test Button was pressed!");
        headerController.setScore(1);
        headerController.setBest(1);

        TileMatrix tileMatrix = new TileMatrix(6, 4);
        boardController.setTileMatrix(tileMatrix);
    }
}
