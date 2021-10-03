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
        TileGrid tileGrid = new TileGrid(new IntegerMatrix(new Integer[][] {
                { 2, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 2, 0, 2, 0 },
                { 0, 2, 0, 2 }
        }));

        tileGrid.push(Direction.LEFT);

        List<Integer> expected = tileGrid.toFlatStream().map(tile -> tile == null ? 0 : tile.getValue()).toList();
        assertEquals(expected, List.of(
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

        tileGrid.push(Direction.LEFT);

        List<Integer> expected = tileGrid.toFlatStream().map(tile -> tile == null ? 0 : tile.getValue()).toList();
        assertEquals(expected, List.of(
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

        tileGrid.push(Direction.LEFT);

        TileGrid expected = new TileGrid(new IntegerMatrix(new Integer[][] {
                { 4, 4, 0, 0 },
                { 8, 8, 0, 0 },
                { 4, 2, 4, 2 },
                { 4, 8, 0, 0 }
        }));

        assertEquals(tileGrid, expected);
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

        Direction.fromUpClockwise.forEach(direction -> {
            original.rotateClockwiseInPlace();
            expected.rotateClockwiseInPlace();

            TileGrid tileGrid = new TileGrid(original);
            tileGrid.push(direction);

            TileGrid expectedTileGrid = new TileGrid(expected);

            assertEquals(tileGrid, expectedTileGrid);
        });
    }
}