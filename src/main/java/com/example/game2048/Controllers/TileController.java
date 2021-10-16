package com.example.game2048.Controllers;

import com.example.game2048.Application;
import com.example.game2048.Game.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class TileController extends VBox {

    private static final String TILE_FXML = "GamePage/Tile.fxml";

    @FXML
    private AnchorPane root;

    @FXML
    private Text text;

    public static FXMLLoader instantiate() throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(TILE_FXML));
        loader.load();
        return loader;
    }

    public void setAppearance(TileGridMeasurements measurements) {
        setSize(measurements.getTileSize());
        setTextMeasurements(measurements);
    }

    public void setTile(int tile) {
        text.setText(Integer.toString(tile));
    }

    private void setSize(double size) {
        root.setPrefWidth(size);
        root.setPrefHeight(size);
    }

    private void setTextMeasurements(TileGridMeasurements measurements) {
        text.setFont(Font.font("Arial", measurements.getFontSize()));
        text.setWrappingWidth(measurements.getTileSize());
        text.setLayoutY(measurements.getTextLayoutY());
    }
}
