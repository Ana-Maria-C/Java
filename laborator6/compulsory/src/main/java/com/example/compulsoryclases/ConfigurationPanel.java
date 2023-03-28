package com.example.compulsoryclases;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfigurationPanel extends VBox {

    private CanvasPanel canvasPanel;
    private int numDots;
    private int numLines;

    public int getNumDots() {
        return numDots;
    }

    public int getNumLines() {
        return numLines;
    }

    public ConfigurationPanel(CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

        HBox configPanel = new HBox();
        Label numDotsLabel = new Label("Number of dots:");
        TextField numDotsInput = new TextField();
        Label numLinesLabel = new Label("Number of lines:");
        TextField numLinesInput = new TextField();
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            int numDots = Integer.parseInt(numDotsInput.getText());
            int numLines = Integer.parseInt(numLinesInput.getText());
            this.numDots = numDots;
            this.numLines = numLines;
            canvasPanel.drawBoard(numDots, numLines);
        });
        configPanel.getChildren().addAll(numDotsLabel, numDotsInput, numLinesLabel, numLinesInput, newGameButton);
        this.getChildren().add(configPanel);
    }
}
