package com.example.game2048.Math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    record Sample(int i) {}

    @Test
    public void testIntMatrixInitializer() {
        Matrix<Integer> integerMatrix = new Matrix<>(3, 5, () -> 99);
        assertEquals(integerMatrix.get(2, 4), 99);
        assertEquals(integerMatrix.getRows(), 3);
        assertEquals(integerMatrix.getColumns(), 5);
    }

    @Test
    public void testIntMatrixEnhancedInitializer() {
        Matrix<Integer> integerMatrix = new Matrix<>(3, 5, (i, j) -> 99);
        assertEquals(integerMatrix.get(2, 4), 99);
        assertEquals(integerMatrix.getRows(), 3);
        assertEquals(integerMatrix.getColumns(), 5);
    }

    @Test
    public void testConstructorFromSource() {
        Integer[][] source = new Integer[][] {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };

        Matrix<Integer> integerMatrix = new Matrix<>(source);

        assertEquals(integerMatrix.toFlatStream().toList(), Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(integerMatrix.getRows(), 2);
        assertEquals(integerMatrix.getColumns(), 3);
    }

    @Test
    public void testConstructorFromSourceWhenSourceIsInvalid() {
        Integer[][] invalidSource = new Integer[][] {
                { 1, 2, 3 },
                { 4, 5, 6, 7 }
        };

        assertThrows(IllegalArgumentException.class, () -> new Matrix<>(invalidSource));
    }

    @Test
    public void testConstructorFromSourceWhenSourceIsEmpty() {
        Integer[][] source = new Integer[0][0];

        Matrix<Integer> integerMatrix = new Matrix<>(source);

        assertEquals(integerMatrix.toFlatStream().toList(), List.of());
        assertEquals(integerMatrix.getRows(), 0);
        assertEquals(integerMatrix.getColumns(), 0);
    }

    @Test
    public void testObjectMatrix() {
        Matrix<Sample> sampleMatrix = new Matrix<>(2, 3, (i, j) -> new Sample(i + j));

        int[] values = sampleMatrix.toFlatStream().mapToInt(Sample::i).toArray();

        assertArrayEquals(values, new int[] { 0, 1, 2, 1, 2, 3 });
    }

    @Test
    public void testSetValue() {
        Matrix<Boolean> booleanMatrix = new Matrix<>(2, 2, () -> false);

        booleanMatrix.set(1, 0, true);

        List<Boolean> values = booleanMatrix.toFlatStream().collect(Collectors.toList());
        assertEquals(values, Arrays.asList(false, false, true, false));
    }

    @Test
    public void testRotation() {
        Matrix<Integer> originalMatrix = new Matrix<>(new Integer[][] {
                { 1,  2,  3,  4 },
                { 5,  6,  7,  8 },
                { 9, 10, 11, 12 }
        });

        Matrix<Integer> rotatedMatrix = originalMatrix.rotateClockwise();

        assertEquals(rotatedMatrix, new Matrix<>(new Integer[][] {
                {  9, 5, 1 },
                { 10, 6, 2 },
                { 11, 7, 3 },
                { 12, 8, 4 }
        }));
    }

    @Test
    public void testAcceptsVector() {
        IntegerMatrix matrix = new IntegerMatrix(5, 3, 0);

        matrix.set(new Vector(3, 1), 5);
        assertEquals(matrix.get(3, 1), 5);

        matrix.set(0, 2, 7);
        assertEquals(matrix.get(new Vector(0, 2)), 7);
    }
}