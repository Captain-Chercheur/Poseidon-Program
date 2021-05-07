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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.print.PrintService;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import javax.print.PrintServiceLookup;



public class Main extends Application {
    public Main() throws IOException {

    }


    public static void main(String[] args) { Application.launch(args); }


    private final Desktop desktop = Desktop.getDesktop();


    public final Label designation = new Label("Désignation de la pièce*");
    private final TextField designationText = new TextField();
    private final Label reference = new Label("Référence du produit");
    private final TextField referenceText = new TextField();
    private final Label state = new Label("Etat de la pièce*");

    private final Label DescriptionState = new Label("Description de l'état de la pièce*");
    private final TextField DescriptionStateText = new TextField();
    private final Label color = new Label("Couleur de la pièce*");
    private final TextField colorText = new TextField();
    private final Label brand = new Label("Marque de la pièce");
    private final TextField brandText = new TextField();
    private final Label shipBrand = new Label("Marque du bateau de provenance*");
    private final TextField shipBrandText = new TextField();
    private final Label shipModel = new Label("Model du bateau de provenance*");
    private final TextField shipModelText = new TextField();
    private final Label shipYear = new Label("Année du bateau de provenance");
    private final TextField shipYearText = new TextField();
    private final Label shipNumber = new Label("Numéro de série du bateau (NIC)");
    private final TextField shipNumberText = new TextField();
    private final Label weight = new Label("Poids de la pièce (en gramme) *");
    private final TextField weightText = new TextField();
    private final Label stockPlacement = new Label("Emplacement de stockage de la pièce*");
    private final TextField stockPlacementText = new TextField();
    private final Label quantity = new Label("Quantité*");
    private final TextField quantityText = new TextField();
    private final Label accessoire = new Label("Accessoires fournies");
    private final TextField accessoireText = new TextField();
    private final Label descriptionComplementaire = new Label("Description complémentaire*");
    private final TextField descriptionComplementaireText = new TextField();

    private final Label PVPHT = new Label("Prix de vente HT (presse enter)* : ");
    private final TextField PVPHTText = new TextField();
    private final Label PVPCommission  = new Label("Prix de vente HT avec commission : ");
    private final Label PVPTTC = new Label("Prix de vente TTC : ");
    private final Label PrixConseille = new Label("Prix de vente HT coneillé : ");
    private final Label EtatDuProduit = new Label("");


    private final Label faceAvant = new Label("Face avant");
    private final Label faceArrière = new Label("Face arrière");
    private final Label faceGauche = new Label("Face gauche");
    private final Label faceDroite = new Label("Face droite");
    private final Label faceDessous = new Label("Face dessous");
    private final Label faceDessus = new Label("Face dessus");


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
    Image takeFileImage = new Image(new FileInputStream("img_static/open-file-folder.png"));
    Image helpImage = new Image(new FileInputStream("img_static/question_mark.png"));


    private static final ImageView imageView = new ImageView(image);
    private static final ImageView imageView1 = new ImageView(image1);
    private static final ImageView imageView2 = new ImageView(image2);
    private static final ImageView imageView3 = new ImageView(image3);
    private static final ImageView imageView4 = new ImageView(image4);
    private static final ImageView imageView5 = new ImageView(image5);

    private final ImageView crossImageView = new ImageView(crossImage);
    private final ImageView crossImageView1 = new ImageView(crossImage);
    private final ImageView crossImageView2 = new ImageView(crossImage);
    private final ImageView crossImageView3 = new ImageView(crossImage);
    private final ImageView crossImageView4 = new ImageView(crossImage);
    private final ImageView crossImageView5 = new ImageView(crossImage);

    private final ImageView takeFileImageView = new ImageView(takeFileImage);
    private final ImageView takeFileImageView1 = new ImageView(takeFileImage);
    private final ImageView takeFileImageView2 = new ImageView(takeFileImage);
    private final ImageView takeFileImageView3 = new ImageView(takeFileImage);
    private final ImageView takeFileImageView4 = new ImageView(takeFileImage);
    private final ImageView takeFileImageView5 = new ImageView(takeFileImage);

    private final ImageView helpImageView = new ImageView(helpImage);
    private final ImageView helpImageView2 = new ImageView(helpImage);


