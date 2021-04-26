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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimerTask;

import com.github.sarxos.webcam.Webcam;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

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
    private final Label DescriptionState = new Label("Description de l'état de la pièce");
    private TextField DescriptionStateText = new TextField();
    private final Label color = new Label("Couleur de la pièce");
    private TextField colorText = new TextField();
    private final Label brand = new Label("Marque de la pièce");
    private TextField brandText = new TextField();
    private final Label shipBrand = new Label("Marque du bateau de provenance");
    private TextField shipBrandText = new TextField();
    private final Label shipModel = new Label("Model du bateau de provenance");
    private TextField shipModelText = new TextField();
    private final Label shipYear = new Label("Année du bateau de provenance");
    private TextField shipYearText = new TextField();
    private final Label shipNumber = new Label("Numéro de série du bateau (NIC)");
    private TextField shipNumberText = new TextField();
    private final Label weight = new Label("Poids de la pièce");
    private TextField weightText = new TextField();
    private final Label stockPlacement = new Label("Emplacement de stockage de la pièce");
    private TextField stockPlacementText = new TextField();

    static Image image;

    static {
        try {
            image = new Image(new FileInputStream("no-image.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Image image1;

    static {
        try {
            image1 = new Image(new FileInputStream("no-image.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Image image2;

    static {
        try {
            image2 = new Image(new FileInputStream("no-image.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static Image image3;

    static {
        try {
            image3 = new Image(new FileInputStream("no-image.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static Image image4;

    static {
        try {
            image4 = new Image(new FileInputStream("no-image.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static Image image5;

    static {
        try {
            image5 = new Image(new FileInputStream("no-image.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    Image crossImage = new Image(new FileInputStream("img_static/red-cross.png"));


    private static ImageView imageView = new ImageView(image);
    private static ImageView imageView1 = new ImageView(image1);
    private static ImageView imageView2 = new ImageView(image2);
    private static ImageView imageView3 = new ImageView(image3);
    private static ImageView imageView4 = new ImageView(image4);
    private static ImageView imageView5 = new ImageView(image5);

    private ImageView crossImageView = new ImageView(crossImage);
    private ImageView crossImageView1 = new ImageView(crossImage);
    private ImageView crossImageView2 = new ImageView(crossImage);
    private ImageView crossImageView3 = new ImageView(crossImage);
    private ImageView crossImageView4 = new ImageView(crossImage);
    private ImageView crossImageView5 = new ImageView(crossImage);

    Button openCameraButton = new Button("Take picture");
    Button openBarcodeScannerButton = new Button("Read barcode");
    Hyperlink removeImage = new Hyperlink();
    Hyperlink removeImage1 = new Hyperlink();
    Hyperlink removeImage2 = new Hyperlink();
    Hyperlink removeImage3 = new Hyperlink();
    Hyperlink removeImage4 = new Hyperlink();
    Hyperlink removeImage5 = new Hyperlink();

    static boolean picture = false;
    static boolean picture1 = false;
    static boolean picture2 = false;
    static boolean picture3 = false;
    static boolean picture4 = false;
    static boolean picture5 = false;

    static int cpt = 0;

    public static void SetImages(String imgName, int number) throws FileNotFoundException {
        cpt++;
        System.out.print(imgName);
        if (!picture) {
            imageView.setImage(new Image(new FileInputStream("img_tmp/" + imgName)));
            picture = true;
        } else if (!picture1) {
            imageView1.setImage(new Image(new FileInputStream("img_tmp/" + imgName)));
            picture1 = true;
        } else if (!picture2) {
            imageView2.setImage(new Image(new FileInputStream("img_tmp/" + imgName)));
            picture2 = true;
        }else if (!picture3) {
            imageView3.setImage(new Image(new FileInputStream("img_tmp/" + imgName)));
            picture3 = true;
        } else if (!picture4) {
            imageView4.setImage(new Image(new FileInputStream("img_tmp/" + imgName)));
            picture4 = true;
        } else if (!picture5) {
            imageView5.setImage(new Image(new FileInputStream("img_tmp/" + imgName)));
            picture5 = true;
        }
        if (cpt == 6){
            cpt = 0;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Poseidon Program");
        Webcam webcam = Webcam.getDefault();
        GridPane root = new GridPane();

        Group images = new Group(imageView, imageView1, imageView2,imageView3, imageView4, imageView5, removeImage, removeImage1, removeImage2, removeImage3, removeImage4, removeImage5);
        removeImage.setGraphic(crossImageView);
        removeImage1.setGraphic(crossImageView1);
        removeImage2.setGraphic(crossImageView2);
        removeImage3.setGraphic(crossImageView3);
        removeImage4.setGraphic(crossImageView4);
        removeImage5.setGraphic(crossImageView5);

        imageView.setFitHeight(150);
        imageView.setFitWidth(250);
        imageView1.setFitHeight(150);
        imageView1.setFitWidth(250);
        imageView2.setFitHeight(150);
        imageView2.setFitWidth(250);
        imageView3.setFitHeight(150);
        imageView3.setFitWidth(250);
        imageView4.setFitHeight(150);
        imageView4.setFitWidth(250);
        imageView5.setFitHeight(150);
        imageView5.setFitWidth(250);

        imageView.setX(0);
        imageView.setY(0);

        imageView1.setX(250);
        imageView1.setY(0);

        imageView2.setX(500);
        imageView2.setY(0);

        imageView3.setX(0);
        imageView3.setY(200);

        imageView4.setX(250);
        imageView4.setY(200);

        imageView5.setX(500);
        imageView5.setY(200);

        removeImage.setLayoutX(0);
        removeImage1.setLayoutX(250);
        removeImage2.setLayoutX(500);
        removeImage3.setLayoutX(0);
        removeImage3.setLayoutY(200);
        removeImage4.setLayoutX(250);
        removeImage4.setLayoutY(200);
        removeImage5.setLayoutX(500);
        removeImage5.setLayoutY(200);

        root.addRow(0, designation, designationText);
        Button submit = new Button("Submit");
        Button refresh = new Button("Refresh");
        submit.setOnAction(value -> {
            while(designationText.getText() == null || designationText.getText().trim().isEmpty() || stateText.getText() == null || stateText.getText().trim().isEmpty() || colorText.getText() == null || colorText.getText().trim().isEmpty() || shipBrandText.getText() == null || shipBrandText.getText().trim().isEmpty() ||
                    shipModelText.getText() == null || shipYearText.getText() == null || shipYearText.getText().trim().isEmpty() || weightText.getText() == null || weightText.getText().trim().isEmpty() ||
                    stockPlacementText.getText() ==  null || designationText.getText() == null || designationText.getText().trim().isEmpty() || DescriptionStateText.getText() == null || DescriptionStateText.getText().trim().isEmpty() || !picture || !picture1 || !picture2 || !picture3 || !picture4 || !picture5){
                designationText.setPromptText("Ce champ ne peut pas être vide");
                stateText.setPromptText("Ce champ ne peut pas être vide");
                colorText.setPromptText("Ce champ ne peut pas être vide");
                shipBrandText.setPromptText("Ce champ ne peut pas être vide");
                shipModelText.setPromptText("Ce champ ne peut pas être vide");
                shipYearText.setPromptText("Ce champ ne peut pas être vide");
                weightText.setPromptText("Ce champ ne peut pas être vide");
                stockPlacementText.setPromptText("Ce champ ne peut pas être vide");
                DescriptionStateText.setPromptText("Ce champ ne peut pas être vide");

                return;
            }
                    try {
                        if ((HTMLrequests.HTMLrequests("get_storage/"+stockPlacementText.getText())).equals("[]")){
                                new shelf(primaryStage, stockPlacementText.getText());
                        }
                        String barcodeName = barcode.barcode(designationText.getText().toUpperCase(Locale.ROOT).charAt(0), stockPlacementText.getText());
                        String barcodeFileName = "barcodes/" + barcodeName;
                        System.out.println("Creating barcode...");
                        imageProcess.imageProcess(designationText.getText(), stateText.getText(), shipBrandText.getText(), shipModelText.getText(), shipYearText.getText(), colorText.getText(), weightText.getText(), stockPlacementText.getText(), barcodeFileName);
                        System.out.println("Processing image...");
                        BarcodePrinter.BarcodePrinter(barcodeFileName);
                        System.out.println("Printing image...");
                        String metas = "put_metas/" + designationText.getText() + "/" + stateText.getText() + "/" + colorText.getText() + "/" + shipBrandText.getText() + "/" + shipModelText.getText() + "/" +
                                shipYearText.getText() + "/" + stockPlacementText.getText() + "/" + weightText.getText() + "/" + barcodeName + "/" + DescriptionStateText.getText() + "/%20"+"/%20";
                        HTMLrequests.HTMLrequests(metas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


        );
        designationText.setPromptText("Poulie simple");
        referenceText.setPromptText("66978");
        stateText.setPromptText("Neuf");
        colorText.setPromptText("Noir");
        brandText.setPromptText("HOLT");
        shipBrandText.setPromptText("Sealine");
        shipModelText.setPromptText("F42/5");
        shipYearText.setPromptText("2010");
        shipBrandText.setPromptText("Sealine");
        shipNumberText.setPromptText("ABC 67436 B6 06");
        weightText.setPromptText("2 kg");
        stockPlacementText.setPromptText("4F");
        DescriptionStateText.setPromptText("Rayure côté droit");


        root.addRow(1, reference, referenceText);
        root.addRow(2, state, stateText);
        root.addRow(3, DescriptionState, DescriptionStateText);
        root.addRow(4, color, colorText);
        root.addRow(5, brand, brandText);
        root.addRow(6, shipBrand, shipBrandText);
        root.addRow(7, shipModel, shipModelText);
        root.addRow(8, shipYear, shipYearText);
        root.addRow(9, shipNumber, shipNumberText);
        root.addRow(10, stockPlacement, stockPlacementText);
        root.addRow(11, weight, weightText);
        root.addRow(12, openCameraButton, openBarcodeScannerButton);
        root.addRow(13, submit);
        root.addRow(14, images);




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
                picture = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        removeImage1.setOnAction(value -> {
            try {
                imageView1.setImage(new Image (new FileInputStream("no-image.png")));
                picture1 = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        removeImage2.setOnAction(value -> {
            try {
                imageView2.setImage(new Image (new FileInputStream("no-image.png")));
                picture2 = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        removeImage3.setOnAction(value -> {
            try {
                imageView3.setImage(new Image (new FileInputStream("no-image.png")));
                picture3 = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        removeImage4.setOnAction(value -> {
            try {
                imageView4.setImage(new Image (new FileInputStream("no-image.png")));
                picture4 = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        removeImage5.setOnAction(value -> {
            try {
                imageView5.setImage(new Image (new FileInputStream("no-image.png")));
                picture5 = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
            while ((designationText.getText() == null || designationText.getText().trim().isEmpty())) {
                designationText.setPromptText("Ce champ ne peut pas être vide");
                return;
            }
            if (webcam != null) {
                int i = 0;
                try {
                    String omg = Camera.Camera(designationText.getText());

                    System.out.println(omg);
                   //imageView.setImage(new Image(new FileInputStream("img_tmp/"+omg)));
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
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                label.setText("No webcam detected");
            }
        });


        primaryStage.getIcons().add(new Image("file:img_static/captainchercheur.png"));
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
