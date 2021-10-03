package com.example.game2048.Utilities;

import java.util.List;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static final List<Direction> fromUpClockwise = List.of(UP, RIGHT, DOWN, LEFT);
}
