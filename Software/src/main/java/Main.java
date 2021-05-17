import com.thoughtworks.qdox.model.expression.Add;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.Objects;



public class Main extends Application {
    public Main() throws Exception {

    }

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

    public void start(Stage primaryStage) throws Exception {
        LoginApplication.test();


    }
    public static void mainPage(Stage primaryStage) throws Exception {


        Image image = new Image("file:img_static/plus-icon.png");
        Image image2 = new Image("file:img_static/parameter-icon.png");

        //Creating the image view
        ImageView imageView1 = new ImageView(image);
        ImageView imageView2 = new ImageView(image2);
        GridPane Gridpane = new GridPane();
        Image ImageBackground = Pieces.Menu(primaryStage, Gridpane);

        AddPieces.setGraphic(imageView1);
        AddPieces.setText("Ajouter une pièce   ");
        AddPieces.setStyle("-fx-background-color: #23a0da;-fx-text-fill: white");
        AddPieces.setOnAction(e->{
            Pieces.aled(primaryStage);
        });

        ViewStock.setGraphic(imageView2);
        ViewStock.setText("Visualiser les stocks");
        ViewStock.setStyle("-fx-background-color: #A9A9A9;-fx-text-fill: white");
        ViewStock.setOnAction(e->{

        });

        Gridpane.addColumn(0, AddPieces, ViewStock);


        Scene scene = new Scene(Gridpane);

        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);

        primaryStage.getIcons().add(new Image("file:img_static/captainchercheur.png"));

        primaryStage.setScene(scene);
        //primaryStage.show();
        databaseVisualizer.test(primaryStage);
    }

}
