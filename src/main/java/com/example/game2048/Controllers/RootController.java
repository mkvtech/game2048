package com.example.game2048.Controllers;

import com.example.game2048.Game.Game;
import com.example.game2048.Game.GameState;
import com.example.game2048.Utilities.Direction;
import javafx.fxml.FXML;
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
    private HeaderController headerController;

    @FXML
    private BoardController boardController;

    private final Game game = new Game();

    public void initialize() {
        boardController.setTileGrid(game.getTileGrid());
        headerController.onNewGameButtonPressed(this::handleNewGameButtonPress);
    }

    public void handleKeyEvent(KeyEvent event) {
        if (game.getGameState() == GameState.IN_PROGRESS) {
            Direction direction = KEYBOARD_CONTROLS.get(event.getCode());

            if (direction != null) {
                handlePush(direction);
            }
        }
    }

    private void handlePush(Direction direction) {
        game.push(direction);
        boardController.updateGrid();
        headerController.setScore(game.getCurrentScore());
        headerController.setBest(game.getBestScore());
        headerController.updateText(game.getGameState());
    }

    public void handleNewGameButtonPress() {
        game.restart();
        boardController.setTileGrid(game.getTileGrid());
        headerController.setScore(0);
    }
}
