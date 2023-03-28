package com.example.compulsoryclases;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class MainFrame extends Application {

    private CanvasPanel canvasPanel;
    private ControlPanel controlPanel;
    private ConfigurationPanel configurationPanel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // create the main frame
        BorderPane root = new BorderPane();

        // create the canvas panel
        canvasPanel = new CanvasPanel(500,500);

        // create the configuration panel
        configurationPanel = new ConfigurationPanel(canvasPanel);

        // create the control panel
        controlPanel = new ControlPanel(canvasPanel);
        root.setTop(configurationPanel);
        root.setCenter(canvasPanel);
        root.setBottom(controlPanel);

        // set up the scene and show the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}