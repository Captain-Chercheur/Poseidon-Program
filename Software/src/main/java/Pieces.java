import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import javax.print.PrintService;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import javax.print.PrintServiceLookup;

public abstract class Pieces extends Application {
    public Pieces() throws IOException {

    }


    public static void pieces(String[] args) { Application.launch(args); }


    private static final Desktop desktop = Desktop.getDesktop();


    public static final Label designation = new Label("Désignation de la pièce*");
    private static final TextField designationText = new TextField();
    private static final Label reference = new Label("Référence du produit");
    private static final TextField referenceText = new TextField();
    private static final Label state = new Label("Etat de la pièce*");

    private static final Label DescriptionState = new Label("Description de l'état de la pièce*");
    private static final TextField DescriptionStateText = new TextField();
    private static final Label color = new Label("Couleur de la pièce*");
    private static final TextField colorText = new TextField();
    private static final Label brand = new Label("Marque de la pièce");
    private static final TextField brandText = new TextField();
    private static final Label shipBrand = new Label("Marque du bateau de provenance*");
    private static final TextField shipBrandText = new TextField();
    private static final Label shipModel = new Label("Model du bateau de provenance*");
    private static final TextField shipModelText = new TextField();
    private static final Label shipYear = new Label("Année du bateau de provenance");
    private static final TextField shipYearText = new TextField();
    private static final Label shipNumber = new Label("Numéro de série du bateau (NIC)");
    private static final TextField shipNumberText = new TextField();
    private static final Label weight = new Label("Poids de la pièce (en gramme) *");
    private static final TextField weightText = new TextField();
    private static final Label stockPlacement = new Label("Emplacement de stockage de la pièce*");
    private static final TextField stockPlacementText = new TextField();
    private static final Label quantity = new Label("Quantité*");
    private static final TextField quantityText = new TextField();
    private static final Label accessoire = new Label("Accessoires fournies");
    private static final TextField accessoireText = new TextField();
    private static final Label descriptionComplementaire = new Label("Description complémentaire*");
    private static final TextField descriptionComplementaireText = new TextField();

    private static final Label PVPHT = new Label("Prix de vente HT (Appuyez sur entrer)* : ");
    private static final TextField PVPHTText = new TextField();
    private static final Label PVPCommission  = new Label("Prix de vente HT avec commission : ");
    private static final Label PVPTTC = new Label("Prix de vente TTC : ");
    private static final Label PrixConseille = new Label("Prix de vente HT coneillé : ");
    private static final Label EtatDuProduit = new Label("");


    private static final Label faceAvant = new Label("Face avant");
    private static final Label faceArrière = new Label("Face arrière");
    private static final Label faceGauche = new Label("Face gauche");
    private static final Label faceDroite = new Label("Face droite");
    private static final Label faceDessous = new Label("Face dessous");
    private static final Label faceDessus = new Label("Face dessus");


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


    static Image crossImage;

