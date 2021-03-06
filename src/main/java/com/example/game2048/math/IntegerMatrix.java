package com.example.game2048.math;

import java.util.function.Supplier;

public class IntegerMatrix extends Matrix<Integer> {

    public IntegerMatrix(int rows, int columns, int fillValue) {
        super(rows, columns, fillValue);
    }

    public IntegerMatrix(int rows, int columns, Supplier<Integer> initializer) {
        super(rows, columns, initializer);
    }

    public IntegerMatrix(int rows, int columns, MatrixInitializer<Integer> initializer) {
        super(rows, columns, initializer);
    }

    public IntegerMatrix(Integer[][] source) {
        super(source);
    }

    public IntegerMatrix(IntegerMatrix other) {
        super(other);
    }
}
