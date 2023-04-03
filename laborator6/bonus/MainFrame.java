package com.example.homework;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jgrapht.GraphMetrics;

import java.io.File;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;

public class MainFrame extends Application {

    private CanvasPanel canvasPanel;
    private ControlPanel controlPanel;
    private ConfigurationPanel configurationPanel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create the main frame
        BorderPane root = new BorderPane();
        String imagePath = "C:\\Users\\Ana-Maria\\IdeaProjects\\laborator6\\Homework";
        File imageFile = new File(imagePath);

        // create the canvas panel
        canvasPanel = new CanvasPanel(500,500,imageFile);

        // create the configuration panel
        configurationPanel = new ConfigurationPanel(canvasPanel);

        // create the control panel
        controlPanel = new ControlPanel(canvasPanel);
        root.setTop(configurationPanel);
        root.setCenter(canvasPanel);
        root.setBottom(controlPanel);
        canvasPanel.getTriangle();

        // set up the scene and show the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}