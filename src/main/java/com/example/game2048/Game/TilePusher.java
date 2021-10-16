package com.example.game2048.Game;

import com.example.game2048.Math.Vector;
import com.example.game2048.Utilities.Direction;

public class TilePusher {

    private final TileMatrix tileMatrix;
    private final Direction direction;
    private final Vector position;
    private final Tile tile;

    private int score = 0;

    public TilePusher(TileMatrix tileMatrix, Direction direction, Vector position) {
        this.tileMatrix = tileMatrix;
        this.direction = direction;
        this.position = position;
        this.tile = tileMatrix.get(position);
    }

    public void pushTile() {
        Vector farthestAvailablePosition = findFarthestAvailable();
        Vector farthestOccupiedPosition = farthestAvailablePosition.next(direction);

        Tile farthestOccupiedTile = tileMatrix.tryGet(farthestOccupiedPosition);

        if (isMergeableWith(farthestOccupiedTile)) {
            mergeWith(farthestOccupiedTile);
        } else if (farthestAvailablePosition != position) {
            moveTo(farthestAvailablePosition);
        }
    }

    public int getScore() {
        return score;
    }

    private Vector findFarthestAvailable() {
        Vector current = position;
        Vector next = position.next(direction);

        while (true) {
            if (!tileMatrix.isInBounds(next) || tileMatrix.get(next) != null) {
                return current;
            }

            current = next;
            next = current.next(direction);
        }
    }

    private boolean isMergeableWith(Tile destination) {
        return destination != null && destination.isMergeableWith(tile);
    }

    private void mergeWith(Tile destination) {
        destination.merge(tile);
        erase();
        score = destination.getValue();
    }

    private void moveTo(Vector destination) {
        tileMatrix.set(destination, tile);
        erase();
    }

    private void erase() {
        tileMatrix.erase(position);
    }
}
