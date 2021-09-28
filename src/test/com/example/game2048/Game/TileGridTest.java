package com.example.game2048.Game;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        assertTrue(Arrays.deepEquals(tileGrid.to2dArray(), new int[][] { { 1, 2 }, { 3, 4 } }));
    }

    @Test
    public void testPushLeft() {
        TileGrid tileGrid = new TileGrid(new int[][] {
                { 2, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 2, 0, 2, 0 },
                { 0, 2, 0, 2 }
        });

        tileGrid.push(com.example.game2048.Utilities.Direction.LEFT);

        assertTrue(Arrays.deepEquals(tileGrid.to2dArray(), new int[][] {
                { 2, 0, 0, 0 },
                { 4, 0, 0, 0 },
                { 4, 0, 0, 0 },
                { 4, 0, 0, 0 }
        }));
    }

    @Test
    public void testPushTilesToEmptySlots() {
        TileGrid tileGrid = new TileGrid(new int[][] {
                { 2, 0, 0 },
                { 0, 2, 0 },
                { 0, 0, 2 }
        });

        tileGrid.push(com.example.game2048.Utilities.Direction.LEFT);

        assertTrue(Arrays.deepEquals(tileGrid.to2dArray(), new int[][] {
                { 2, 0, 0 },
                { 2, 0, 0 },
                { 2, 0, 0 }
        }));
    }

    @Test
    public void testPushTiles4IdenticalTiles() {

        TileGrid tileGrid = new TileGrid(new int[][] {
                { 2, 2, 2, 2 },
                { 4, 4, 4, 4 },
                { 4, 2, 4, 2 },
                { 2, 2, 4, 4 }
        });

        tileGrid.push(com.example.game2048.Utilities.Direction.LEFT);

        assertTrue(Arrays.deepEquals(tileGrid.to2dArray(), new int[][] {
                { 4, 4, 0, 0 },
                { 8, 8, 0, 0 },
                { 4, 2, 4, 2 },
                { 4, 8, 0, 0 }
        }));
    }
}