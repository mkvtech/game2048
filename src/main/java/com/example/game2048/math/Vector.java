package com.example.game2048.math;

import com.example.game2048.utilities.Direction;

public class Vector {

    private final int i;
    private final int j;

    public Vector(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;

        Vector otherVector = (Vector) obj;

        return i == otherVector.i && j == otherVector.j;
    }

    @Override
    public String toString() {
        return "(" + i + ", " + j + ")";
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getAxisValue(Axis axis) { return axis == Axis.I ? i : j; }

    public Vector add(Vector other) {
        return new Vector(i + other.i, j + other.j);
    }

    public Vector next(Direction direction) { return add(direction.toVector()); }

    public Vector swap() { return new Vector(j, i); }
}
