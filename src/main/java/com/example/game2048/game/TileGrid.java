package com.example.game2048.game;

import com.example.game2048.math.Vector;
import com.example.game2048.utilities.Direction;
import com.example.game2048.math.IntegerMatrix;

import java.util.stream.Stream;

public class TileGrid {

    private final TileMatrix tileMatrix;
    private final TileSpawner tileSpawner;

    private int score = 0;

    public TileGrid(int rows, int columns) {
        this.tileMatrix = new TileMatrix(rows, columns);
        this.tileSpawner = new TileSpawner(tileMatrix);

        tileSpawner.spawn();
        tileSpawner.spawn();
    }

    public TileGrid(IntegerMatrix source) {
        this.tileMatrix = new TileMatrix(source);
        this.tileSpawner = new TileSpawner(tileMatrix);
    }

    public Stream<Tile> toFlatStream() {
        return this.tileMatrix.toFlatStream();
    }

    public int getValueAt(int i, int j) { return tileMatrix.getValueAt(new Vector(i, j)); }

    public int getValueAt(Vector position) {
        return tileMatrix.getValueAt(position);
    }

    public void push(Direction direction) {
        clearMerged();

        TileMatrixPusher pusher = new TileMatrixPusher(tileMatrix, direction);
        pusher.push();
        score += pusher.getScore();

        trySpawn();
    }

    public boolean canBePushed() {
        TileMatrixPushChecker checker = new TileMatrixPushChecker(tileMatrix);

        return checker.isPushable();
    }

    public int getScore() { return score; }

    public Vector getSize() { return tileMatrix.getSize(); }

    public int getRows() { return tileMatrix.getRows(); }

    public int getColumns() { return tileMatrix.getColumns(); }

    public boolean areTileValuesEqual(TileGrid other) { return tileMatrix.areTileValuesEqual(other.tileMatrix); }

    public boolean containsValue(int value) {
        return tileMatrix.toFlatStream().anyMatch(tile -> tile != null && tile.getValue() == value);
    }

    private void clearMerged() {
        this.tileMatrix.forEach(tile -> {
            if (tile != null) {
                tile.clearMerged();
            }
        });
    }

    private void trySpawn() {
        if (tileMatrix.atLeastOneMatch(tileMatrix::isEmptyAt)) {
            tileSpawner.spawn();
        }
    }
}
