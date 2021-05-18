import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 *
 * @author Bushan Sirgur
 */
public class LoginApplication {

    public static void test(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(LoginApplication.class.getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}