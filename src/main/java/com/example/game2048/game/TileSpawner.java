package com.example.game2048.game;

import com.example.game2048.math.Vector;

import java.util.ArrayList;
import java.util.Random;

public class TileSpawner {

    private static final int[] SPAWNABLE_VALUES = { 2, 4 };

    private final Random generator = new Random();
    private final TileMatrix tileMatrix;

    public TileSpawner(TileMatrix tileMatrix) {
        this.tileMatrix = tileMatrix;
    }

    public void spawn() {
        int value = pickTileValue();
        Vector position = pickEmptyPosition();
        spawnTile(value, position);
    }

    private int pickTileValue() {
        int index =  generator.nextInt(SPAWNABLE_VALUES.length);
        return SPAWNABLE_VALUES[index];
    }

    private Vector pickEmptyPosition() {
        ArrayList<Vector> emptyPositions = getEmptyPositions();
        int index = generator.nextInt(emptyPositions.size());
        return emptyPositions.get(index);
    }

    private ArrayList<Vector> getEmptyPositions() {
        ArrayList<Vector> emptyPositions = new ArrayList<>();

        tileMatrix.forEachPosition((i, j) -> {
            if (tileMatrix.isEmptyAt(i, j)) {
                emptyPositions.add(new Vector(i, j));
            }
        });

        return emptyPositions;
    }

    private void spawnTile(int value, Vector position) {
        tileMatrix.set(position, new Tile(value));
    }
}
