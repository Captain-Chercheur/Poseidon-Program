import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.sun.javafx.menu.MenuItemBase;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import javafx.util.Duration;

public class Main extends Application {
    public Main() throws IOException {
    }

    public static void main(String[] args) { Application.launch(args);    }
    public final Label designation = new Label("Désignation de la pièce");
    private TextField designationText = new TextField();
    private final Label reference = new Label("Référence du produit");
    private TextField referenceText = new TextField();
    private final Label state = new Label("Etat de la pièce");
    private TextField stateText = new TextField();
    private final Label color = new Label("Couleur de la pièce");
    private TextField colorText = new TextField();
    private final Label brand = new Label("Marque de la pièce");
    private TextField brandText = new TextField();
    private final Label ship = new Label("Provenance de la pièce (Marque - Modèle - Année)");
    private TextField shipText = new TextField();
    private final Label shipNumber = new Label("Numéro de série du bateau (NIC)");
    private TextField shipNumberText = new TextField();
    String [] aled = getAllImages(new File("img_tmp/"), false).toArray(new String[0]);

    private final Image image = new Image(new FileInputStream(aled[0]));
    private final Image image1 = new Image(new FileInputStream(aled[1]));
    private final Image image2 = new Image(new FileInputStream(aled[2]));

    ImageView imageView = new ImageView(image);
    ImageView imageView1 = new ImageView(image1);
    ImageView imageView2 = new ImageView(image2);



    Button openCameraButton = new Button("Take picture");
    Button openBarcodescannerButton = new Button("Read barcode");
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Poseidon Program");
        Webcam webcam = Webcam.getDefault();
        GridPane root = new GridPane();

        Group images = new Group(imageView, imageView1,imageView2);
        imageView.setFitHeight(150);
        imageView.setFitWidth(250);
        imageView1.setFitHeight(150);
        imageView1.setFitWidth(250);
        imageView2.setFitHeight(150);
        imageView2.setFitWidth(250);
        imageView1.setX(0);
        imageView1.setY(0);
        imageView1.setX(250);
        imageView1.setY(0);
        imageView2.setX(500);
        imageView2.setY(0);

        root.addRow(0,designation, designationText);
        Button submit = new Button("Submit");
        Button refresh = new Button("Refresh");
        submit.setOnAction(e->System.out.println("You entered: Product name: "+designationText.getText()));
        referenceText.setPromptText("Poulie simple");
        root.addRow(1,reference, referenceText);
        root.addRow(2,state, stateText);
        root.addRow(3, color,colorText);
        root.addRow(4,brand, brandText);
        root.addRow(5,ship, shipText);
        root.addRow(6,shipNumber, shipNumberText);
        root.addRow(7, openCameraButton, openBarcodescannerButton);
        root.addRow(8, submit,  refresh);
        root.addRow(10, images);

        refresh.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                for(int i=0; i<3; i++) {
                    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(i));
                    int finalI = i;
                    pauseTransition.setOnFinished(event -> root.addRow(1, images));
                    pauseTransition.play();
                }
            }
        });

        Scene scene = new Scene(root, 1000, 800, Color.WHITE);
        Label label = new Label("");


        openBarcodescannerButton.setOnAction(value ->  {
            if (webcam != null) {
                try {
                    new barcodescanner();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                label.setText("No webcam detected");
            }
        });
        openCameraButton.setOnAction(value -> {
            while(referenceText.getText() == null || referenceText.getText().trim().isEmpty()) {
                referenceText.setPromptText("Ce champ ne peut pas être vide");
                return;
            }
            if (webcam != null) {
                try {
                    Camera.Camera(designationText.getText());
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                label.setText("No webcam detected");
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static ArrayList<String> getAllImages(File directory, boolean descendIntoSubDirectories) throws IOException {
        ArrayList<String> resultList = new ArrayList<String>(256);
        File[] f = directory.listFiles();
        for (File file : f) {
            if (file != null && file.getName().toLowerCase().endsWith(".png") && !file.getName().startsWith("tn_")) {
                resultList.add(file.getPath());
            }
            if (descendIntoSubDirectories && file.isDirectory()) {
                ArrayList<String> tmp = getAllImages(file, true);
                if (tmp != null) {
                    resultList.addAll(tmp);
                }
            }
        }
        if (resultList.size() > 0)
            return resultList;
        else
            return null;
    }
}
