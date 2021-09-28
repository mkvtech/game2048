package com.example.game2048.Utilities;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Arrays {
    public static <T> T[][] copyArray2d(T[][] source) {
        return java.util.Arrays.stream(source).map(el -> el.clone()).toArray($ -> source.clone());
    }

    public static int[][] copyIntArray2d(int[][] source) {
        return java.util.Arrays.stream(source).map(int[]::clone).toArray(int[][]::new);
    }

    public static void prettyPrintIntArray2d(int[][] array) {
        int sizeX = array[0].length;

        System.out.println("\t   \t" + IntStream.range(0, sizeX).mapToObj(String::valueOf).collect(Collectors.joining("\t")));
        System.out.println("\t \t" + new String(new char[sizeX * 4]).replace("\0", "-"));

        for (int i = 0; i < array.length; i++) {
            int[] row = array[i];

            String elementsString = java.util.Arrays.stream(row).mapToObj(String::valueOf).collect(Collectors.joining("\t"));

            System.out.println("\t" + i + " |\t" + elementsString);
        }
    }
}
