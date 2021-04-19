import com.sun.glass.ui.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import com.github.sarxos.webcam.Webcam;
import javafx.util.Duration;

public class Main extends Application {
    public Main() throws IOException {

    }


    public static void main(String[] args) { Application.launch(args); }
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


    Image image = new Image(new FileInputStream("test.png"));
    Image image1 = new Image(new FileInputStream("no-image.png"));
    Image image2 = new Image(new FileInputStream("no-image.png"));

    Image crossImage = new Image(new FileInputStream("img_static/red-cross.png"));


    private ImageView imageView = new ImageView(image);
    private ImageView imageView1 = new ImageView(image1);
    private ImageView imageView2 = new ImageView(image2);

    private ImageView crossImageView = new ImageView(crossImage);
    private ImageView crossImageView1 = new ImageView(crossImage);
    private ImageView crossImageView2 = new ImageView(crossImage);

    Button openCameraButton = new Button("Take picture");
    Button openBarcodeScannerButton = new Button("Read barcode");
    Hyperlink removeImage = new Hyperlink();
    Hyperlink removeImage1 = new Hyperlink();
    Hyperlink removeImage2 = new Hyperlink();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Timeline fiveSecondsWonder = new Timeline(
                new KeyFrame(Duration.seconds(10),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    imageView.setImage(new Image(new FileInputStream("no-image.png")));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                };
                            }
                        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

        primaryStage.setTitle("Poseidon Program");
        Webcam webcam = Webcam.getDefault();
        GridPane root = new GridPane();

        Group images = new Group(imageView, imageView1, imageView2, removeImage, removeImage1, removeImage2);
        removeImage.setGraphic(crossImageView);
        removeImage1.setGraphic(crossImageView1);
        removeImage2.setGraphic(crossImageView2);

        imageView.setFitHeight(150);
        imageView.setFitWidth(250);
        imageView1.setFitHeight(150);
        imageView1.setFitWidth(250);
        imageView2.setFitHeight(150);
        imageView2.setFitWidth(250);
        imageView1.setX(0);
        imageView1.setY(200);
        imageView1.setX(250);
        imageView1.setY(20);
        imageView2.setX(500);
        imageView2.setY(20);

        removeImage.setLayoutX(0);
        removeImage1.setLayoutX(250);
        removeImage2.setLayoutX(500);

        root.addRow(0, designation, designationText);
        Button submit = new Button("Submit");
        Button refresh = new Button("Refresh");
        submit.setOnAction(e -> System.out.println("You entered: Product name: " + designationText.getText()));
        referenceText.setPromptText("Poulie simple");
        root.addRow(1, reference, referenceText);
        root.addRow(2, state, stateText);
        root.addRow(3, color, colorText);
        root.addRow(4, brand, brandText);
        root.addRow(5, ship, shipText);
        root.addRow(6, shipNumber, shipNumberText);
        root.addRow(7, openCameraButton, openBarcodeScannerButton);
        root.addRow(10, images);
        //root.addRow(9, removeImage, removeImage1, removeImage2);




        HBox hbox = new HBox(root);

        refresh.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                root.getChildren().remove(images);
                }
        });

        Scene scene = new Scene(hbox, 1000, 800, Color.WHITE);
        Label label = new Label("");

        removeImage.setOnAction(value -> {
            try {
                imageView.setImage(new Image (new FileInputStream("no-image.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        removeImage1.setOnAction(value -> {
            imageView1.setImage(new Image("no-image.png"));
        });
        removeImage2.setOnAction(value -> {
            imageView2.setImage(new Image("no-image.png"));
        });

        openBarcodeScannerButton.setOnAction(value -> {
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
            while (referenceText.getText() == null || referenceText.getText().trim().isEmpty()) {
                referenceText.setPromptText("Ce champ ne peut pas être vide");
                return;
            }
            if (webcam != null) {
                int i = 0;
                try {
                    String omg = Camera.Camera(designationText.getText());
                    System.out.println(omg);
                    imageView.setImage(new Image("img_tmp/"+omg));
                    if (getAllImages(new File("img_tmp/"), false) == null) {
                        System.out.println("no images");
                    } else {
                        String[] aled = getAllImages(new File("img_tmp/"), false).toArray(new String[0]);
                        i++;
                        System.out.println(i);

                        /*Image image = new Image(new FileInputStream(aled[i]));

                        imageView = new ImageView(image);
                        imageView1 = new ImageView(image1);
                        imageView2 = new ImageView(image2);*/
                    }
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
