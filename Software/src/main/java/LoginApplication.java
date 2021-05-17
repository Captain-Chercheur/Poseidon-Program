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

    public static void test() throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(LoginApplication.class.getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}