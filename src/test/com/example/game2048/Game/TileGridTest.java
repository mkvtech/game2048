package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import com.example.game2048.Utilities.Direction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileGridTest {
    @Test
    public void testSizeBasedConstructor() {
        TileGrid tileGrid = new TileGrid(2, 2);
        List<Integer> expected = tileGrid.toFlatStream().map(tile -> tile == null ? 0 : tile.getValue()).toList();
        assertEquals(expected, List.of(0, 0, 0, 0 ));
    }

    @Test
    public void testSourceBasedConstructor() {
        IntegerMatrix source = new IntegerMatrix(new Integer[][] { { 1, 2 }, { 3, 4 } });
        TileGrid tileGrid = new TileGrid(source);
        List<Integer> expected = tileGrid.toFlatStream().map(tile -> tile == null ? 0 : tile.getValue()).toList();
        assertEquals(expected, List.of(1, 2, 3, 4 ));
    }

    @Test
    public void testCanBePushed() {
        TileGrid tileGrid = new TileGrid(new IntegerMatrix(new Integer[][] {
                { 2, 4, 2, 4 },
                { 4, 2, 8, 2 },
                { 8, 4, 2, 4 },
                { 2, 8, 4, 2 }
        }));

        assertFalse(tileGrid.canBePushed());
    }

    @Test
    public void testPushDoesNothingWhenMatrixIsFull() {
        IntegerMatrix originalSource = new IntegerMatrix(new Integer[][] {
                { 4, 2, 4 },
                { 2, 4, 2 },
                { 4, 2, 4 }
        });

        TileGrid original = new TileGrid(originalSource);
        TileGrid expected = new TileGrid(originalSource);

        original.push(Direction.LEFT);

        assertTrue(original.areTileValuesEqual(expected));
    }
}