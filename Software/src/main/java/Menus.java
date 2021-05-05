import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicReference;

import com.github.sarxos.webcam.Webcam;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Menus {

    static class WebCamInfo {

        private String webCamName;
        private int webCamIndex;

        public void setWebCamName(String webCamName) {
            this.webCamName = webCamName;
        }

        public void setWebCamIndex(int webCamIndex) {
            this.webCamIndex = webCamIndex;
        }

        @Override
        public String toString() {
            return webCamName;
        }
    }
    private Webcam webCam = null;
    private boolean stopCamera = false;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    public static ObservableList<WebCamInfo> defaultCamera() {

        int webCamCounter = 0;
        ObservableList<WebCamInfo> options = FXCollections.observableArrayList();


        for (Webcam webcam : Webcam.getWebcams()) {
            WebCamInfo webCamInfo = new WebCamInfo();
            webCamInfo.setWebCamIndex(webCamCounter);
            webCamInfo.setWebCamName(webcam.getName());
            options.add(webCamInfo);
            webCamCounter++;
        }


        return options;

    }

    }

