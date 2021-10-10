package com.example.game2048.Math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    @Test
    public void testEquality() {
        Vector first = new Vector(1, 5);
        Vector second = new Vector(1, 5);

        assertEquals(first, second);
    }

    @Test
    public void testEqualityWhenPartsAreEqual() {
        Vector first = new Vector(4, 4);
        Vector second = new Vector(4, 4);

        assertEquals(first, second);
    }
}