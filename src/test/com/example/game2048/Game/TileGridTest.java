package com.example.game2048.Game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileGridTest {
    @Test
    public void testSizeBasedConstructor() {
        TileGrid tileGrid = new TileGrid(2, 2);
        assertArrayEquals(tileGrid.toFlatStream(), new int[] { 0, 0, 0, 0 });
    }

    @Test
    public void testSourceBasedConstructor() {
        int[][] source = new int[][] { { 1, 2 }, { 3, 4 } };
        TileGrid tileGrid = new TileGrid(source);
        assertArrayEquals(tileGrid.toFlatStream(), new int[] { 1, 2, 3, 4 });
    }
}