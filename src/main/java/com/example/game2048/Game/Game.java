package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import com.example.game2048.Utilities.Direction;

public class Game {

    private TileGrid tileGrid;
    private int bestScore = 0;
    private GameState gameState = GameState.IN_PROGRESS;

    public Game() {
        this.tileGrid = new TileGrid(4, 4);
    }

    public Game(IntegerMatrix valuesMatrix) {
        this.tileGrid = new TileGrid(valuesMatrix);
        updateState();
    }

    public boolean isPlayable() { return gameState == GameState.IN_PROGRESS || gameState == GameState.VICTORY; }

    public GameState getGameState() { return gameState; }

    public void push(Direction direction) {
        tileGrid.push(direction);
        updateScore();
        updateState();
    }

    public void restart() {
        tileGrid = new TileGrid(4, 4);
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
        if (!tileGrid.canBePushed()) {
            gameState = GameState.ENDED;
        } else if (tileGrid.containsValue(2048)) {
            gameState = GameState.VICTORY;
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
