package com.example.game2048.Game;

import com.example.game2048.Math.Vector;
import com.example.game2048.Utilities.Direction;

import java.util.function.Consumer;

public class TileMatrixPusher {

    private final TileMatrix tileMatrix;
    private final Direction direction;

    public TileMatrixPusher(TileMatrix tileMatrix, Direction direction) {
        this.tileMatrix = tileMatrix;
        this.direction = direction;
    }

    public void push() {
        forEachPosition(position -> {
            if (!tileMatrix.isEmptyAt(position)) {
                pushTile(position);
            }
        });
    }

    private void forEachPosition(Consumer<Vector> callback) {
        forEachRow(i -> {
            forEachColumn(j -> {
                callback.accept(new Vector(i, j));
            });
        });
    }

    private void forEachRow(Consumer<Integer> callback) {
        iterateTowards(direction.rotateClockwise(), callback);
    }

    private void forEachColumn(Consumer<Integer> callback) {
        iterateTowards(direction.getOpposite(), callback);
    }

    private void iterateTowards(Direction direction, Consumer<Integer> callback) {
        int matrixSizeOnAxis = tileMatrix.getSize().getAxisValue(direction.getAxis());

        int start, end;

        if (direction.isPositive()) {
            start = 0;
            end = matrixSizeOnAxis;
        } else {
            start = matrixSizeOnAxis - 1;
            end = -1;
        }

        for (int i = start; i != end; i += direction.getDelta()) {
            callback.accept(i);
        }
    }

    private void pushTile(Vector position) {
        TilePusher tilePusher = new TilePusher(tileMatrix, direction, position);

        tilePusher.pushTile();
    }
}
