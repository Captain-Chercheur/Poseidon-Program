
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends Application {
    public Main() {

    }
    static Image crossImage;

    static {
        try {
            crossImage = new Image(new FileInputStream("img_static/red-cross.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static final ImageView crossImageView = new ImageView(crossImage);
    private static final ImageView crossImageView1 = new ImageView(crossImage);
    static Hyperlink removeImage = new Hyperlink();
    static Hyperlink removeImage1 = new Hyperlink();

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    static Hyperlink AddPieces = new Hyperlink();
    static Hyperlink ViewStock = new Hyperlink();
    static boolean visibility1 = true;
    static boolean visibility2 = true;

    public void start(Stage primaryStage) throws Exception {
        //LoginApplication.test(primaryStage);
        mainPage(primaryStage);

    }

    public static void mainPage(Stage primaryStage) throws Exception {
        removeImage.setGraphic(crossImageView);
        removeImage1.setGraphic(crossImageView1);
        removeImage.setLayoutX(0);
        removeImage.setLayoutY(20);
        removeImage1.setLayoutX(250);
        removeImage1.setLayoutY(20);



        Image image = new Image("file:img_static/plus-icon.png");
        Image image2 = new Image("file:img_static/parameter-icon.png");

        HBox hbox = new HBox();
        //hbox.setPadding(new Insets(15, 12, 15, 12));
        //hbox.setSpacing(10);
        /*hbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");*/

        //Creating the image view
        ImageView imageView1 = new ImageView(image);
        ImageView imageView2 = new ImageView(image2);
        GridPane Gridpane = new GridPane();
        Pieces.Menu(primaryStage, Gridpane);

        GridPane addModifyValues = new GridPane();
        GridPane addModifyValues2 = new GridPane();

        ColumnConstraints column = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();

        column.setMaxWidth((Screen.getPrimary().getBounds().getMaxX())/2);
        column2.setMaxWidth((Screen.getPrimary().getBounds().getMaxX())/2);

        addModifyValues.setStyle("-fx-padding: 10;" + "-fx-border-style:  solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-color: black;" + "-fx-background-color: lightgrey");
        addModifyValues2.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-color: black;" + "-fx-background-color: lightgrey");
        addModifyValues2.getColumnConstraints().add(column);
        addModifyValues.getColumnConstraints().add(column2);
        addModifyValues2.setVisible(false);
        addModifyValues.setVisible(false);
        removeImage.setVisible(false);
        removeImage1.setVisible(false);

        AddPieces.setGraphic(imageView1);
        AddPieces.setText("Ajouter une pièce   ");
        AddPieces.setStyle("-fx-background-color: #23a0da;-fx-text-fill: white");
        hbox.getChildren().addAll(databaseVisualizer.test(addModifyValues), removeImage);
        hbox.getChildren().addAll(Pieces.aled(addModifyValues2), removeImage1);



        ViewStock.setGraphic(imageView2);
        ViewStock.setText("Visualiser les stocks");
        ViewStock.setStyle("-fx-background-color: #A9A9A9;-fx-text-fill: white");
        ViewStock.setOnAction(e->{
            try {

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        removeImage.setOnAction(value -> {
            visibility1 = false;
            addModifyValues.setVisible(false);
            removeImage.setVisible(false);

        });
        removeImage1.setOnAction(value -> {
            visibility2 = false;
            addModifyValues2.setVisible(false);
            removeImage1.setVisible(false);
        });
        AddPieces.setOnAction(e->{
            if (visibility1 && visibility2) {
                try {
                    addModifyValues2.setVisible(true);
                    HBox.setHgrow(addModifyValues2, Priority.ALWAYS);
                    addModifyValues.setVisible(true);
                    removeImage1.setVisible(true);
                    removeImage.setVisible(true);


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (visibility1){
                addModifyValues2.setVisible(true);
                removeImage1.setVisible(true);
            } else if (visibility2){
                addModifyValues.setVisible(true);
                removeImage.setVisible(true);
            }
        });
        Gridpane.addRow(1, AddPieces);
        Gridpane.addRow(2,  ViewStock);
        Gridpane.addRow(3, hbox);

        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);

        primaryStage.getIcons().add(new Image("file:img_static/captainchercheur.png"));
        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Ajouter une pièce", Gridpane);
        Tab tab2 = new Tab("Visualiser les stocks"  , new Label("Show all cars available"));

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);

        VBox vBox = new VBox(tabPane);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
