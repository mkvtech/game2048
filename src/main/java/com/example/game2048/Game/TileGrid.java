package com.example.game2048.Game;

import com.example.game2048.Math.Vector;
import com.example.game2048.Utilities.Direction;
import com.example.game2048.Math.IntegerMatrix;

import java.util.stream.Stream;

public class TileGrid {

    private final TileMatrix tileMatrix;
    private final TileSpawner tileSpawner;

    public TileGrid(int rows, int columns) {
        this.tileMatrix = new TileMatrix(rows, columns);
        this.tileSpawner = new TileSpawner(tileMatrix);
    }

    public TileGrid(IntegerMatrix source) {
        this.tileMatrix = new TileMatrix(source);
        this.tileSpawner = new TileSpawner(tileMatrix);
    }

    public Stream<Tile> toFlatStream() {
        return this.tileMatrix.toFlatStream();
    }

    public int getValueAt(Vector position) {
        return tileMatrix.getValueAt(position);
    }

    public void push(Direction direction) {
        clearMerged();

        TileMatrixPusher pusher = new TileMatrixPusher(tileMatrix, direction);
        pusher.push();

        trySpawn();
    }

    public boolean canBePushed() {
        TileMatrixPushChecker checker = new TileMatrixPushChecker(tileMatrix);

        return checker.isPushable();
    }

    public Vector getSize() { return tileMatrix.getSize(); }

    public int getRows() { return tileMatrix.getRows(); }

    public int getColumns() { return tileMatrix.getColumns(); }

    public boolean areTileValuesEqual(TileGrid other) { return tileMatrix.areTileValuesEqual(other.tileMatrix); }

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
