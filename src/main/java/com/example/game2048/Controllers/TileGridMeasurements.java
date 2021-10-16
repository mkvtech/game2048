package com.example.game2048.Controllers;

import com.example.game2048.Math.Vector;

public class TileGridMeasurements {

    private static final double FONT_SIZE_FACTOR = 0.5;
    private static final double BIG_NUMBER_FONT_SIZE_MULTIPLIER = 0.8;
    public static final double GAP_FACTOR = 15 / 106.25;
    public static final int BOARD_SIZE = 500;
    public static final int MIN_FONT_SIZE = 16;

    private final double tileSize;
    private final double gapSize;
    private final int fontSize;
    private final double textLayoutY;

    public TileGridMeasurements(Vector matrixSize) {
        int maxMatrixSize = Math.max(matrixSize.getI(), matrixSize.getJ());

        tileSize = calculateTileSize(maxMatrixSize);
        gapSize = calculateGapSize(tileSize);
        fontSize = calculateFontSize(tileSize);
        textLayoutY = calculateTextLayoutY(tileSize);
    }

    public double getFontSize(int value) {
        return value <= 512 ? fontSize : fontSize * BIG_NUMBER_FONT_SIZE_MULTIPLIER;
    }

    public double getGapSize() { return gapSize; }

    public double getTileSize() { return tileSize; }

    public double getTextLayoutY() { return textLayoutY; }

    private double calculateTileSize(int maxMatrixSize) {
        return BOARD_SIZE / (maxMatrixSize + GAP_FACTOR * (maxMatrixSize + 1));
    }

    private double calculateGapSize(double tileSize) {
        return tileSize * GAP_FACTOR;
    }

    private int calculateFontSize(double tileSize) {
        return Math.max(MIN_FONT_SIZE, (int) Math.round(tileSize * FONT_SIZE_FACTOR));
    }

    private double calculateTextLayoutY(double tileSize) {
        return tileSize / 2;
    }
}
