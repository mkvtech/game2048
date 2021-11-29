package com.example.game2048.game;

public class Tile {
    private int value;
    private boolean merged;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void merge(Tile other) {
        this.value += other.value;
        this.merged = true;
    }

    public void clearMerged() {
        this.merged = false;
    }

    public boolean isMergeableWith(Tile other) {
        return !this.merged && this.value == other.value;
    }
}
