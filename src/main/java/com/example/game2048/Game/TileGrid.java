package com.example.game2048.Game;

import com.example.game2048.Utilities.Direction;
import com.example.game2048.Math.Matrix;
import com.example.game2048.Math.IntegerMatrix;

import java.util.stream.Stream;

public class TileGrid {
    private final Matrix<Tile> tileMatrix;

    public TileGrid(int rows, int columns) {
        this.tileMatrix = new Matrix<>(rows, columns, () -> null);
    }

    public TileGrid(IntegerMatrix source) {
        this.tileMatrix = new Matrix<>(source.getRows(), source.getColumns(), (i, j) -> {
            int value = source.get(i, j);
            return value == 0 ? null : new Tile(value);
        });
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

    public void push(Direction direction) {
        // Will use 'direction' argument soon, stay tuned :>

        clearMerged();

        for (int i = 0; i < this.tileMatrix.getRows(); i++) {
            pushRow(i);
        }

        clearMerged();
    }

    private void pushRow(int rowIndex) {
        for (int columnIndex = 0; columnIndex < this.tileMatrix.getColumns(); columnIndex++) {
            if (this.tileMatrix.get(rowIndex, columnIndex) != null) {
                pushTile(rowIndex, columnIndex);
            }
        }
    }

    private void pushTile(int rowIndex, int tileIndex) {
        Tile currentTile = this.tileMatrix.get(rowIndex, tileIndex);

        int farthestAvailableIndex = findFarthestAvailable(rowIndex, tileIndex);
        int farthestOccupiedIndex = farthestAvailableIndex - 1;

        Tile farthestOccupiedTile = tileMatrix.tryGet(rowIndex, farthestOccupiedIndex);
        if (isMergeable(currentTile, farthestOccupiedTile)) {
            merge(rowIndex, tileIndex, farthestOccupiedTile);
        } else if (farthestAvailableIndex != tileIndex) { // isMovable
            tileMatrix.set(rowIndex, farthestAvailableIndex, currentTile); // move
            tileMatrix.erase(rowIndex, tileIndex);
        }
    }

    private int findFarthestAvailable(int rowIndex, int startIndex) {
        for (int i = startIndex; i > 0; i--) {
            if (this.tileMatrix.get(rowIndex, i - 1) != null) {
                return i;
            }
        }

        return 0;
    }

    private boolean isMergeable(Tile source, Tile destination) {
        return destination != null && destination.isMergeableWith(source);
    }

    private void merge(int sourceRow, int sourceColumn, Tile destination) {
        Tile source = tileMatrix.get(sourceRow, sourceColumn);
        destination.merge(source);
        tileMatrix.erase(sourceRow, sourceColumn);
    }

    private void clearMerged() {
        this.tileMatrix.forEach(tile -> {
            if (tile != null) {
                tile.clearMerged();
            }
        });
    }
}
