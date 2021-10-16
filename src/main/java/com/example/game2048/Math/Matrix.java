package com.example.game2048.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix<T> {
    private int rows;
    private int columns;
    private List<T> data;

    // TODO: Special case when rows == 0, columns == 0 or both are == 0
    public Matrix(int rows, int columns, T fillValue) {
        this.rows = rows;
        this.columns = columns;
        this.data = new ArrayList<>(Collections.nCopies(this.getContainerSize(), fillValue));
    }

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
        forEachPosition((i, j) -> data.add(initializer.call(i, j)));
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

    public Matrix(Matrix<T> other) {
        this.rows = other.rows;
        this.columns = other.columns;
        this.data = new ArrayList<>(other.data);
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return columns;
    }

    public Vector getSize() { return new Vector(rows, columns); }

    public T get(int i, int j) {
        return this.data.get(getIndex(i, j));
    }

    public T get(Vector v) { return get(v.getI(), v.getJ()); }

    public T tryGet(int i, int j) {
        return isInBounds(i, j) ? get(i, j) : null;
    }

    public T tryGet(Vector position) {
        return tryGet(position.getI(), position.getJ());
    }

    public void set(int i, int j, T value) {
        data.set(getIndex(i, j), value);
    }

    public void set(Vector v, T value) { set(v.getI(), v.getJ(), value); }

    public void erase(Vector position) {
        set(position, null);
    }

    public boolean isEmptyAt(int i, int j) { return get(i, j) == null; }

    public boolean isEmptyAt(Vector position) { return get(position.getI(), position.getJ()) == null; }

    public boolean atLeastOneMatch(MatrixPositionMatcher matcher) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matcher.match(i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean allMatch(Predicate<Vector> matcher) {
        return allMatch((i, j) -> matcher.test(new Vector(i, j)));
    }

    public boolean allMatch(MatrixPositionMatcher matcher) {
        return !atLeastOneMatch((i, j) -> !matcher.match(i, j));
    }

    public Stream<T> toFlatStream() {
        return this.data.stream();
    }

    public void forEach(Consumer<T> operation) {
        data.forEach(operation);
    }

    public void forEachPosition(BiConsumer<Integer, Integer> operation) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                operation.accept(i, j);
            }
        }
    }

    public void forEachWithPosition(MatrixElementWithPositionConsumer<T> operation) {
        forEachPosition((i, j) -> operation.call(get(i, j), i, j));
    }

    public Matrix<T> rotateClockwise() {
        return new Matrix<>(columns, rows, (i, j) -> get(rows - 1 - j, i));
    }
    
    public void rotateClockwiseInPlace() {
        Matrix<T> temp = this.rotateClockwise();
        this.data = temp.data;
        this.rows = temp.rows;
        this.columns = temp.columns;
    }

    public boolean isInBounds(int i, int j) {
        return i >= 0 && i < this.rows && j >= 0 && j < this.columns;
    }

    public boolean isInBounds(Vector vector) {
        return isInBounds(vector.getI(), vector.getJ());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;

        Matrix<?> otherMatrix = (Matrix<?>) obj;

        return this.data.equals(otherMatrix.data);
    }

    private int getIndex(int i, int j) {
        return i * this.columns + j;
    }

    private int getContainerSize() {
        return this.rows * this.columns;
    }
}
