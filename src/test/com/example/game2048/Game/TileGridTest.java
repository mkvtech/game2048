package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileGridTest {
    @Test
    public void testSizeBasedConstructor() {
        TileGrid tileGrid = new TileGrid(2, 2);
        assertEquals(tileGrid.toFlatStream().toList(), List.of(0, 0, 0, 0 ));
    }

    @Test
    public void testSourceBasedConstructor() {
        IntegerMatrix source = new IntegerMatrix(new Integer[][] { { 1, 2 }, { 3, 4 } });
        TileGrid tileGrid = new TileGrid(source);
        assertEquals(tileGrid.toFlatStream().toList(), List.of(1, 2, 3, 4 ));
    }

    @Test
    public void testPushLeft() {
        TileGrid tileGrid = new TileGrid(new IntegerMatrix(new Integer[][] {
                { 2, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 2, 0, 2, 0 },
                { 0, 2, 0, 2 }
        }));

        tileGrid.push(com.example.game2048.Utilities.Direction.LEFT);

        assertEquals(tileGrid.toFlatStream().toList(), List.of(
                2, 0, 0, 0,
                4, 0, 0, 0,
                4, 0, 0, 0,
                4, 0, 0, 0
        ));
    }

    @Test
    public void testPushTilesToEmptySlots() {
        TileGrid tileGrid = new TileGrid(new IntegerMatrix(new Integer[][] {
                { 2, 0, 0 },
                { 0, 2, 0 },
                { 0, 0, 2 }
        }));

        tileGrid.push(com.example.game2048.Utilities.Direction.LEFT);

        assertEquals(tileGrid.toFlatStream().toList(), List.of(
                2, 0, 0,
                2, 0, 0,
                2, 0, 0
        ));
    }

    @Test
    public void testPushTiles4IdenticalTiles() {
        TileGrid tileGrid = new TileGrid(new IntegerMatrix(new Integer[][] {
                { 2, 2, 2, 2 },
                { 4, 4, 4, 4 },
                { 4, 2, 4, 2 },
                { 2, 2, 4, 4 }
        }));

        tileGrid.push(com.example.game2048.Utilities.Direction.LEFT);

        assertEquals(tileGrid.toFlatStream().toList(), List.of(
                4, 4, 0, 0,
                8, 8, 0, 0,
                4, 2, 4, 2,
                4, 8, 0, 0
        ));
    }
}