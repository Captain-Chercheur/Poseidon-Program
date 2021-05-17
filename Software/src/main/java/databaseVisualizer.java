import java.sql.Connection;
import java.sql.ResultSet;

import com.sun.javafx.scene.control.LabeledText;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.Text;

import javax.swing.*;

/**
 *
 * @author Narayan
 * @Editor SeifAllah
 */

public class databaseVisualizer{

    private static TableView tableview;
    private static TableColumn col = new TableColumn();

    //CONNECTION DATABASE
    public static void buildData(){
        Connection c ;
        //TABLE VIEW AND DATA
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try{
            c = dbconnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from Products";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


    public static void test(Stage stage) throws Exception {
        //TableView
        tableview = new TableView();
        buildData();
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TablePosition pos = (TablePosition) tableview.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                System.out.println(row);
// Item here is the table view type:
                TableColumn col = pos.getTableColumn();
                String data = (String) col.getCellObservableValue(tableview.getItems().get(row)).getValue();
                col.setCellValueFactory(col.getCellValueFactory());
                System.out.println(data);

            }
        });
        stage.setWidth(285);
//        stage.setMaxWidth(285);
//        stage.setMinWidth(285);
        stage.setTitle("Java Fx 2.0 DataBase Connection");
        stage.setResizable(false);
        //Main Scene
        Scene scene = new Scene(tableview);

        stage.setScene(scene);
        stage.show();
    }
}
