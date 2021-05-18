import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.plaf.TableHeaderUI;

public class databaseVisualizer{

    private static TableView tableview;
    public static ResultSet rs;

    public static ObservableList<ObservableList> data = FXCollections.observableArrayList();


    //CONNECTION DATABASE
    public static void buildData(){
        Connection c ;
        //TABLE VIEW AND DATA
        tableview.getItems().clear();
        try{
            c = dbconnect.connect();
            //SQL FOR SELECTING ALL OF PRODUCTS
            String SQL = "SELECT * from Products";
            //ResultSet
            rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));

                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());

                    }
                });

                tableview.getColumns().addAll(col);
                tableview.setEditable(true);
                //System.out.println("Column ["+i+"] ");
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
                //System.out.println("Row [1] added "+row );
                data.add(row);


            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    static int column;
    static int row;
    public static GridPane test(GridPane databaseGridpane) throws Exception {

        //TableView
        tableview = new TableView();
        buildData();
        /*tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TablePosition pos = (TablePosition) tableview.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                System.out.println(row);
// Item here is the table view type:
                TableColumn col = pos.getTableColumn();
                String data = (String) col.getCellObservableValue(tableview.getItems().get(row)).getValue();
                System.out.println(pos.getColumn());
                System.out.println(data);

            }
        });*/

        TextField textField = new TextField();
        tableview.setEditable(true);
        // allows the individual cells to be selected
        tableview.getSelectionModel().cellSelectionEnabledProperty().set(true);
        // when character or numbers pressed it will start edit in editable
        // fields
        tableview.setOnKeyPressed(event -> {
        if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
            TablePosition pos = (TablePosition) tableview.getSelectionModel().getSelectedCells().get(0);
            textField.requestFocus();
            column  = pos.getColumn();
            row = pos.getRow();
        }
        });

        Button send = new Button("Modifier la valeur");
        send.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        String ColumnName = null;
                        TablePosition pos = (TablePosition) tableview.getSelectionModel().getSelectedCells().get(0);
                        String textValue = textField.getText();

                        //System.out.println(pos.getColumn());
                        try {
                            ColumnName = rs.getMetaData().getColumnName(pos.getColumn()+1);
                            System.out.println(textValue);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        int row = pos.getRow() + 1;


                        int index = pos.getRow();
                        String selected = tableview.getItems().get(index).toString();
                        selected = selected.substring(1, selected.indexOf(","));
                        try {
                            modifyData(textValue, ColumnName, selected);
                        } catch (SQLException | InstantiationException | IllegalAccessException throwables) {
                            throwables.printStackTrace();
                        }


                    }
                });




        databaseGridpane.addRow(1,tableview);
        databaseGridpane.addRow(3, textField);
        databaseGridpane.add(send, 0, 5);
        return databaseGridpane;
    }
    public static void modifyData(String value, String column, String id) throws SQLException, InstantiationException, IllegalAccessException {
        Connection c;
        c = dbconnect.connect();
        //SQL FOR SELECTING ALL OF PRODUCTS
        String SQL = "UPDATE Products SET " + column + " = " + "'"+value +"'"+ " WHERE id = " + id;
        //ResultSet
        c.createStatement().executeUpdate(SQL);
        tableview.refresh();
        tableview.getItems().clear();

        buildData();

    }
}
