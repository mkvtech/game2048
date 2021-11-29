package com.example.game2048.utilities;

import com.example.game2048.math.Axis;
import com.example.game2048.math.Vector;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum Direction {
    UP(Axis.I, new Vector(-1, 0), -1),
    RIGHT(Axis.J, new Vector(0, 1), 1),
    DOWN(Axis.I, new Vector(1, 0), 1),
    LEFT(Axis.J, new Vector(0, -1), -1);

    private final Axis axis;
    private final Vector vectorRepresentation;
    private final int delta;

    Direction(Axis axis, Vector vectorRepresentation, int delta) {
        this.axis = axis;
        this.vectorRepresentation = vectorRepresentation;
        this.delta = delta;
    }

    public static final List<Direction> fromUpClockwise = List.of(UP, RIGHT, DOWN, LEFT);

    public static final EnumMap<Direction, Direction> opposites = new EnumMap<>(Map.of(
            UP, DOWN,
            DOWN, UP,
            RIGHT, LEFT,
            LEFT, RIGHT
    ));

    public Vector toVector() { return vectorRepresentation; }

    public Direction getOpposite() { return opposites.get(this); }

    public int getDelta() { return delta; }

    public Axis getAxis() { return axis; }

    public Direction rotateClockwise() {
        return fromUpClockwise.get((fromUpClockwise.indexOf(this) + 1) % fromUpClockwise.size());
    }

    public boolean isPositive() { return this.delta == 1; }
}
