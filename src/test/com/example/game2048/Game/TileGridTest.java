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
    public void testPushLeft() {
        IntegerMatrix original = new IntegerMatrix(new Integer[][] {
                { 2, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 2, 0, 2, 0 },
                { 0, 2, 0, 2 }
        });

        IntegerMatrix expected = new IntegerMatrix(new Integer[][] {
                { 2, 0, 0, 0 },
                { 4, 0, 0, 0 },
                { 4, 0, 0, 0 },
                { 4, 0, 0, 0 }
        });

        assertEqualsWhenPushedInAllDirections(original, expected);
    }

    @Test
    public void testPushTilesToEmptySlots() {
        IntegerMatrix original = new IntegerMatrix(new Integer[][] {
                { 2, 0, 0 },
                { 0, 2, 0 },
                { 0, 0, 2 }
        });

        IntegerMatrix expected = new IntegerMatrix(new Integer[][] {
                { 2, 0, 0 },
                { 2, 0, 0 },
                { 2, 0, 0 }
        });

        assertEqualsWhenPushedInAllDirections(original, expected);
    }

    @Test
    public void testPushTiles4IdenticalTiles() {
        IntegerMatrix original = new IntegerMatrix(new Integer[][] {
                { 2, 2, 2, 2 },
                { 4, 4, 4, 4 },
                { 4, 2, 4, 2 },
                { 2, 2, 4, 4 }
        });

        IntegerMatrix expected = new IntegerMatrix(new Integer[][] {
                { 4, 4, 0, 0 },
                { 8, 8, 0, 0 },
                { 4, 2, 4, 2 },
                { 4, 8, 0, 0 }
        });

        assertEqualsWhenPushedInAllDirections(original, expected);
    }

    @Test
    public void testPushInEveryDirection() {
        IntegerMatrix original = new IntegerMatrix(new Integer[][] {
                { 2, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 2, 0, 2, 0 },
                { 0, 2, 0, 2 }
        });

        IntegerMatrix expected = new IntegerMatrix(new Integer[][] {
                { 2, 0, 0, 0 },
                { 4, 0, 0, 0 },
                { 4, 0, 0, 0 },
                { 4, 0, 0, 0 }
        });

        assertEqualsWhenPushedInAllDirections(original, expected);
    }

    private void assertEqualsWhenPushedInAllDirections(IntegerMatrix original, IntegerMatrix expected) {
        IntegerMatrix originalCopied = new IntegerMatrix(original);
        IntegerMatrix expectedCopied = new IntegerMatrix(expected);

        Direction.fromUpClockwise.forEach(direction -> {
            originalCopied.rotateClockwiseInPlace();
            expectedCopied.rotateClockwiseInPlace();

            TileGrid tileGrid = new TileGrid(originalCopied);
            tileGrid.push(direction);

            TileGrid expectedTileGrid = new TileGrid(expectedCopied);

            assertTrue(tileGrid.areTileValuesEqual(expectedTileGrid));
        });
    }
}