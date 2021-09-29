package com.example.game2048.Math;

import java.util.function.Supplier;

public class BooleanMatrix extends Matrix<Boolean> {

    public BooleanMatrix(int rows, int columns, boolean fillValue) {
        super(rows, columns, fillValue);
    }

    public BooleanMatrix(int rows, int columns, Supplier<Boolean> initializer) {
        super(rows, columns, initializer);
    }

    public BooleanMatrix(int rows, int columns, MatrixInitializer<Boolean> initializer) {
        super(rows, columns, initializer);
    }

    public BooleanMatrix(Boolean[][] source) {
        super(source);
    }
}
