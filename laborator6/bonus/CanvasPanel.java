package com.example.homework;

import org.jgrapht.Graph;
import org.jgrapht.GraphMetrics;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Random;


public class CanvasPanel extends Canvas {
    private double width;
    private double height;
    private File file; // fisierul unde se va salva imaginea
    private AtomicInteger[][] lineEndpoints;
    public CanvasPanel(double width, double height, File file) {
        super(width, height);
        this.height=height;
        this.width=width;
        this.file=new File("null");
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
        this.lineEndpoints = new AtomicInteger[numDots][numDots]; // pt a putea fi modificata in lambda function
        for (int i = 0; i < lineEndpoints.length; i++) {
            for (int j = 0; j < lineEndpoints.length; j++) {
                lineEndpoints[i][j] = new AtomicInteger(0);
            }
        }
        int maxLines = (numDots * (numDots - 1)) / 2;
        try {
            if (numLines > maxLines) {
                throw new IllegalArgumentException("The number of lines entered is too large. Maximum number of lines: " + maxLines);
            }
            for (int i = 0; i < numLines; i++) {
                int startDot = (int) (Math.random() * numDots);
                int endDot = (int) (Math.random() * numDots);
                while (endDot == startDot || lineEndpoints[startDot][endDot].get()==-1 || lineEndpoints[endDot][startDot].get()==-1) {
                    endDot = (int) (Math.random() * numDots);
                }
                lineEndpoints[startDot][endDot]= new AtomicInteger(-1);
                lineEndpoints[endDot][startDot]=new AtomicInteger(-1);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // draw the lines
        double angle = 2 * Math.PI / numDots;
        gc.setStroke(Color.GRAY);
        for (int i=0;i<lineEndpoints.length-1;i++)
            for(int j=i;j<lineEndpoints.length;j++) {
                if ( lineEndpoints[i][j].get() ==-1 || lineEndpoints[j][i].get() == -1)
                {
                    double xcoord_i = centerX + radius * Math.cos(i * angle);
                    double ycoord_i = centerY + radius * Math.sin(i * angle);
                    double xcoord_j = centerX + radius * Math.cos(j * angle);
                    double ycoord_j = centerY + radius * Math.sin(j * angle);
                    gc.strokeLine(xcoord_i, ycoord_i, xcoord_j, ycoord_j);
                }
            }

        /***
         * desenam jocul propriu-zis
         */
         AtomicInteger nrPlayer = new AtomicInteger(1); // pentru a putea fi modificat in functia lambda; va fi 1 pt primul jucator si 2 pentru al doilea;
        AtomicInteger out=new AtomicInteger(0); // pt a parasi jocul cand un jucator castiga

        int ok=0;

        while(out.get()!=1 && ok<maxLines-1)
        {
            if(nrPlayer.get()==1)
            {
                human(out,nrPlayer);
            }
            else
            {
                computer(out,nrPlayer);
            }
            ok++;
            gameOver( out);
            nrPlayer.set(nrPlayer.get() == 1 ? 2 : 1);
        }
    }
    private void gameOver( AtomicInteger out)
    {
        int ok=0; // presupunem ca matricea este completata
        for(int i=0;i<lineEndpoints.length;i++)
            for(int j=0;j<lineEndpoints.length;j++)
            {
                if(lineEndpoints[i][j].get()==-1)
                {
                    ok=1; // am gasit o linie necompletata
                    i=lineEndpoints.length;
                    j=lineEndpoints.length;
                }
            }
        if(ok==0)
        {
            out.set(1); // matricea este plina
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("GAME OVER!");
            alert.showAndWait();
        }
    }
    private void human( AtomicInteger out, AtomicInteger nrPlayer)
    {
        GraphicsContext gc = this.getGraphicsContext2D();
        double centerX = width / 2;
        double centerY = height / 2;
        double padding = 50.0;
        double angle = 2 * Math.PI / lineEndpoints.length;
        double radius = Math.min(width, height) / 2 - padding;

        if(nrPlayer.get()==1) {
        AtomicReference<EventHandler<MouseEvent>> clickHandlerRef = new AtomicReference<>();
        /**
         * pentru a opri jocul atunci cand un player castiga jocul
         */
        clickHandlerRef.set(event -> {
            if (out.get()==1) {
                this.removeEventHandler(MouseEvent.MOUSE_CLICKED, clickHandlerRef.get());
            }
        });
            setOnMouseClicked(event -> {
                if (nrPlayer.get() == 1) {
                    /***
                     * memoram coordonatele clickului pentru ca mai aopoi sa verificam daca se gasesc pe vreo linie;
                     */
                    double xMouseCoord = event.getX();
                    double yMouseCoord = event.getY();

                    /**
                     * verificam daca exista linie care sa contina coordonatele clickului
                     */
                    for (int i = 0; i < lineEndpoints.length - 1; i++) {
                        for (int j = i + 1; j < lineEndpoints.length; j++) {
                            double xcoord_i = centerX + radius * Math.cos(i * angle);
                            double ycoord_i = centerY + radius * Math.sin(i * angle);
                            double xcoord_j = centerX + radius * Math.cos(j * angle);
                            double ycoord_j = centerY + radius * Math.sin(j * angle);
                            lineEndpoints[0][1] = new AtomicInteger(5);

                            if (lineEndpoints[i][j].get() == -1) {
                                double distance = Math.sqrt(Math.pow(xMouseCoord - xcoord_i, 2) + Math.pow(yMouseCoord - ycoord_i, 2))
                                        + Math.sqrt(Math.pow(xMouseCoord - xcoord_j, 2) + Math.pow(yMouseCoord - ycoord_j, 2));
                                if (distance < Math.sqrt(Math.pow(xcoord_i - xcoord_j, 2) + Math.pow(ycoord_i - ycoord_j, 2)) + 0.1 && lineEndpoints[i][j].get() == -1) {
                                    gc.setStroke(Color.RED);
                                    lineEndpoints[i][j].set(1);
                                    nrPlayer.set(2);
                                    /**
                                     * verificam daca a fost miscarea castigatoare
                                     */
                                    if (getWinner() == 1) {
                                        gc.strokeLine(xcoord_i, ycoord_i, xcoord_j, ycoord_j);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Game Over");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Player1 won!");
                                        alert.showAndWait();
                                        out.set(1);
                                        setOnMouseClicked(clickHandlerRef.get());
                                        gc.clearRect(0, 0, width, height);
                                        return;
                                    }
                                    gc.strokeLine(xcoord_i, ycoord_i, xcoord_j, ycoord_j);
                                }
                                break;
                            }

                        }
                    }
                }
            });
        }

    }
     private void computer( AtomicInteger out, AtomicInteger nrPlayer)
     {
         GraphicsContext gc = this.getGraphicsContext2D();
         double centerX = width / 2;
         double centerY = height / 2;
         double padding = 50.0;
         double angle = 2 * Math.PI / lineEndpoints.length;
         double radius = Math.min(width, height) / 2 - padding;

         if(nrPlayer.get()==2) {
             System.out.println(nrPlayer);
             int x=-1;
             int y=-1;
             int a=-1;
             int b=-1;
             int c=-1;
             int d=-1;
             /**
              * AI-ul va cauta doua/trei puncte deja conectate pentru a le conecta  ca sa castige,
              * iar in cazul in care nu gaseste alehge random
              */
             for(int i =0;i<lineEndpoints.length;i++)
                 for(int j=i+1;j<lineEndpoints.length;j++)
                     for(int k=i+1;k<lineEndpoints.length;k++)
                     {
                         /**
                          * se asigura ca player1 nu este la o mutare sa castige(il blocheaza
                          */
                         if(lineEndpoints[i][j].get()==1 && lineEndpoints[j][k].get()==1 && lineEndpoints[i][k].get()==-1)
                         {
                             x=i;
                             y=k;
                             break;
                         }
                         else
                         if(lineEndpoints[i][j].get()==1 && lineEndpoints[i][k].get()==1 && lineEndpoints[j][k].get()==-1)
                         {
                             x=j;
                             y=k;
                             break;
                         }
                         else
                         if(lineEndpoints[i][k].get()==1 && lineEndpoints[j][k].get()==1 && lineEndpoints[i][j].get()==-1)
                         {
                             x=i;
                             y=j;
                             break;
                         }
                         else
                         /**
                          *  cautam 3 puncte astfel incat printr-o mutare sa castigam
                          */

                         if(lineEndpoints[i][j].get()==2 && lineEndpoints[j][k].get()==2 && lineEndpoints[i][k].get()==-1)
                         {
                             a=i;
                             b=k;
                         }
                         else
                         if(lineEndpoints[i][j].get()==2 && lineEndpoints[i][k].get()==2 && lineEndpoints[j][k].get()==-1)
                         {
                             a=j;
                             b=k;
                         }
                         else
                         if(lineEndpoints[i][k].get()==2 && lineEndpoints[j][k].get()==2 && lineEndpoints[i][j].get()==-1)
                         {
                             a=i;
                             b=j;
                         }
                         else
                         /**
                          * cautam 3 puncte astfel incat prin 2 mutari sa castigam
                          */
                             if(lineEndpoints[i][j].get()==2 && lineEndpoints[j][k].get()==-1 && lineEndpoints[i][k].get()==-1)
                             {
                                 c=i;
                                 d=k;
                             }
                             else
                             if(lineEndpoints[i][j].get()==2 && lineEndpoints[i][k].get()==-1 && lineEndpoints[j][k].get()==-1)
                             {
                                 c=j;
                                 d=k;
                             }
                             else
                             if(lineEndpoints[i][k].get()==2 && lineEndpoints[j][k].get()==-1 && lineEndpoints[i][j].get()==-1)
                             {
                                 c=i;
                                 d=j;
                             }

                     }
             if(a!=-1 && b!=-1 && x==-1 && y==-1)
             {
                 x=c;
                 y=d;
             }
             else
             if(c!=-1 && d!=-1 && x==-1 && y==-1)
             {
                 x=c;
                 y=d;
             }
             else {
                 /**
                  * deoarece nu exista nicio mutare favorabile se genereaza o mutare random
                  */
                 Random random = new Random();
                 x = random.nextInt(lineEndpoints.length);
                 y = random.nextInt(lineEndpoints.length);
                 while (lineEndpoints[x][y].get() != -1) {
                     x = random.nextInt(lineEndpoints.length);
                     y = random.nextInt(lineEndpoints.length);
                 }
             }
             double xcoord_i = centerX + radius * Math.cos(x * angle);
             double ycoord_i = centerY + radius * Math.sin(x * angle);
             double xcoord_j = centerX + radius * Math.cos(y * angle);
             double ycoord_j = centerY + radius * Math.sin(y * angle);
             gc.setStroke(Color.BLUE);
             lineEndpoints[x][y].set(2);
             nrPlayer.set(1);
             /**
              * verificam daca a fost miscarea castigatoare
              */
             if (getWinner() == 2) {

                 gc.strokeLine(xcoord_i, ycoord_i, xcoord_j, ycoord_j);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Game Over");
                 alert.setHeaderText(null);
                 alert.setContentText("Player2 won!");
                 alert.showAndWait();
                 out.set(1);
                 gc.clearRect(0, 0, width, height);
             }
         }

     }

    public int getWinner()
    {
        /***
         * pentru a gasi un castigator , dupa fiecare noua mutare verificam daca exista vreun trunghi de aceeasi culoare creat, adica:
         *
         */
        for(int i=0;i<lineEndpoints.length;i++)
        {
            for(int j=0;j< lineEndpoints.length;j++)
            {
                for(int k=0;k< lineEndpoints.length;k++)
                {
                    if(lineEndpoints[i][j].get()==1 && lineEndpoints[i][k].get()==1 && lineEndpoints[j][k].get()==1)
                    {
                        return 1;
                    }
                    else
                    if(lineEndpoints[i][j].get()==2 && lineEndpoints[i][k].get()==2 && lineEndpoints[j][k].get()==2)
                    {
                        return 2;
                    }

                }
            }
        }
        return 0;

    }

    /**
     * functia va salva o imagine cu starea curenta a jocului in folderul cu proiectul
     * si in acelasi timp salveaza datele jocului astfel incat sa se poata reveni la starea curenta, accesand load
     */
    public void savePicture() throws IOException {
        WritableImage image = snapshot(new SnapshotParameters(), null);
         this.file = new File("drawing.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Drawing saved to " + file.getAbsolutePath());
        } catch (IOException er) {
            System.out.println("Error saving drawing: " + er.getMessage());
        }

    }
    public void numberOfTriangle()
    {

    }
    public void load() {
        try {
            // Read the image file into a byte array
            byte[] bytes = Files.readAllBytes(file.toPath());

            // Convert the byte array to an InputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

            // Read the image from the input stream
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Convert the buffered image to a JavaFX Image
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            // Get the graphics context of the canvas and draw the image
            GraphicsContext gc = this.getGraphicsContext2D();
            gc.drawImage(image, 0, 0, this.getWidth(), this.getHeight());
        } catch (IOException e) {
            // Handle the exception
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load image from file: " + e.getMessage());
            alert.showAndWait();
        }
    }
    private byte[] saveBytePicture() {
        WritableImage image = snapshot(new SnapshotParameters(), null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputStream);
            System.out.println("Drawing saved to byte array");
        } catch (IOException ex) {
            System.err.println("Error saving drawing: " + ex.getMessage());
        }
        return outputStream.toByteArray();
    }

    /**
     * generam graful corespunzator desenului, in functie de matricea de adiacenta
     */
    public int getTriangle()
    {
        /**
         * creem nodurile si liste lor de vecini
         * adaugam nodurile si muchiile in graf
         */

        org.jgrapht.Graph<Node, DefaultEdge> jGraphTGraph = new SimpleGraph<>(DefaultEdge.class);
        for(int i=0;i<lineEndpoints.length;i++)
        {
            Node x= new Node(i);
            jGraphTGraph.addVertex(x);
            for(int j=0;j<lineEndpoints.length;j++)
            {
                if(lineEndpoints[i][j].get()!=0)
                {
                    Node y=new Node(j);
                    x.addNeighbor(y);
                    if(j>i)
                    {
                        jGraphTGraph.addEdge(x, y);
                    }
                }
            }
        }
        return  GraphMetrics.getNumberOfTriangles(jGraphTGraph);;
    }

}