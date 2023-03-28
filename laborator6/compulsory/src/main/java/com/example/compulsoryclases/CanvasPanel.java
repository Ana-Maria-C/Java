package com.example.compulsoryclases;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasPanel extends Canvas {
    private double width;
    private double height;
    public CanvasPanel(double width, double height) {
        super(width, height);
        this.height=height;
        this.width=width;
    }
    public void drawBoard(int numDots, int numLines) {
        double width = this.getWidth();
        double height = this.getHeight();
        double padding = 50.0;
        double centerX = width / 2;
        double centerY = height / 2;
        double radius = Math.min(width, height) / 2 - padding;

        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        // calculate the positions of the dots
        double[][] dotPositions = new double[numDots][2];
        for (int i = 0; i < numDots; i++) {
            double angle = i * 2 * Math.PI / numDots;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            dotPositions[i][0] = x;
            dotPositions[i][1] = y;
        }

        // draw the dots
        gc.setFill(Color.BLACK);
        for (double[] pos : dotPositions) {
            gc.fillOval(pos[0] - 5, pos[1] - 5, 10, 10);
        }

        // calculate the line endpoints
        int[][] lineEndpoints = new int[numDots][numDots];
        int maxLines = (numDots * (numDots - 1)) / 2;
        try {
            if (numLines > maxLines) {
                throw new IllegalArgumentException("The number of lines entered is too large. Maximum number of lines: " + maxLines);
            }
            for (int i = 0; i < numLines; i++) {
                int startDot = (int) (Math.random() * numDots);
                int endDot = (int) (Math.random() * numDots);
                while (endDot == startDot || lineEndpoints[startDot][endDot]==1 || lineEndpoints[endDot][startDot]==1) {
                    endDot = (int) (Math.random() * numDots);
                }
                lineEndpoints[startDot][endDot]=1;
                lineEndpoints[endDot][startDot]=1;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // draw the lines
        double angle = 2 * Math.PI / numDots;
        gc.setStroke(Color.BLACK);
        for (int i=0;i<lineEndpoints.length-1;i++)
            for(int j=i;j<lineEndpoints.length;j++) {
                if ( lineEndpoints[i][j] ==1 || lineEndpoints[j][i] == 1)
                {
                    double xcoord_i = centerX + radius * Math.cos(i * angle);
                    double ycoord_i = centerY + radius * Math.sin(i * angle);
                    double xcoord_j = centerX + radius * Math.cos(j * angle);
                    double ycoord_j = centerY + radius * Math.sin(j * angle);
                    gc.strokeLine(xcoord_i, ycoord_i, xcoord_j, ycoord_j);
                }
            }
    }
}