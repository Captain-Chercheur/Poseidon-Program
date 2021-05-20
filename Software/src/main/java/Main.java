import com.thoughtworks.qdox.model.expression.Add;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;


public class Main extends Application {
    public Main() {

    }
    static Image crossImage;

    static Tab homeTab;
    static double size1;
    static  double size2;
    static double size3;
    static double size4;

    static Tab aboutTab;
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


        AddPieces.setGraphic(imageView1);
        AddPieces.setText("Ajouter une pièce   ");
        AddPieces.setStyle("-fx-background-color: #23a0da;-fx-text-fill: white");




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

        Gridpane.addRow(1, AddPieces);
        Gridpane.addRow(2,  ViewStock);
        //Gridpane.addRow(3, hbox);

        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);

        primaryStage.getIcons().add(new Image("file:img_static/captainchercheur.png"));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
// Create the necessary panes.
        DraggableTab tab1 = new DraggableTab("Stock");
        tab1.setClosable(true);
        GridPane test = databaseVisualizer.test(addModifyValues);
        tab1.setContent(test);

        System.out.println();
        DraggableTab tab2 = new DraggableTab("Ajouter une pièce");
        tab2.setClosable(true);
        tab2.setContent(Pieces.aled(addModifyValues2));

        /*TabPane tabs = new TabPane();
        TabPane tabs2 = new TabPane();
        tabs.getTabs().add(tab1);
        tabs2.getTabs().add(tab2);


        StackPane root = new StackPane();
        root.getChildren().addAll(tabs);
        StackPane root2 = new StackPane();
        root2.getChildren().addAll(tabs2);
*/
        TabPane tabPane = new TabPane();
        TabPane tabPane1 = new TabPane();
        tabPane.getSelectionModel().select(tab1);
        tabPane1.getSelectionModel().select(tab2);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(tabPane1, tabPane);



        tab1.setOnCloseRequest(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {

                if( tabPane.getTabs().contains( tab1 ) ) {
                    tabPane.getTabs().remove( tab1 );
                    tabPane1.setMinSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());


                }

                arg0.consume();
            }
        });
        tab2.setOnCloseRequest(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {

                if( tabPane1.getTabs().contains( tab2 ) ) {
                    tabPane1.getTabs().remove( tab2 );
                    tabPane.setMinSize(primaryScreenBounds.getWidth()-100, primaryScreenBounds.getHeight()-300);
                }

                arg0.consume();
            }
        });

        AddPieces.setOnAction(actionEvent -> {
            if( !tabPane1.getTabs().contains( tab2 ) ) {

                tabPane1.getTabs().add( tab2 );
                tabPane.setMinSize(500, 500);
            }
            tabPane1.getSelectionModel().select(tab2);
        });

        ViewStock.setOnAction(actionEvent -> {
            if( !tabPane.getTabs().contains( tab1 ) ) {
                tabPane.getTabs().add( tab1 );
                tabPane1.setMinSize(500, 500);
            }
            tabPane.getSelectionModel().select(tab1);
        });


        VBox vbox = new VBox();
        vbox.getChildren().addAll(Gridpane, AddPieces, ViewStock, hbox);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
