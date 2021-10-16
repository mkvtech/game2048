package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import com.example.game2048.Utilities.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileMatrixPusherTest {

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

        assertEqualsWhenPushedInAllDirections(original, expected, 0);
    }

    @Test
    public void testPushAndMerge() {
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

        assertEqualsWhenPushedInAllDirections(original, expected, 12);
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

        assertEqualsWhenPushedInAllDirections(original, expected, 36);
    }

    private void assertEqualsWhenPushedInAllDirections(IntegerMatrix original, IntegerMatrix expected, int score) {
        IntegerMatrix originalCopied = new IntegerMatrix(original);
        IntegerMatrix expectedCopied = new IntegerMatrix(expected);

        Direction.fromUpClockwise.forEach(direction -> {
            originalCopied.rotateClockwiseInPlace();
            expectedCopied.rotateClockwiseInPlace();

            TileMatrix tileMatrix = new TileMatrix(originalCopied);

            TileMatrixPusher pusher = new TileMatrixPusher(tileMatrix, direction);
            pusher.push();

            TileMatrix expectedTileGrid = new TileMatrix(expectedCopied);

            assertTrue(tileMatrix.areTileValuesEqual(expectedTileGrid));
            assertEquals(pusher.getScore(), score);
        });
    }
}