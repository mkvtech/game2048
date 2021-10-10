package com.example.game2048.Game;

import com.example.game2048.Math.Vector;
import com.example.game2048.Utilities.Direction;
import com.example.game2048.Math.Matrix;
import com.example.game2048.Math.IntegerMatrix;

import java.util.function.Consumer;
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

    public boolean areTileValuesEqual(TileGrid other) {
        if (!this.tileMatrix.getSize().equals(other.tileMatrix.getSize())) {
            return false;
        }

        for (int i = 0; i < tileMatrix.getRows(); i++) {
            for (int j = 0; j < tileMatrix.getColumns(); j++) {
                Vector position = new Vector(i, j);

                if (this.getValueAt(position) != other.getValueAt(position)) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getValueAt(Vector position) {
        Tile tile = tileMatrix.get(position);

        return tile == null ? 0 : tile.getValue();
    }

    public void push(Direction direction) {
        clearMerged();

        iterateTowards(direction.rotateClockwise(), i -> {
            iterateTowards(direction.getOpposite(), j -> {
                Vector position = new Vector(i, j);

                if (tileMatrix.get(position) != null) {
                    pushTile(position, direction);
                }
            });
        });
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

    private void pushTile(Vector position, Direction direction) {
        Tile currentTile = tileMatrix.get(position);

        Vector farthestAvailablePosition = findFarthestAvailable(position, direction);
        Vector farthestOccupiedPosition = farthestAvailablePosition.next(direction);

        Tile farthestOccupiedTile = tileMatrix.tryGet(farthestOccupiedPosition);
        if (isMergeable(currentTile, farthestOccupiedTile)) {
            merge(position, farthestOccupiedTile);
        } else if (farthestAvailablePosition != position) { // isMovable
            tileMatrix.set(farthestAvailablePosition, currentTile); // move
            tileMatrix.erase(position);
        }
    }

    private Vector findFarthestAvailable(Vector origin, Direction direction) {
        Vector current = origin, next = origin.next(direction);

        while (true) {
            if (!tileMatrix.isInBounds(next) || tileMatrix.get(next) != null) {
                return current;
            }

            current = next;
            next = current.next(direction);
        }
    }

    private boolean isMergeable(Tile source, Tile destination) {
        return destination != null && destination.isMergeableWith(source);
    }

    private void merge(Vector sourcePosition, Tile destination) {
        Tile source = tileMatrix.get(sourcePosition);
        destination.merge(source);
        tileMatrix.erase(sourcePosition);
    }

    private void clearMerged() {
        this.tileMatrix.forEach(tile -> {
            if (tile != null) {
                tile.clearMerged();
            }
        });
    }
}
