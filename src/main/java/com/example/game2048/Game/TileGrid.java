package com.example.game2048.Game;

import java.util.Arrays;

public class TileGrid {
    private final int sizeX;
    private final int sizeY;
    private final int[][] data;

    public TileGrid(int sizeX, int sizeY) {
        this.data = new int[sizeY][sizeX];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public TileGrid(int[][] source) {
        this.sizeY = source.length;
        this.sizeX = source[0].length;
        this.data = new int[this.sizeY][this.sizeX];

        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, this.data[i], 0, source[0].length);
        }
    }

    public int[] toFlatStream() {
        return Arrays.stream(this.data).flatMapToInt(Arrays::stream).toArray();
    }
}
