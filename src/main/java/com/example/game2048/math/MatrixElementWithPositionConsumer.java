package com.example.game2048.math;

public interface MatrixElementWithPositionConsumer<T> {
    void call(T element, int i, int j);
}
