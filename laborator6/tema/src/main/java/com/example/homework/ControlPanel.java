package com.example.homework;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;


public class ControlPanel extends VBox {
    private CanvasPanel canvasPanel;

    public ControlPanel(CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

        HBox controlPanel = new HBox();
        Button loadButton = new Button("Load");
        loadButton.setOnAction(e->{
            canvasPanel.load();
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e->{
            try {
                canvasPanel.savePicture();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.exit());

        //afisam butoanele pe mijloc
        controlPanel.setAlignment(Pos.CENTER);

        controlPanel.getChildren().addAll(loadButton, saveButton, exitButton);
        this.getChildren().add(controlPanel);
    }

}