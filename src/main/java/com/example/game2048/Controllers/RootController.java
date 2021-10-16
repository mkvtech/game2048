package com.example.game2048.Controllers;

import com.example.game2048.Game.TileGrid;
import com.example.game2048.Math.IntegerMatrix;
import com.example.game2048.Utilities.Direction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Map;

public class RootController {

    private static final Map<KeyCode, Direction> KEYBOARD_CONTROLS = Map.of(
            KeyCode.UP, Direction.UP,
            KeyCode.DOWN, Direction.DOWN,
            KeyCode.RIGHT, Direction.RIGHT,
            KeyCode.LEFT, Direction.LEFT
    );

    @FXML
    private Button testButton;

    @FXML
    private HeaderController headerController;

    @FXML
    private BoardController boardController;

    private final TileGrid tileGrid = new TileGrid(new IntegerMatrix(new Integer[][] {
            { 2, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
    }));

    public void initialize() {
        boardController.setTileGrid(tileGrid);
    }

    public void testAction(ActionEvent actionEvent) {
        System.out.println("Test Button was pressed!");
        headerController.setScore(1);
        headerController.setBest(1);
    }

    public void handleKeyEvent(KeyEvent event) {
        Direction direction =  KEYBOARD_CONTROLS.get(event.getCode());

        if (direction != null) {
            tileGrid.push(direction);
            boardController.updateGrid();
        }
    }
}
