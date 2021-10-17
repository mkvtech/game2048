package com.example.game2048.Game;

import com.example.game2048.Math.IntegerMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void testGameStateIsInProgressWhenMatrixIsNotFull() {
        Game game = new Game(new IntegerMatrix(new Integer[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        }));

        assertEquals(game.getGameState(), GameState.IN_PROGRESS);
        assertTrue(game.isPlayable());
    }

    @Test
    public void testGameStateIsEndedWhenMatrixIsFull() {
        Game game = new Game(new IntegerMatrix(new Integer[][] {
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 },
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 }
        }));

        assertEquals(game.getGameState(), GameState.ENDED);
        assertFalse(game.isPlayable());
    }

    @Test
    public void testGameStateIsVictoryWhenContains2048() {
        Game game = new Game(new IntegerMatrix(new Integer[][] {
                { 2048, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        }));

        assertEquals(game.getGameState(), GameState.VICTORY);
        assertTrue(game.isPlayable());
    }

    @Test
    public void testGameStateIsEndedWhenIsFullAndContains2048() {
        Game game = new Game(new IntegerMatrix(new Integer[][] {
                { 2048, 4, 2, 4 },
                { 4, 2, 4, 2 },
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 }
        }));

        assertEquals(game.getGameState(), GameState.ENDED);
        assertFalse(game.isPlayable());
    }
}