package com.example.game2048.Controllers;

import com.example.game2048.Game.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Map;

public class HeaderController {

    @FXML
    private Text score;

    @FXML
    private Text best;

    @FXML
    private Button newGameButton;

    @FXML
    private Text gameStateText;

    private static final Map<GameState, String> GAME_STATE_TEXT_MAP = Map.of(
            GameState.IN_PROGRESS, "Join the tiles, get to 2048!",
            GameState.VICTORY, "You got to 2048, congrats! You can continue playing.",
            GameState.LOSS, "Game Over! You can try again."
    );

    public void setScore(int newScore) {
        score.setText("SCORE " + newScore);
    }

    public void setBest(int newBest) {
        best.setText("BEST " + newBest);
    }

    public void onNewGameButtonPressed(Runnable callback) {
        newGameButton.setOnAction(event -> callback.run());
    }

    public void updateText(GameState gameState) {
        gameStateText.setText(GAME_STATE_TEXT_MAP.get(gameState));
    }
}
