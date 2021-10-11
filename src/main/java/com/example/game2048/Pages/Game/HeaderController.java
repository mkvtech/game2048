package com.example.game2048.Pages.Game;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class HeaderController {

    @FXML
    private Text score;

    @FXML
    private Text best;

    public void setScore(int newScore) {
        score.setText("SCORE " + newScore);
    }

    public void setBest(int newBest) {
        best.setText("BEST " + newBest);
    }
}
