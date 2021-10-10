package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import com.example.game2048.Math.Matrix;

public class TileMatrix extends Matrix<Tile> {

    public TileMatrix(int rows, int columns) {
        super(rows, columns, (Tile) null);
    }

    public TileMatrix(IntegerMatrix source) {
        super(source.getRows(), source.getColumns(), (i, j) -> {
            int value = source.get(i, j);
            return value == 0 ? null : new Tile(value);
        });
    }
}
