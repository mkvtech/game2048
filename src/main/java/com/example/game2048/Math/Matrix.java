package com.example.game2048.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix<T> {
    private final int rows;
    private final int columns;
    private final List<T> data;

    // TODO: Special case when rows == 0, columns == 0 or both are == 0
    public Matrix(int rows, int columns, Supplier<T> initializer) {
        this.rows = rows;
        this.columns = columns;
        this.data = Stream.generate(initializer).limit(this.getContainerSize()).collect(Collectors.toList());
    }

    // TODO: Special case when rows == 0, columns == 0 or both are == 0
    public Matrix(int rows, int columns, MatrixInitializer<T> initializer) {
        this.rows = rows;
        this.columns = columns;

        this.data = new ArrayList<>(this.getContainerSize());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data.add(initializer.call(i, j));
            }
        }
    }

    public Matrix(T[][] source) {
        this.data = new ArrayList<>();

        for (T[] row : source) {
            if (row.length != source[0].length) {
                throw new IllegalArgumentException("Source is not a matrix");
            }

            this.data.addAll(Arrays.asList(row));
        }

        this.rows = source.length;
        this.columns = source.length == 0 ? 0 : source[0].length;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return columns;
    }

    public T get(int i, int j) {
        return this.data.get(getIndex(i, j));
    }

    public T set(int i, int j, T value) {
        return this.data.set(getIndex(i, j), value);
    }

    public Stream<T> toFlatStream() {
        return this.data.stream();
    }

    private int getIndex(int i, int j) {
        return i * this.columns + j;
    }

    private int getContainerSize() {
        return this.rows * this.columns;
    }
}
