/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinemanagement;

import DB.DBConnection;
import DB.DeleteDatabase;
import DB.DisplayDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author tanzeem
 */
public class PpSceneController implements Initializable {

    @FXML
    private AnchorPane PpScene;
    @FXML
    private Button addPlaneBtn;
    @FXML
    private Button addPilotBtn;
    @FXML
    private TextField planeName;
    @FXML
    private TextField planeCapacity;
    @FXML
    private TextField pilotName;
    @FXML
    private TextArea pilotAdrs;
    @FXML
    private TableView<String> planeTable;
    @FXML
    private TableView<String> pilotTable;
DisplayDatabase planeData =new  DisplayDatabase();
DisplayDatabase pilotData =new  DisplayDatabase();

    @FXML
    private TextField pilotContact;
    @FXML
    private MenuItem dPlaneBtn;
    @FXML
    private MenuItem dPilotBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planeData.buildData(planeTable, "Select * from planetable;");
        pilotData.buildData(pilotTable, "Select * from pilottable;");
    }    
String namePlane ="";
String namePilot ="";
String capacity ="";
String contactPilot ="";
String addPilot ="";

    @FXML
    private void btnAddPlane(ActionEvent event) {
        try {
             getPlaneFields();
         Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO Airline.PlaneTable (PlaneName,Capacity)VALUES("+
 "'"+namePlane+"',\n" +
"'"+capacity+"');";            
                   
                   
            
        
            c.createStatement().execute(query);
            
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PpSceneController .class.getName()).log(Level.SEVERE, null, ex);
        }clearFields();
            
   
     planeData.buildData(planeTable, "Select * from planetable;");

    }

    @FXML
    private void btnAddPilot(ActionEvent event) {
        try {
             getPilotFields();
         Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO Airline.PilotTable (PilotName,PilotContact,PilotAddress)VALUES("+
 "'"+namePilot+"',\n" +
 "'"+contactPilot+"',\n" +
"'"+addPilot+"');";            
                   
                   
            
        
            c.createStatement().execute(query);
            
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PpSceneController .class.getName()).log(Level.SEVERE, null, ex);
        }clearPlaneFields();
        
        pilotData.buildData(pilotTable, "Select * from pilottable;");
    }

    private void getPlaneFields() {
        namePlane = planeName.getText();
        capacity = planeCapacity.getText();
        
    }

    private void clearFields() {
        planeName.clear();
       
        planeCapacity.clear();
    }

    private void clearPlaneFields() {
        pilotName.clear();
        pilotContact.clear();
        pilotAdrs.clear();
        
    }

    private void getPilotFields() {
       namePilot = pilotName.getText();
       contactPilot = pilotContact.getText();
       addPilot = pilotAdrs.getText();
    }

    @FXML
    private void DeletePlane(ActionEvent event) {
        int index =  planeTable.getSelectionModel().getSelectedIndex(); 
          ObservableList<ObservableList> data =   planeData.getData();
          ObservableList<String> row = data.get(index);
          DeleteDatabase.deleteRecord(Integer.parseInt(row.get(0)), "planeTable");
          planeData.buildData(planeTable, "Select * from planeTable;");
        
    }

    @FXML
    private void DeletePilot(ActionEvent event) {
        int index =  pilotTable.getSelectionModel().getSelectedIndex(); 
          ObservableList<ObservableList> data =   pilotData.getData();
          ObservableList<String> row = data.get(index);
          DeleteDatabase.deleteRecord(Integer.parseInt(row.get(0)), "PilotTable");
          pilotData.buildData(pilotTable, "Select * from pilottable;");
          
    }
    
}
