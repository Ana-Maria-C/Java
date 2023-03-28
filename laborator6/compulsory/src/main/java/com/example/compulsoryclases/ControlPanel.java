package com.example.compulsoryclases;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ControlPanel extends VBox {
    private CanvasPanel canvasPanel;

    public ControlPanel(CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

        HBox controlPanel = new HBox();
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");

        //afisam butoanele pe mijloc
        controlPanel.setAlignment(Pos.CENTER);

        controlPanel.getChildren().addAll(loadButton, saveButton, exitButton);
        this.getChildren().add(controlPanel);
    }

}