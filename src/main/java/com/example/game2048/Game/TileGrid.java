package com.example.game2048.Game;

import com.example.game2048.Math.Vector;
import com.example.game2048.Utilities.Direction;
import com.example.game2048.Math.IntegerMatrix;

import java.util.stream.Stream;

public class TileGrid {

    private final TileMatrix tileMatrix;

    public TileGrid(int rows, int columns) {
        this.tileMatrix = new TileMatrix(rows, columns);
    }

    public TileGrid(IntegerMatrix source) {
        this.tileMatrix = new TileMatrix(source);
    }

    public Stream<Tile> toFlatStream() {
        return this.tileMatrix.toFlatStream();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;

        TileGrid otherTileGrid = (TileGrid) obj;

        return this.tileMatrix.equals(otherTileGrid.tileMatrix);
    }

    public boolean areTileValuesEqual(TileGrid other) {
        if (!this.tileMatrix.getSize().equals(other.tileMatrix.getSize())) {
            return false;
        }

        return tileMatrix.allMatch((position) -> this.getValueAt(position) == other.getValueAt(position));
    }

    public int getValueAt(Vector position) {
        Tile tile = tileMatrix.get(position);

        return tile == null ? 0 : tile.getValue();
    }

    public void push(Direction direction) {
        clearMerged();

        TileMatrixPusher pusher = new TileMatrixPusher(tileMatrix, direction);

        pusher.push();
    }

    public boolean canBePushed() {
        TileMatrixPushChecker checker = new TileMatrixPushChecker(tileMatrix);

        return checker.isPushable();
    }

    public Vector getSize() { return tileMatrix.getSize(); }

    public int getRows() { return tileMatrix.getRows(); }

    public int getColumns() { return tileMatrix.getColumns(); }

    private void clearMerged() {
        this.tileMatrix.forEach(tile -> {
            if (tile != null) {
                tile.clearMerged();
            }
        });
    }
}
