package com.example.game2048.Game;

import com.example.game2048.Utilities.Direction;

public class Game {

    private TileGrid tileGrid = new TileGrid(4, 4);
    private int bestScore = 0;
    private GameState gameState = GameState.IN_PROGRESS;

    public GameState getGameState() { return gameState; }

    public void push(Direction direction) {
        tileGrid.push(direction);
        updateScore();
        updateState();
    }

    public void restart() {
        tileGrid = new TileGrid(4, 4);
        updateState();
        updateState();
    }

    public TileGrid getTileGrid() {
        return tileGrid;
    }

    public int getCurrentScore() {
        return tileGrid.getScore();
    }

    public int getBestScore() {
        return bestScore;
    }

    private void updateState() {
        if (tileGrid.containsValue(2048)) {
            gameState = GameState.VICTORY;
        } else if (!tileGrid.canBePushed()) {
            gameState = GameState.LOSS;
        } else {
            gameState = GameState.IN_PROGRESS;
        }
    }

    private void updateScore() {
        int currentScore = tileGrid.getScore();

        if (currentScore > bestScore) {
            bestScore = currentScore;
        }
    }
}
