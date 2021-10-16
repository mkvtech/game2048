package com.example.game2048.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class HeaderController {

    @FXML
    private Text score;

    @FXML
    private Text best;

    @FXML
    private Button newGameButton;

    public void setScore(int newScore) {
        score.setText("SCORE " + newScore);
    }

    public void setBest(int newBest) {
        best.setText("BEST " + newBest);
    }

    public void onNewGameButtonPressed(Runnable callback) {
        newGameButton.setOnAction(event -> callback.run());
    }
}
