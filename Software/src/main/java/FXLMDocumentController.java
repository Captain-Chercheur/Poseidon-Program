
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Bushan Sirgur
 */
public class FXLMDocumentController implements Initializable {



    @FXML
    private TextField textEmail;

    @FXML
    private TextField textKey;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    Connection newconnection = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement newpreparedStatement = null;
    ResultSet resultSet = null;
    ResultSet newresultSet = null;
    int ID;
    public FXLMDocumentController() {
         connection = ConnectionUtil.connectdb();
        newconnection = ConnectionUtil.connectdb();
    }



    public void loginAction(ActionEvent event){
        String email = textEmail.getText().toString();
        String key = textKey.getText().toString();

        String sql = "SELECT ID FROM wp_users WHERE user_email = ?";
        String sql1 = "SELECT meta_value FROM wp_usermeta WHERE user_id = ? AND meta_value = ?";

        try{

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                    ID = resultSet.getInt("ID");
            }

            newpreparedStatement = newconnection.prepareStatement(sql1);
            newpreparedStatement.setString(1, String.valueOf(ID));
            newpreparedStatement.setString(2, key);

            newresultSet = newpreparedStatement.executeQuery();

            if(!newresultSet.next() && !resultSet.next()){

                infoBox("Veuillez entrer une adresse e-mail valide et / ou une cléf correcte", null, "Failed");

            }else{

                infoBox("Connexion réussi !",null,"Success" );
                Main.mainPage(dialogStage);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        //alert.showAndWait();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

}