package com.example.game2048.game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class GameStats {

    public String BEST_SCORE_SAVE_FILE = "best_score.txt";

    private static GameStats instance;

    public static GameStats getInstance() {
        if (instance == null) {
            instance = new GameStats();
        }

        return instance;
    }

    private int bestScore;

    private GameStats() { loadBestScore(); }

    public int getBestScore() { return bestScore; }

    public void updateBestScore(int newBestScore) {
        if (newBestScore > bestScore) {
            bestScore = newBestScore;

            try {
                Files.writeString(Path.of(BEST_SCORE_SAVE_FILE), Integer.toString(bestScore));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadBestScore() {
        try (FileInputStream inputStream = new FileInputStream(BEST_SCORE_SAVE_FILE)) {
            Scanner scanner = new Scanner(inputStream);
            bestScore = scanner.nextInt();
        } catch (IOException e) {
            bestScore = 0;
        }
    }
}
