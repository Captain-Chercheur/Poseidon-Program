import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class shelf {
    public shelf(Stage primaryStage, String storage) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Aucune pièce n'a été encore enregistrée à cet emplacement"));
        dialogVbox.getChildren().add(new Text("Souhaitez-vous créer un emplacement pour ce type de pièce ? : " + storage));
        Button Oui = new Button("Oui");
        Button Non = new Button("Non");
        dialogVbox.getChildren().addAll(Oui, Non);
        Oui.setOnMouseClicked(new EventHandler<Event>() {
                                  @Override
                                  public void handle(Event arg0) {
                                      HTMLrequests.HTMLrequests("");
                                  }
                              });
        Scene dialogScene = new Scene(dialogVbox, 500, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

}
