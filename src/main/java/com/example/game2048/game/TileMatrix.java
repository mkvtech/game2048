package com.example.game2048.game;

import com.example.game2048.math.IntegerMatrix;
import com.example.game2048.math.Matrix;
import com.example.game2048.math.Vector;

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

    public boolean areTileValuesEqual(TileMatrix other) {
        if (!this.getSize().equals(other.getSize())) {
            return false;
        }

        return this.allMatch((position) -> this.getValueAt(position) == other.getValueAt(position));
    }

    public int getValueAt(Vector position) {
        Tile tile = get(position);

        return tile == null ? 0 : tile.getValue();
    }
}
