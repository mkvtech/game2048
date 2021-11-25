package com.example.game2048.Controllers;

import com.example.game2048.Game.Game;
import com.example.game2048.Game.GameState;
import com.example.game2048.Game.GameStats;
import com.example.game2048.patterns.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Map;

public class HeaderController implements Observer {

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
            GameState.ENDED, "Game Over! You can try again."
    );

    private Game game;

    public void setGame(Game game) {
        this.game = game;
        this.game.addObserver(this);
    }

    @Override
    public void update() {
        setScore(game.getCurrentScore());
        setBest(GameStats.getInstance().getBestScore());
        updateText(game.getGameState());
    }

    public void onNewGameButtonPressed(Runnable callback) {
        newGameButton.setOnAction(event -> callback.run());
    }

    private void setScore(int newScore) {
        score.setText("SCORE " + newScore);
    }

    private void setBest(int newBest) {
        best.setText("BEST " + newBest);
    }

    private void updateText(GameState gameState) {
        gameStateText.setText(GAME_STATE_TEXT_MAP.get(gameState));
    }
}
