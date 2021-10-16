package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileSpawnerTest {

    @Test
    public void testSpawnsATileWhenMatrixHasEmptySpaces() {
        TileMatrix tileMatrix = new TileMatrix(new IntegerMatrix(new Integer[][] {
                { 0, 2, 0 },
                { 0, 0, 0 },
                { 0, 2, 0 }
        }));

        TileSpawner tileSpawner = new TileSpawner(tileMatrix);
        tileSpawner.spawn();

        int numberOfNonEmptyTiles = (int) tileMatrix
                .toFlatStream()
                .map(tile -> tile == null ? 0 : tile.getValue())
                .filter(value -> value == 2 || value == 4)
                .count();

        assertEquals(numberOfNonEmptyTiles, 3);
    }
}