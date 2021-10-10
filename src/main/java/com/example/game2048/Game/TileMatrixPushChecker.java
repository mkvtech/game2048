package com.example.game2048.Game;

public class TileMatrixPushChecker {

    private final TileMatrix tileMatrix;

    public TileMatrixPushChecker(TileMatrix tileMatrix) {
        this.tileMatrix = tileMatrix;
    }

    public boolean isPushable() {
        return tileMatrix.atLeastOneMatch(this::isEmptyOrMergeable);
    }

    private boolean isEmptyOrMergeable(int i, int j) {
        Tile tile = tileMatrix.get(i, j);

        return tile == null || isSameValueDownOrRight(i, j, tile.getValue());
    }

    private boolean isSameValueDownOrRight(int i, int j, int value) {
        return isSameValue(i + 1, j, value) || isSameValue(i, j + 1, value);
    }

    private boolean isSameValue(int i, int j, int value) {
        if (i >= tileMatrix.getRows() || j >= tileMatrix.getColumns()) {
            return false;
        }

        Tile tile = tileMatrix.get(i, j);
        return tile != null && tile.getValue() == value;
    }
}
