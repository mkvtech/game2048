package com.example.game2048.Math;

public interface MatrixElementWithPositionConsumer<T> {
    void call(T element, int i, int j);
}
