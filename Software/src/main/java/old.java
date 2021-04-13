import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.sun.javafx.menu.MenuItemBase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;

public class old extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Button Experiment 1");
        Webcam webcam = Webcam.getDefault();
        Label label = new Label("Not clicked");
        Button button = new Button("test");

        button.setOnAction(value ->  {
            label.setText("Clicked!");
            if (webcam != null) {
                try {
                    Camera.Camera();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No webcam detected");
            }
        });


        HBox hbox = new HBox(button,label);


        Scene scene = new Scene(hbox, 400, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
