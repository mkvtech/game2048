package com.example.game2048.game;

import com.example.game2048.math.IntegerMatrix;
import com.example.game2048.utilities.Direction;
import com.example.game2048.patterns.observer.Observer;
import com.example.game2048.patterns.observer.Subject;

public class Game {

    private Subject subject = new Subject();
    private TileGrid tileGrid;
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

    public void addObserver(Observer observer) {
        subject.addObserver(observer);
    }

    private void updateState() {
        if (!tileGrid.canBePushed()) {
            gameState = GameState.ENDED;
        } else if (tileGrid.containsValue(2048)) {
            gameState = GameState.VICTORY;
        } else {
            gameState = GameState.IN_PROGRESS;
        }

        subject.update();
    }

    private void updateScore() {
        GameStats.getInstance().updateBestScore(tileGrid.getScore());
    }
}
