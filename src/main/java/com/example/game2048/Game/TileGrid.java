package com.example.game2048.Game;

import com.example.game2048.Utilities.Direction;
import com.example.game2048.Math.IntegerMatrix;
import com.example.game2048.Math.BooleanMatrix;

import java.util.stream.Stream;

public class TileGrid {
    private final IntegerMatrix data;
    private final BooleanMatrix mergedMap;

    public TileGrid(int rows, int columns) {
        this.data = new IntegerMatrix(rows, columns, 0);
        this.mergedMap = new BooleanMatrix(rows, columns, false);
    }

    public TileGrid(IntegerMatrix source) {
        this.data = source;
        this.mergedMap = new BooleanMatrix(source.getRows(), source.getColumns(), false);
    }

    public Stream<Integer> toFlatStream() {
        return this.data.toFlatStream();
    }

    public void push(Direction direction) {
        // Will use 'direction' argument soon, stay tuned :>

        clearMergedMap();

        for (int i = 0; i < this.data.getRows(); i++) {
            pushRow(i);
        }
    }

    private void pushRow(int rowIndex) {
        for (int columnIndex = 0; columnIndex < this.data.getColumns(); columnIndex++) {
            if (this.data.get(rowIndex, columnIndex) != 0) {
                pushTile(rowIndex, columnIndex);
            }
        }
    }

    private void pushTile(int rowIndex, int tileIndex) {
        int currentTile = this.data.get(rowIndex, tileIndex);

        int farthestAvailableIndex = findFarthestAvailable(rowIndex, tileIndex);
        int farthestOccupiedIndex = farthestAvailableIndex - 1;

        if (farthestOccupiedIndex != -1 && // If inside grid
                !mergedMap.get(rowIndex, farthestOccupiedIndex) && // If it was not merged
                this.data.get(rowIndex, farthestOccupiedIndex) == currentTile) { // If tile values are the same
            this.data.set(rowIndex, farthestOccupiedIndex, currentTile * 2); // merge
            this.mergedMap.set(rowIndex, farthestOccupiedIndex, true);
            this.data.set(rowIndex, tileIndex, 0);
        } else if (farthestAvailableIndex != tileIndex) {
            this.data.set(rowIndex, farthestAvailableIndex, currentTile);
            this.data.set(rowIndex, tileIndex, 0);
        }
    }

    private int findFarthestAvailable(int rowIndex, int startIndex) {
        for (int i = startIndex; i > 0; i--) {
            if (this.data.get(rowIndex, i - 1) != 0) {
                return i;
            }
        }

        return 0;
    }

    private void clearMergedMap() {
        this.mergedMap.fill(false);
    }
}
