/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinemanagement;

import DB.DBConnection;
import DB.DeleteDatabase;
import DB.DisplayDatabase;
import DB.QueryDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author tanzeem
 */
public class FlightSceneController implements Initializable {

    @FXML
    private AnchorPane flightScene;
    @FXML
    private Button addFlgtBtn;
    @FXML
    private TextField flightName;
    @FXML
    private TextField ecnFare;
    @FXML
    private TextField BussFare;
    @FXML
    private TextField srcField;
    @FXML
    private TextField destField;
    @FXML
    private TextField depTime;
    @FXML
    private TextField arrTime;
    @FXML
    private ComboBox<String> plnNameComboBtn;
    @FXML
    private TextField srchFlgtBtn;
    @FXML
    private TableView<?> flightTable;
ObservableList<String> pList = FXCollections.observableArrayList();
DisplayDatabase flightData =new  DisplayDatabase();
    @FXML
    private Label warnMsg;
    @FXML
    private MenuItem deleteFlight;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         flightData.buildData(flightTable, "Select * from FlightTable;");
        
         ResultSet rs = QueryDatabase.query("Select PlaneName from PlaneTable;");
        if(rs!=null){
            try {
                while(rs.next()){
                    pList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FlightSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
      
        plnNameComboBtn.setItems(pList);
        // TODO
    }    
String fName ="";
String pName ="";
String source ="";
String Dest ="";
String dTime ="";
String aTime ="";
double economyFare =0;
double BusinessFare =0;
    @FXML
    private void btnAddFlgt(ActionEvent event) {
         Connection c;
         try {
              
              if(!update){
        getFligthFields();
             ResultSet rs = QueryDatabase.query("Select PName from FlightTable where PName='"+pName+"';");
             if(rs!=null){
                 if(rs.next()){
                     warnMsg.setText("Plane Already Reserved.");
                     return;
                 }
             }
            c = DBConnection.connect();
            String query = "INSERT INTO Airline.FlightTable (FlightName,PName,EconomyFare,BusinessFare,Source,Destination,DepartureTime,ArrivalTime)VALUES("+
 "'"+fName+"',\n" +
 "'"+pName+"',\n" +
 "'"+economyFare+"',\n" +
 "'"+BusinessFare+"',\n" +
 "'"+source+"',\n" +
 "'"+Dest+"',\n" +
 "'"+dTime+"',\n" +              
 "'"+aTime+"');";            
                   
                   
            
        
            c.createStatement().execute(query);
            }else{
              c = DBConnection.connect();
                getFligthFields();
           String query = "Update FlightTable set FlightName='"+fName+"',PName='"+pName+"',EconomyFare='"+economyFare+"',"
                   + "BusinessFare='"+BusinessFare+"',Source='"+source+"',Destination='"+Dest+"',DepartureTime='"+dTime+"',ArrivalTime='"+aTime+"' Where Id='"+id+"';";
                  System.out.println(query);
           c.createStatement().executeUpdate(query);
          } 
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FlightSceneController .class.getName()).log(Level.SEVERE, null, ex);
        }clearFields();
        
        flightData.buildData(flightTable, "Select * from FlightTable;");
    }

    @FXML
    private void btnPlnNameCombo(ActionEvent event) {
    }

    @FXML
    private void btnSrchFlgt(ActionEvent event) {
    String fnum= srchFlgtBtn.getText();
    if(fnum==null||fnum.isEmpty()){
        flightData.buildData(flightTable, "Select * from FlightTable;");
    }else
        flightData.buildData(flightTable, "Select * from FlightTable where Id='"+fnum+"';");
        
    }
    
        
    
    
    

    private void getFligthFields() {
         fName = flightName.getText();
 pName =plnNameComboBtn.getValue();
 source =srcField.getText();
 Dest =destField.getText();
 dTime =depTime.getText();
 aTime =arrTime.getText();
 economyFare =Double.parseDouble(ecnFare.getText());
 BusinessFare =Double.parseDouble(BussFare.getText()); 
 
        
    }

    private void clearFields() {
       flightName.clear();
        srcField.clear();
       destField .clear();
        depTime.clear();
         arrTime .clear();     
          ecnFare.clear();     
          BussFare.clear(); 
           update = false;
      addFlgtBtn.setText("Add Flight");
    
    }

    @FXML
    private void DeleteFlight(ActionEvent event) {
        int index =  flightTable.getSelectionModel().getSelectedIndex(); 
          ObservableList<ObservableList> data =   flightData.getData();
          ObservableList<String> row = data.get(index);
          DeleteDatabase.deleteRecord(Integer.parseInt(row.get(0)), "flightTable");
          flightData.buildData(flightTable, "Select * from flightTable;");
    }
boolean update = false;
    int id;
    @FXML
    private void UpdateFlight(ActionEvent event) {
         int index = flightTable.getSelectionModel().getFocusedIndex();
      ObservableList<ObservableList> data = flightData.getData();
      ObservableList<String> itemData = data.get(index);
      
      
      id = Integer.parseInt(itemData.get(0));
      flightName.setText(itemData.get(1));
      plnNameComboBtn.setValue(itemData.get(2));
        srcField.setText(itemData.get(5));
        destField.setText(itemData.get(6));
        depTime.setText(itemData.get(7));
        arrTime.setText(itemData.get(8));
        ecnFare.setText(itemData.get(3));
        BussFare.setText(itemData.get(4));
       
        update=true;
        addFlgtBtn.setText("Update Flight");
    }
    
    
}