    Button openCameraButton = new Button("Take picture");
    Button openBarcodeScannerButton = new Button("Read barcode");
    Hyperlink removeImage = new Hyperlink();
    Hyperlink removeImage1 = new Hyperlink();
    Hyperlink removeImage2 = new Hyperlink();
    Hyperlink removeImage3 = new Hyperlink();
    Hyperlink removeImage4 = new Hyperlink();
    Hyperlink removeImage5 = new Hyperlink();
    Hyperlink FileImage = new Hyperlink();
    Hyperlink FileImage1 = new Hyperlink();
    Hyperlink FileImage2 = new Hyperlink();
    Hyperlink FileImage3 = new Hyperlink();
    Hyperlink FileImage4 = new Hyperlink();
    Hyperlink FileImage5 = new Hyperlink();


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

    String selectedItem = null;
    Webcam webcam = Webcam.getDefault();
    String printerChoosen = PrintServiceLookup.lookupDefaultPrintService().toString();
    String selected= "";
    String stateText = "";
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Poseidon Program");

        GridPane root = new GridPane();

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
            int selectedIndex = combo_box.getSelectionModel().getSelectedIndex();
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
        root.addRow(1, designation, designationText);
        Button submit = new Button("Submit");
        Button ChangeQuantities = new Button("Change quantities");
        submit.setOnAction(value -> {
            while(designationText.getText() == null || designationText.getText().trim().isEmpty() || colorText.getText() == null || colorText.getText().trim().isEmpty() || shipBrandText.getText() == null || shipBrandText.getText().trim().isEmpty() ||
                    shipModelText.getText() == null || weightText.getText() == null || weightText.getText().trim().isEmpty() ||
                    stockPlacementText.getText() ==  null || designationText.getText() == null || designationText.getText().trim().isEmpty() || DescriptionStateText.getText() == null || DescriptionStateText.getText().trim().isEmpty() || !picture || !picture1 || !picture2 || !picture3 || !picture4 || !picture5){
                designationText.setPromptText("Ce champ ne peut pas être vide");
                colorText.setPromptText("Ce champ ne peut pas être vide");
                shipBrandText.setPromptText("Ce champ ne peut pas être vide");
                shipModelText.setPromptText("Ce champ ne peut pas être vide");
                weightText.setPromptText("Ce champ ne peut pas être vide");
                stockPlacementText.setPromptText("Ce champ ne peut pas être vide");
                DescriptionStateText.setPromptText("Ce champ ne peut pas être vide");

                return;
            }
                    try {
                        if ((HTMLrequests.HTMLrequests("get_storage/"+stockPlacementText.getText())).equals("[]")){
                            new shelf(primaryStage, stockPlacementText.getText(), designationText.getText(), printerChoosen);
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


                            imageProcess.imageProcess(designationText.getText(), selected, shipBrandText.getText(), shipModelText.getText(), shipYearText.getText(), colorText.getText(), weightText.getText(), stockPlacementText.getText(), barcodeFileName);
                            System.out.println("Processing image...");
                            BarcodePrinter.BarcodePrinter(barcodeFileName, printerChoosen);
                            System.out.println("Printing image...");
                            String AllImages = "";
                            for (int j = 0; j < saveImagesDatabase.AllImages.size(); j++){
                                AllImages = saveImagesDatabase.getAllImages().toString().replaceAll(" ", "").replaceAll(",", "-");

                            }
                            String metas = "put_metas/" + designationText.getText() + "/" + selected + "/" + colorText.getText() + "/" + shipBrandText.getText() + "/" + shipModelText.getText() + "/" +
                                    shipYearText.getText() + "/" + stockPlacementText.getText() + "/" + weightText.getText() + "/" + barcodeName + "/" + DescriptionStateText.getText() + "/%20"+"/%20/" + quantityText.getText() + "/" + accessoireText.getText() + "/" + descriptionComplementaireText.getText() + "/\"" + AllImages + "\"";
                            HTMLrequests.HTMLrequests(metas);

                            shelf.quantity.waiting_shelving(primaryStage, stockPlacementText.getText(), id_int, 1);
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




        PVPHTText.setOnAction(e -> {
            float PVPHT_value = Integer.parseInt(PVPHTText.getText());
            float Commission = (float) (PVPHT_value * 1.1);
            float PVPTTC_value = (float) (Commission * 1.2);

            PVPCommission.setText("Prix de vente HT avec commission : " + Commission + " (10%)");
            PVPTTC.setText("Prix de vente TTC : " + PVPTTC_value + " (20%)");
        });


        // PrixConseille.setText("Nous vous conseillons de vendre la pièce (HT) pour : " + advisedPrice);
        Menu menu = new Menu("File");
        MenuItem menuItem1 = new MenuItem("Exit");
        menu.getItems().add(menuItem1);

        Menu menu1 = new Menu("Préférences");

        Menu subMenu = new Menu("Camera par défaut");

        Menu PrinterSubMenu = new Menu("Imprimante par défaut");



        ObservableList<Menus.WebCamInfo> Webcaminfos = Menus.defaultCamera();

        RadioMenuItem choice1Item = new RadioMenuItem(Webcaminfos.get(0).toString());
        RadioMenuItem choice2Item = new RadioMenuItem(Webcaminfos.get(1).toString());
        RadioMenuItem choice3Item = new RadioMenuItem(Webcaminfos.get(2).toString());
        RadioMenuItem choice4Item = new RadioMenuItem(Webcaminfos.get(3).toString());
        RadioMenuItem choice5Item = new RadioMenuItem(Webcaminfos.get(4).toString());

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
                if ((" " + choice4Item.getText()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                    choice4Item.setSelected(true);
                }
                if ((" " + choice5Item.getText()).equals(Webcam.getDefault().toString().replaceAll("Webcam", ""))){
                    choice5Item.setSelected(true);
                }
            }
        }

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(choice1Item, choice2Item, choice3Item, choice4Item, choice5Item);
        subMenu.getItems().addAll(choice1Item, choice2Item, choice3Item, choice4Item, choice5Item);


        subMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent e) {
                RadioMenuItem selectedRadioButton = (RadioMenuItem) toggleGroup.getSelectedToggle();
                String toogleGroupValue = selectedRadioButton.getText();
                webcam = Webcam.getWebcamByName(toogleGroupValue);
            }
        });

