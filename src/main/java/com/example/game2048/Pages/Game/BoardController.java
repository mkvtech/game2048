package com.example.game2048.Pages.Game;

import com.example.game2048.Application;
import com.example.game2048.Game.TileMatrix;
import com.example.game2048.Math.Matrix;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class BoardController {

    private TileMatrix tileMatrix;
    private Matrix<TileController> tileControllerMatrix;
    private TileGridMeasurements measurements;

    @FXML
    private GridPane tileContainer;

    public void setTileMatrix(TileMatrix tileMatrix) {
        this.tileMatrix = tileMatrix;

        measurements = new TileGridMeasurements(tileMatrix.getSize());

        setGridGap(measurements.getGapSize());
        tileControllerMatrix = buildAndRenderMatrix();
    }

    private Matrix<TileController> buildAndRenderMatrix() {
        return new Matrix<>(tileMatrix.getRows(), tileMatrix.getColumns(), this::buildAndRenderTile);
    }

    private TileController buildAndRenderTile(int i, int j) {
        FXMLLoader tileComponent = loadTileComponent();

        TileController tileController = tileComponent.getController();

        tileController.setTile(tileMatrix.get(i, j));
        tileController.setAppearance(measurements);

        tileContainer.add(tileComponent.getRoot(), j, i);

        return tileController;
    }

    private FXMLLoader loadTileComponent() {
        try {
            return TileController.instantiate();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setGridGap(double gap) {
        tileContainer.setHgap(gap);
        tileContainer.setVgap(gap);
        tileContainer.setPadding(new Insets(gap, gap, gap, gap));
    }
}
