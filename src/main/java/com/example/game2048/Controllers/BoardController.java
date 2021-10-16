package com.example.game2048.Controllers;

import com.example.game2048.Game.TileGrid;
import com.example.game2048.Math.Matrix;
import com.example.game2048.Math.Vector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class BoardController {

    private TileGrid tileGrid;
    private Matrix<TileController> tileControllerMatrix;
    private TileGridMeasurements measurements;

    @FXML
    private GridPane tileContainer;

    public void setTileGrid(TileGrid tileGrid) {
        this.tileGrid = tileGrid;

        measurements = new TileGridMeasurements(tileGrid.getSize());

        setGridGap(measurements.getGapSize());
        tileControllerMatrix = buildAndRenderMatrix();
    }

    public void updateGrid() {
        tileControllerMatrix.forEachWithPosition((controller, i, j) -> {
            controller.setTile(tileGrid.getValueAt(i, j));
        });
    }

    private Matrix<TileController> buildAndRenderMatrix() {
        return new Matrix<>(tileGrid.getRows(), tileGrid.getColumns(), this::buildAndRenderTile);
    }

    private TileController buildAndRenderTile(int i, int j) {
        FXMLLoader tileComponent = loadTileComponent();

        TileController tileController = tileComponent.getController();

        tileController.setTile(tileGrid.getValueAt(new Vector(i, j)));
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