        MenuBar menuBar = new MenuBar();

        menuBar.getMenus().addAll(menu, menu1);

        menuItem1.setOnAction(e -> {
            primaryStage.close();
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
                String toogleGroupValue = selectedRadioButton.getText();
                printerChoosen = toogleGroupValue;
            }
        });

        menu1.getItems().addAll(subMenu, PrinterSubMenu);
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


        root.addRow(0, menuBar);
        root.addRow(2, reference, referenceText);
        root.addRow(3, state, combo_box);
        root.addRow(4, DescriptionState, DescriptionStateText);
        root.addRow(5, color, colorText);
        root.addRow(6, brand, brandText);
        root.addRow(7, descriptionComplementaire, descriptionComplementaireText);
        root.addRow(8, accessoire, accessoireText);
        root.addRow(9, shipBrand, shipBrandText);
        root.addRow(10, shipModel, shipModelText);
        root.addRow(11, shipYear, shipYearText);
        root.addRow(12, shipNumber, shipNumberText);
        root.addRow(13, stockPlacement, stockPlacementText);
        root.addRow(14, weight, weightText);
        root.addRow(15, quantity, quantityText);
        root.addRow(16, PVPHT, PVPHTText);
        root.addRow(17, EtatDuProduit);
        root.addRow(18, PVPCommissionGroup);
        root.addRow(19, PVPTTCGroup);
        root.addRow(20, openCameraButton, openBarcodeScannerButton);
        root.addRow(21, submit, ChangeQuantities);
        root.addRow(22, images);


        root.setAlignment(Pos.TOP_CENTER);



        HBox hbox = new HBox(root);

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


        Scene scene = new Scene(hbox, 1000, 1000, Color.WHITE);
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
    public static void takePhoto (TextField designationText, Webcam webcam){
        while ((designationText.getText() == null || designationText.getText().trim().isEmpty())) {
            designationText.setPromptText("Ce champ ne peut pas être vide");
            return;
        }
        if (webcam != null) {
            int i = 0;
            try {
                String omg = Camera.Camera(designationText.getText(), webcam);

                if (getAllImages(new File("img_tmp/"), false) == null) {
                    System.out.println("no images");
                } else {
                    String[] aled = getAllImages(new File("img_tmp/"), false).toArray(new String[0]);
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

    private String openFile() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile.getAbsolutePath());


        return selectedFile.getAbsolutePath();
    }

}
