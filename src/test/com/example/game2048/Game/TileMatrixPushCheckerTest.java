package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileMatrixPushCheckerTest {

    @Test
    public void testCheckWithAvailableSpaces() {
        TileMatrix tileMatrix = new TileMatrix(new IntegerMatrix(new Integer[][] {
                { 2, 0, 4 },
                { 4, 8, 16 },
                { 0, 2, 0 },
        }));

        TileMatrixPushChecker checker = new TileMatrixPushChecker(tileMatrix);

        assertTrue(checker.isPushable());
    }

    @Test
    public void testCheckWithoutAvailableSpacesWithMergeableTiles() {
        TileMatrix tileMatrix = new TileMatrix(new IntegerMatrix(new Integer[][] {
                { 2, 2, 4 },
                { 4, 8, 16 },
                { 4, 2, 16 },
        }));

        TileMatrixPushChecker checker = new TileMatrixPushChecker(tileMatrix);

        assertTrue(checker.isPushable());
    }

    @Test
    public void testCheckWithoutEmptyOrMergeableTiles() {
        TileMatrix tileMatrix = new TileMatrix(new IntegerMatrix(new Integer[][] {
                { 2, 32, 4 },
                { 4, 8, 16 },
                { 8, 2, 4 },
        }));

        TileMatrixPushChecker checker = new TileMatrixPushChecker(tileMatrix);

        assertFalse(checker.isPushable());
    }
}