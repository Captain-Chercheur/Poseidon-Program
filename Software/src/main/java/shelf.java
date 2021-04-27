import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class shelf {
    public shelf(Stage primaryStage, String storage, String designation) {
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
                                      String barcodeName = null;
                                      try {
                                          barcodeName = barcode.shelf_barcode(designation.substring(0,2), storage);
                                      } catch (Exception e) {
                                          e.printStackTrace();
                                      }
                                      String barcodeFileName = "shelf_barcodes/" + barcodeName;
                                      System.out.println("Creating barcode...");
                                      try {
                                          imageProcess.imageProcessShelf(designation, storage, barcodeFileName);
                                      } catch (IOException e) {
                                          e.printStackTrace();
                                      }
                                      System.out.println("Processing image...");
                                      try {
                                          BarcodePrinter.BarcodePrinter(barcodeFileName);
                                      } catch (Exception e) {
                                          e.printStackTrace();
                                      }
                                      System.out.println("Printing image...");
                                      HTMLrequests.HTMLrequests("new_shelf/" + barcodeName + "/" + storage + "/" + designation + "/" + 1);
                                      dialog.close();
                                  }
                              });
        Non.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dialog.close();
            }
        });
        Scene dialogScene = new Scene(dialogVbox, 500, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public static class quantity {
        public static void adding_quantity(Stage primaryStage, String designation, String stockPlacement) {


            String request = HTMLrequests.HTMLrequests("get_shelf_quantity/" + stockPlacement);
            request = request.replaceAll("[\\[\\](){}]", "");
            int quantity = Integer.parseInt(request);
            quantity += 1;
            HTMLrequests.HTMLrequests("change_shelf_quantity/" + stockPlacement + "/" + quantity);
        }
        public static void removing_quantity(Stage primaryStage, String stockPlacement){
            String request = HTMLrequests.HTMLrequests("get_shelf_quantity/" + stockPlacement);
            request = request.replaceAll("[\\[\\](){}]", "");
            int quantity = Integer.parseInt(request);
            quantity -= 1;
            HTMLrequests.HTMLrequests("change_shelf_quantity/" + stockPlacement + "/" + quantity);
        }
        public static void waiting_shelving(Stage primaryStage, String stockPlacement, int id, int waiting){

            String request = HTMLrequests.HTMLrequests("product_waiting/" + id + "/1");
            request = request.replaceAll("[\\[\\](){}]", "");

            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Votre pièce est en attente de rayonnage"));
            dialogVbox.getChildren().add(new Text("Veuillez scanner l'étiquette sur l'étagère puis celle du produit afin de l'ajouter"));
            Button Oui = new Button("Ouvrir le scanner");
            Button Non = new Button("Cancel");
            dialogVbox.getChildren().addAll(Oui, Non);
            Oui.setOnMouseClicked(new EventHandler<Event>() {
                @Override
                public void handle(Event arg0) {
                    try {
                        new quantity_management_scanner();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