    static {
        try {
            crossImage = new Image(new FileInputStream("img_static/red-cross.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Image takeFileImage;

    static {
        try {
            takeFileImage = new Image(new FileInputStream("img_static/open-file-folder.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Image helpImage;

    static {
        try {
            helpImage = new Image(new FileInputStream("img_static/question_mark.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static final ImageView imageView = new ImageView(image);
    private static final ImageView imageView1 = new ImageView(image1);
    private static final ImageView imageView2 = new ImageView(image2);
    private static final ImageView imageView3 = new ImageView(image3);
    private static final ImageView imageView4 = new ImageView(image4);
    private static final ImageView imageView5 = new ImageView(image5);

    private static final ImageView crossImageView = new ImageView(crossImage);
    private static final ImageView crossImageView1 = new ImageView(crossImage);
    private static final ImageView crossImageView2 = new ImageView(crossImage);
    private static final ImageView crossImageView3 = new ImageView(crossImage);
    private static final ImageView crossImageView4 = new ImageView(crossImage);
    private static final ImageView crossImageView5 = new ImageView(crossImage);

    private static final ImageView takeFileImageView = new ImageView(takeFileImage);
    private static final ImageView takeFileImageView1 = new ImageView(takeFileImage);
    private static final ImageView takeFileImageView2 = new ImageView(takeFileImage);
    private static final ImageView takeFileImageView3 = new ImageView(takeFileImage);
    private static final ImageView takeFileImageView4 = new ImageView(takeFileImage);
    private static final ImageView takeFileImageView5 = new ImageView(takeFileImage);

    private static final ImageView helpImageView = new ImageView(helpImage);
    private static final ImageView helpImageView2 = new ImageView(helpImage);


    static Button openCameraButton = new Button("Take picture");
    static Button openBarcodeScannerButton = new Button("Read barcode");
    static Hyperlink removeImage = new Hyperlink();
    static Hyperlink removeImage1 = new Hyperlink();
    static Hyperlink removeImage2 = new Hyperlink();
    static Hyperlink removeImage3 = new Hyperlink();
    static Hyperlink removeImage4 = new Hyperlink();
    static Hyperlink removeImage5 = new Hyperlink();
    static Hyperlink FileImage = new Hyperlink();
    static Hyperlink FileImage1 = new Hyperlink();
    static  Hyperlink FileImage2 = new Hyperlink();
    static Hyperlink FileImage3 = new Hyperlink();
    static Hyperlink FileImage4 = new Hyperlink();
    static Hyperlink FileImage5 = new Hyperlink();

    static Image ImageBackground;
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

    static Webcam webcam = Webcam.getDefault();
    static String printerChoosen = PrintServiceLookup.lookupDefaultPrintService().toString();
    static String selected= "";
    static String stateText = "";

    public static GridPane aled(GridPane root) {




        Group images = new Group(imageView, imageView1, imageView2,imageView3, imageView4, imageView5, removeImage, removeImage1, removeImage2, removeImage3, removeImage4, removeImage5, faceAvant, faceArrière, faceGauche,faceDroite, faceDessous, faceDessus,
                FileImage,FileImage1,FileImage2,FileImage3,FileImage4,FileImage5);

        removeImage.setGraphic(crossImageView);
        removeImage1.setGraphic(crossImageView1);
        removeImage2.setGraphic(crossImageView2);
        removeImage3.setGraphic(crossImageView3);
        removeImage4.setGraphic(crossImageView4);
        removeImage5.setGraphic(crossImageView5);

        FileImage.setGraphic(takeFileImageView);
        FileImage1.setGraphic(takeFileImageView1);
        FileImage2.setGraphic(takeFileImageView2);
        FileImage3.setGraphic(takeFileImageView3);
        FileImage4.setGraphic(takeFileImageView4);
        FileImage5.setGraphic(takeFileImageView5);



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
        imageView.setY(20);
        imageView1.setX(250);
        imageView1.setY(20);
        imageView2.setX(500);
        imageView2.setY(20);
        imageView3.setX(0);
        imageView3.setY(220);
        imageView4.setX(250);
        imageView4.setY(220);
        imageView5.setX(500);
        imageView5.setY(220);

        removeImage.setLayoutX(0);
        removeImage.setLayoutY(20);
        removeImage1.setLayoutX(250);
        removeImage1.setLayoutY(20);
        removeImage2.setLayoutX(500);
        removeImage2.setLayoutY(20);
        removeImage3.setLayoutX(0);
        removeImage3.setLayoutY(220);
        removeImage4.setLayoutX(250);
        removeImage4.setLayoutY(220);
        removeImage5.setLayoutX(500);
        removeImage5.setLayoutY(220);

        FileImage.setLayoutX(230);
        FileImage.setLayoutY(20);
        FileImage1.setLayoutX(480);
        FileImage1.setLayoutY(20);
        FileImage2.setLayoutX(730);
        FileImage2.setLayoutY(20);
        FileImage3.setLayoutX(230);
        FileImage3.setLayoutY(220);
        FileImage4.setLayoutX(480);
        FileImage4.setLayoutY(220);
        FileImage5.setLayoutX(730);
        FileImage5.setLayoutY(220);

        faceAvant.setLayoutX(0);
        faceAvant.setLayoutY(0);
        faceArrière.setLayoutX(250);
        faceArrière.setLayoutY(0);
        faceGauche.setLayoutX(500);
        faceGauche.setLayoutY(0);
        faceDroite.setLayoutX(0);
        faceDroite.setLayoutY(200);
        faceDessous.setLayoutX(250);
        faceDessous.setLayoutY(200);
        faceDessus.setLayoutX(500);
        faceDessus.setLayoutY(200);

        // States
        String[] etat =
                {"Etat neuf", "Bon etat", "Mauvais etat"};
        // Create a combo box
        ComboBox<String> combo_box =
                new ComboBox<>(FXCollections
                        .observableArrayList(etat));



        combo_box.setOnAction((event) -> {
            Object selectedItem = combo_box.getSelectionModel().getSelectedItem();
            selected = (String) selectedItem;
            System.out.println(selected);
        });

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        stateText = combo_box.getValue();
                        System.out.println("test" + stateText);
                        if (stateText.equals("Etat neuf")){
                            EtatDuProduit.setText("Prix de vente HT coneillé pour un produit en très bon état: Maximum 75% du prix neuf");
                        }if (stateText.equals("Bon etat")){
                        EtatDuProduit.setText("Prix de vente HT coneillé pour un produit en bon état : Maximum 50% du prix neuf");
                    }if (stateText.equals("Mauvais etat")){
                        EtatDuProduit.setText("Prix de vente HT coneillé pour un produit en mauvais état : Maximum 25% du prix neuf");
                    }
                    }
                };
        root.addRow(2, designation, designationText);
        Button submit = new Button("Submit");
        Button ChangeQuantities = new Button("Change quantities");
        submit.setOnAction(value -> {

                    while(designationText.getText() == null || designationText.getText().trim().isEmpty() || colorText.getText() == null || colorText.getText().trim().isEmpty() || shipBrandText.getText() == null || shipBrandText.getText().trim().isEmpty() ||
                            shipModelText.getText() == null || weightText.getText() == null || weightText.getText().trim().isEmpty() ||
                            stockPlacementText.getText() ==  null || designationText.getText() == null || designationText.getText().trim().isEmpty() || DescriptionStateText.getText() == null || DescriptionStateText.getText().trim().isEmpty()){
                        designationText.setPromptText("Ce champ ne peut pas être vide");
                        colorText.setPromptText("Ce champ ne peut pas être vide");
                        shipBrandText.setPromptText("Ce champ ne peut pas être vide");
                        shipModelText.setPromptText("Ce champ ne peut pas être vide");
                        weightText.setPromptText("Ce champ ne peut pas être vide");
                        stockPlacementText.setPromptText("Ce champ ne peut pas être vide");
                        DescriptionStateText.setPromptText("Ce champ ne peut pas être vide");
                        System.out.println("400");
                        return;
                    }
                    try {
                        if ((HTMLrequests.HTMLrequests("get_storage/"+stockPlacementText.getText())).equals("[]")){
                            new shelf(new Stage(), stockPlacementText.getText(), designationText.getText(), printerChoosen);
                            System.out.println("406");
                        }


                        for (int i=0; i < Integer.parseInt(quantityText.getText()); i++){
                            String id = HTMLrequests.HTMLrequests("get_id/").replaceAll("(^\\[|\\]$)", "");
                            id= id.replaceAll("(^\\[|\\]$)", "");
                            id = id.replaceAll("(^\\[|\\]$)", "");
                            int id_int = Integer.parseInt(id);
                            id_int = id_int+1;
                            shelf.quantity.adding_quantity(stockPlacementText.getText(), quantityText.getText());
                            String barcodeName = barcode.barcode(designationText.getText().toUpperCase(Locale.ROOT).charAt(0), stockPlacementText.getText());
                            String barcodeFileName = "barcodes/" + barcodeName;
                            System.out.println("Creating barcode...");
                            System.out.println("420");

                            imageProcess.imageProcess(designationText.getText(), selected, shipBrandText.getText(), shipModelText.getText(), shipYearText.getText(), colorText.getText(), weightText.getText(), stockPlacementText.getText(), barcodeFileName);
                            System.out.println("Processing image...");
                            BarcodePrinter.BarcodePrinter(barcodeFileName, printerChoosen);
                            System.out.println("Printing image...");
                            String AllImages = "";
                            for (int j = 0; j < saveImagesDatabase.AllImages.size(); j++){
                                AllImages = saveImagesDatabase.getAllImages().toString().replaceAll(" ", "").replaceAll(",", "_");
                                System.out.println("429");
                            }
                            String metas = "put_metas/" + designationText.getText() + "/" + selected + "/" + colorText.getText() + "/" + shipBrandText.getText() + "/" + shipModelText.getText() + "/" +
                                    shipYearText.getText() + "/" + stockPlacementText.getText() + "/" + weightText.getText() + "/" + barcodeName + "/" + DescriptionStateText.getText() + "/%20"+"/%20/" + quantityText.getText() + "/" + accessoireText.getText() + "/" + descriptionComplementaireText.getText() + "/\"" + AllImages + "\"";
                            HTMLrequests.HTMLrequests(metas);
                            System.out.println("434");
                            shelf.quantity.waiting_shelving(new Stage(), stockPlacementText.getText(), id_int, 1);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


        );
        designationText.setPromptText("Poulie simple");
        referenceText.setPromptText("66978");
        colorText.setPromptText("Noir");
        brandText.setPromptText("HOLT");
        shipBrandText.setPromptText("Beneteau");
        shipModelText.setPromptText("First 40");
        shipYearText.setPromptText("2008");
        shipNumberText.setPromptText("ABC 67436 B6 06");
        weightText.setPromptText("200 g");
        stockPlacementText.setPromptText("4F");
        DescriptionStateText.setPromptText("Rayure côté droit");
        descriptionComplementaireText.setPromptText("Poulie simple pour palen");
        descriptionComplementaireText.setPromptText("Poulie simple pour palen");
        descriptionComplementaireText.setPromptText("Poulie simple pour palen");
        accessoireText.setPromptText("Cable Pour écran");

        System.out.println("462");


        PVPHTText.setOnAction(e -> {
            float PVPHT_value = Integer.parseInt(PVPHTText.getText());
            float Commission = (float) (PVPHT_value * 1.1);
            float PVPTTC_value = (float) (Commission * 1.2);

            PVPCommission.setText("Prix de vente HT avec commission : " + Commission + " (10%)");
            PVPTTC.setText("Prix de vente TTC : " + PVPTTC_value + " (20%)");
        });



        final Tooltip tooltip = new Tooltip();
        final Tooltip tooltip2 = new Tooltip();
        tooltip.setText("Prix avec commission : 10%");
        tooltip2.setText("Prix avec taxes : 20%");

        helpImageView.setImage(helpImage);

        Tooltip.install(helpImageView, new Tooltip("Prix avec commission : 10%"));
        helpImageView.setLayoutX(235);
        Group PVPCommissionGroup = new Group(PVPCommission, helpImageView);

        Tooltip.install(helpImageView2, new Tooltip("Prix avec taxes : 20%"));
        helpImageView2.setLayoutX(130);
        Group PVPTTCGroup = new Group(PVPTTC, helpImageView2);






        root.addRow(3, reference, referenceText);
        root.addRow(4, state, combo_box);
        root.addRow(5, DescriptionState, DescriptionStateText);
        root.addRow(6, color, colorText);
        root.addRow(7, brand, brandText);
        root.addRow(8, descriptionComplementaire, descriptionComplementaireText);
        root.addRow(9, accessoire, accessoireText);
        root.addRow(10, shipBrand, shipBrandText);
        root.addRow(11, shipModel, shipModelText);
        root.addRow(12, shipYear, shipYearText);
        root.addRow(13, shipNumber, shipNumberText);
        root.addRow(14, stockPlacement, stockPlacementText);
        root.addRow(15, weight, weightText);
        root.addRow(16, quantity, quantityText);
        root.addRow(17, PVPHT, PVPHTText);
        root.addRow(18, EtatDuProduit);
        root.addRow(19, PVPCommissionGroup);
        root.addRow(20, PVPTTCGroup);
        root.addRow(21, openCameraButton, openBarcodeScannerButton);
        root.addRow(22, submit, ChangeQuantities);
        root.addRow(23, images);


        //root.setAlignment(Pos.TOP_CENTER);



        VBox hbox = new VBox(root);

        ChangeQuantities.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                try {
                    new quantity_management_scanner();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //Scene scene = new Scene(hbox, 1000, 1000, Color.WHITE);
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


        FileImage.setOnAction(value -> {
            try {
                imageView.setImage(new Image((new FileInputStream(openFile()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        FileImage1.setOnAction(value -> {
            try {
                imageView1.setImage(new Image((new FileInputStream(openFile()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        FileImage2.setOnAction(value -> {
            try {
                imageView2.setImage(new Image((new FileInputStream(openFile()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        FileImage3.setOnAction(value -> {
            try {
                imageView3.setImage(new Image((new FileInputStream(openFile()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        FileImage4.setOnAction(value -> {
            try {
                imageView4.setImage(new Image((new FileInputStream(openFile()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        FileImage5.setOnAction(value -> {
            try {
                imageView5.setImage(new Image((new FileInputStream(openFile()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });


        openBarcodeScannerButton.setOnAction(value -> {
            if (webcam != null) {
                try {
                    new barcodescanner(webcam);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                label.setText("No webcam detected");
            }
        });

        imageView.setOnMouseClicked( event2 -> {
            takePhoto(designationText, webcam);
            saveImagesDatabase.getAllImages();
        });
        imageView1.setOnMouseClicked( event2 -> {
            takePhoto(designationText, webcam);
        });
        imageView2.setOnMouseClicked( event2 -> {
            takePhoto(designationText, webcam);
        });
        imageView3.setOnMouseClicked( event2 -> {
            takePhoto(designationText, webcam);
        });
        imageView4.setOnMouseClicked( event2 -> {
            takePhoto(designationText, webcam);
        });
        imageView5.setOnMouseClicked( event2 -> {
            takePhoto(designationText, webcam);
        });
        return root;
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
    public static void takePhoto (TextField designationText, Webcam webcam){
        while ((designationText.getText() == null || designationText.getText().trim().isEmpty())) {
            designationText.setPromptText("Ce champ ne peut pas être vide");
            return;
        }
        if (webcam != null) {
            int i = 0;
            try {
                String omg = Camera.Camera(designationText.getText(), webcam);

                if (getAllImages(new File("img_tmp\\"), false) == null) {
                    System.out.println("no images");
                } else {
                    String[] aled = getAllImages(new File("img_tmp\\"), false).toArray(new String[0]);
                    i++;
                    System.out.println(i);

                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Alerte mon général");
        }
    }
    private static String openFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile.getAbsolutePath());


        return selectedFile.getAbsolutePath();
    }

    static Image Menu(Stage primaryStage, GridPane test) {

        Menu helpmenu = new Menu("Help");
        MenuItem helpMenuItem = new MenuItem("Manuel");
        MenuItem helpMenuItem2 = new MenuItem("Signaler un problème");
        helpmenu.getItems().addAll(helpMenuItem, helpMenuItem2);

        Menu menu = new Menu("Poseidon");
        MenuItem menuItem2 = new MenuItem("A propos");
        MenuItem menuItem3 = new MenuItem("Compte");
        MenuItem menuItem1 = new MenuItem("Exit");
        MenuItem menuItemHome = new MenuItem("Acceuil");

        menu.getItems().addAll(menuItemHome,menuItem2, menuItem3, menuItem1);

        Menu menu1 = new Menu("Préférences");

        Menu subMenu = new Menu("Camera par défaut");

        Menu PrinterSubMenu = new Menu("Imprimante par défaut");

        MenuItem subMenuBackground = new MenuItem("Choisir un fond d'écran par défaut");

        ObservableList<Menus.WebCamInfo> Webcaminfos = Menus.defaultCamera();

        RadioMenuItem choice1Item = new RadioMenuItem(Webcaminfos.get(0).toString());
        RadioMenuItem choice2Item = new RadioMenuItem(Webcaminfos.get(1).toString());
        RadioMenuItem choice3Item = new RadioMenuItem(Webcaminfos.get(2).toString());
//        RadioMenuItem choice4Item = new RadioMenuItem(Webcaminfos.get(3).toString());
//        RadioMenuItem choice5Item = new RadioMenuItem(Webcaminfos.get(4).toString());

        for (int i = 0; i < Webcaminfos.size()-1; i++){

            if ((" "  + Webcaminfos.get(i).toString()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                if ((" " + choice1Item.getText()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                    choice1Item.setSelected(true);
                }
                if ((" " + choice2Item.getText()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                    choice2Item.setSelected(true);
                }
                if ((" " + choice3Item.getText()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                    choice3Item.setSelected(true);
                }
             /*   if ((" " + choice4Item.getText()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                    choice4Item.setSelected(true);
                }
                if ((" " + choice5Item.getText()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                    choice5Item.setSelected(true);
                }*/
            }
        }

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(choice1Item, choice2Item, choice3Item/*, choice4Item, choice5Item*/);
        subMenu.getItems().addAll(choice1Item, choice2Item, choice3Item/*,choice4Item, choice5Item*/);

        subMenuBackground.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ImageBackground = new Image("file:"+openFile(), true);
                BackgroundImage myBI= new BackgroundImage(ImageBackground,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, false));
                test.setBackground(new Background(myBI));
            }
        });
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pieces.openBrowser("https://test.captainchercheur.fr/account-2/");
            }
        });
        helpMenuItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent e) {
                WebView web = new WebView();
                web.getEngine().load("https://drive.google.com/file/d/1IGKlF11wx1s5aEgIhBsXiCSInJgYE7Qq/view?usp=sharing");
                GridPane grid = new GridPane();
                grid.addRow(1,web);
                Scene scene = new Scene(grid);
                Stage stageManuel = new Stage();
                stageManuel.setScene(scene);
                stageManuel.show();
            }
        });
        helpMenuItem2.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent e) {
                WebView web = new WebView();
                web.getEngine().load("https://test.captainchercheur.fr/help/");
                Scene scene = new Scene(web);
                Stage stageHelp = new Stage();
                stageHelp.setScene(scene);
                stageHelp.show();
            }
        });
        menuItemHome.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent e) {
                try {
                    Main.mainPage(primaryStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(menu, menu1, helpmenu);

        menuItem1.setOnAction(e -> {
            primaryStage.close();
        });
        menuItem2.setOnAction(e -> {
            GridPane Gridpane = new GridPane();
            GridPane Gridpane2 = new GridPane();
            GridPane Gridpane3 = new GridPane();
            InputStream stream = null;
            try {
                stream = new FileInputStream("img_static/captainchercheur_fullsize.png");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            Image image = new Image(stream);
            //Creating the image view
            ImageView imageView1 = new ImageView(image);
            //Setting the image view parameters
            imageView1.setFitWidth(170);
            imageView1.setPreserveRatio(true);
            Text t = new Text();
            t.setBoundsType(TextBoundsType.VISUAL);
            Label copyright = new Label();
            copyright.setText("© 2019-2021 Cap'tain Chercheur. Tous droits réservés - www.captainchercheur.com");
            copyright.setTextFill(Color.GRAY);

            copyright.setStyle("-fx-font-size: 8pt;-fx-font-family: \"Verdana\";-fx-font-weight: bold;");

            t.setText("Logiciel de gestion créé par Cap'tain  Chercheur\n" +
                    "Version Professionnelle\n" +
                    "Numéro de série : XXXX\n" +
                    "Version 1.5\n");

            t.setStyle("-fx-font-size: 9pt;-fx-font-family: \"Verdana\";");


            Gridpane2.addColumn(1, copyright);
            Gridpane.addColumn(1, imageView1);
            Gridpane.addColumn(2, t);

            Gridpane.setHgap(30);
            Gridpane2.setHgap(30);

            Gridpane.setAlignment(Pos.CENTER);
            Gridpane3.setAlignment(Pos.CENTER);

            Gridpane2.setAlignment(Pos.BOTTOM_CENTER);

            Gridpane3.addRow(1, Gridpane);
            Gridpane3.addRow(10, Gridpane2);

            Scene scene = new Scene(Gridpane3);

            Stage stage = new Stage();
            stage.setTitle("A propos");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(520);
            stage.show();
        });

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);


        RadioMenuItem choice6Item = new RadioMenuItem(printServices[0].toString());
        RadioMenuItem choice7Item = new RadioMenuItem(printServices[1].toString());
        RadioMenuItem choice8Item = new RadioMenuItem(printServices[2].toString());

        ToggleGroup PrinterToggleGroup = new ToggleGroup();
        PrinterToggleGroup.getToggles().add(choice6Item);
        PrinterToggleGroup.getToggles().add(choice7Item);
        PrinterToggleGroup.getToggles().add(choice8Item);

        PrinterSubMenu.getItems().addAll(choice6Item, choice7Item, choice8Item);

        for (int i = 0; i < printServices.length; i++){

            if ((printServices[i].toString()).equals(PrintServiceLookup.lookupDefaultPrintService().toString())){
                if ((choice6Item.getText()).equals(PrintServiceLookup.lookupDefaultPrintService().toString())){
                    choice6Item.setSelected(true);
                    printerChoosen = choice6Item.getText();
                }
                if ((choice7Item.getText()).equals(PrintServiceLookup.lookupDefaultPrintService().toString())){
                    choice7Item.setSelected(true);
                    printerChoosen = choice7Item.getText();
                }
                if ((choice8Item.getText()).equals(PrintServiceLookup.lookupDefaultPrintService().toString())){
                    choice8Item.setSelected(true);
                    printerChoosen = choice8Item.getText();
                }
            }
        }

        PrinterSubMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent e) {
                RadioMenuItem selectedRadioButton = (RadioMenuItem) PrinterToggleGroup.getSelectedToggle();
                printerChoosen = selectedRadioButton.getText();
            }
        });

        menu1.getItems().addAll(subMenu, PrinterSubMenu, subMenuBackground);
        test.addRow(0, menuBar);
        return ImageBackground;
    }
    public static void openBrowser(String url){
        Application a = new Application() {
            @Override
            public void start(Stage stage) throws Exception {

            }
        };
        a.getHostServices().showDocument(url);

    }
}
