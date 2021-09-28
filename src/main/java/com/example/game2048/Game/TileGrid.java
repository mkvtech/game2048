package com.example.game2048.Game;

import com.example.game2048.Utilities.Direction;

import java.util.Arrays;

public class TileGrid {
    private final int sizeX;
    private final int sizeY;
    private final int[][] data;
    private final boolean[][] mergedMap;

    public TileGrid(int sizeX, int sizeY) {
        this.data = new int[sizeY][sizeX];
        this.mergedMap = new boolean[sizeY][sizeX];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public TileGrid(int[][] source) {
        this.sizeY = source.length;
        this.sizeX = source[0].length;
        this.data = new int[this.sizeY][this.sizeX];
        this.mergedMap = new boolean[this.sizeY][this.sizeX];

        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, this.data[i], 0, source[0].length);
        }

        clearMergedMap();
    }

    public int[] toFlatStream() {
        return Arrays.stream(this.data).flatMapToInt(Arrays::stream).toArray();
    }

    public int[][] to2dArray() {
        return com.example.game2048.Utilities.Arrays.copyIntArray2d(this.data);
    }

    public void push(Direction direction) {
        // Will use 'direction' argument soon, stay tuned :>

        clearMergedMap();

        for (int i = 0; i < this.data.length; i++) {
            pushRow(this.data[i], this.mergedMap[i]);
        }
    }

    private void pushRow(int[] row, boolean[] mergedMapRow) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0) {
                pushTile(row, mergedMapRow, i);
            }
        }
    }

    private void pushTile(int[] row, boolean[] mergedMapRow, int tileIndex) {
        int currentTile = row[tileIndex];

        int farthestAvailableIndex = findFarthestAvailable(row, tileIndex);
        int farthestOccupiedIndex = farthestAvailableIndex - 1;

        if (farthestOccupiedIndex != -1 && // If inside grid
                !mergedMapRow[farthestOccupiedIndex] && // If it was not merged
                row[farthestOccupiedIndex] == currentTile) { // If tile values are the same
            row[farthestOccupiedIndex] = currentTile * 2; // merge
            mergedMapRow[farthestOccupiedIndex] = true;
            row[tileIndex] = 0;
        } else if (farthestAvailableIndex != tileIndex) {
            row[farthestAvailableIndex] = currentTile;
            row[tileIndex] = 0;
        }

    }

    private int findFarthestAvailable(int[] row, int startIndex) {
        for (int i = startIndex; i > 0; i--) {
            if (row[i - 1] != 0) {
                return i;
            }
        }

        return 0;
    }

    private void clearMergedMap() {
        for (int i = 0; i < mergedMap.length; i++) {
            for (int j = 0; j < mergedMap[i].length; j++) {
                this.mergedMap[i][j] = false;
            }
        }
    }
}
