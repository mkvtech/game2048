package com.example.game2048.Controllers;

import com.example.game2048.Game.Game;
import com.example.game2048.Game.GameStats;
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
        boardController.setGame(game);
        headerController.onNewGameButtonPressed(this::handleNewGameButtonPress);
        headerController.setBest(GameStats.getInstance().getBestScore());
    }

    public void handleKeyEvent(KeyEvent event) {
        if (game.isPlayable()) {
            Direction direction = KEYBOARD_CONTROLS.get(event.getCode());

            if (direction != null) {
                handlePush(direction);
            }
        }
    }

    private void handlePush(Direction direction) {
        game.push(direction);
        headerController.setScore(game.getCurrentScore());
        headerController.setBest(GameStats.getInstance().getBestScore());
        headerController.updateText(game.getGameState());
    }

    public void handleNewGameButtonPress() {
        game.restart();
        headerController.setScore(0);
        headerController.updateText(game.getGameState());
    }
}
