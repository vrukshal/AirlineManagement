/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinemanagement;

import DB.DBConnection;
import DB.DisplayDatabase;
import DB.QueryDatabase;
import Model.Passenger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author tanzeem
 */
public class TicketSceneController implements Initializable {

    @FXML
    private TextField pName;
    @FXML
    private TextField pAge;
    @FXML
    private Label dateTimeLable;
    @FXML
    private Label fareLable;
    @FXML
    private TableView<Passenger> passengerTable;
    @FXML
    private TextField passportNum;
    @FXML
    private TextField srcField;
    @FXML
    private TextField destfield;
    @FXML
    private ComboBox<String> pGender;
    @FXML
    private ComboBox<String> flightClass;
    @FXML
    private Button bookFlighttBtn;
    @FXML
    private TableView<?> TicketTable;
    @FXML
    private ComboBox<String> cFlightName;
    @FXML
    private DatePicker datePick;
   
   
DisplayDatabase ticketData = new DisplayDatabase();
ObservableList<String> fList = FXCollections.observableArrayList(); 
    @FXML
    private Label fWarnMsg;
    @FXML
    private Label classFare;
    @FXML
    private Label totalL;
    @FXML
    private TextField sTNum;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ticketData.buildData(TicketTable, "Select * from tickettable;");
        
        
       totalL.setText("Rs."+String.format("%.2f", 0.0));
      classFare.setText("Rs."+String.format("%.2f", 0.0));  
        
        
        ObservableList<String> gList = FXCollections.observableArrayList();
        gList.add("M");
        gList.add("F");
       gList.add("O");
       
       pGender.setItems(gList);
       
       ObservableList<String> classList = FXCollections.observableArrayList();
        classList.add("Economy");
        classList.add("Business");
      
       
       flightClass.setItems(classList);
       
       createPTable();
 
    }    
ObservableList<Passenger> pList = FXCollections.observableArrayList();
 
 String nameP = "";
 String ageP = "";
 String pPortNum = "";
 String genderP = "";
 
 
    @FXML
    private void addPassenger(ActionEvent event) {
        GetPassengerFields();
        
       pList.add(new Passenger(nameP,ageP,pPortNum,genderP));
        passengerTable.setItems(pList);
        
        clearPassFields();
        tAmount+=fare;
        totalL.setText("Rs."+String.format("%.2f", tAmount));
        
    }
String src = "";
 String dest = "";
 String flightName = "";
 String fClass = "";
 LocalDateTime tDate;
 double tAmount = 0;
 String tId ="";


 
    @FXML
    private void bookFlight(ActionEvent event) {
        
          boolean val =  getTicketFields();
       if(!val){
       return;
       }
       
       Connection c;
       try{
           c = DBConnection.connect();
           PreparedStatement ps;
            String query = "INSERT INTO ticketTable (tDate,source,destination,FlightName,Class,Amount)VALUES("+
                "'"+tDate+"',\n" +
                "'"+src+"',\n" +
                "'"+dest+"',\n" +
                "'"+flightName+"',\n" +
                "'"+fClass+"',\n" +                   
                "'"+tAmount+"');";                    
            ps = c.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            tId = rs.getString(1);
//            c.createStatement().execute(query);
            
            for(Passenger i: pList){
          
             query = "INSERT INTO passengerTable (Ticketid,Name,Passport,Age,Gender)VALUES("+
                "'"+tId+"',\n" +
                "'"+i.getName()+"',\n" +
                "'"+i.getPassPort()+"',\n" +
                "'"+i.getAge()+"',\n" +     
                "'"+i.getGender()+"');";                    
         
            c.createStatement().execute(query);
            
            }
            
            
            
            c.close();
       
       } catch (SQLException ex) {
            Logger.getLogger(TicketSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       clearAllFields();
         ticketData.buildData(TicketTable, "Select * from ticketTable;");
    }

    private boolean getTicketFields() {
       
        src = srcField.getText();
 dest = destfield.getText();
 flightName = (String) cFlightName.getValue();
 fClass = (String) flightClass.getValue();
  tDate = LocalDateTime.of( datePick.getValue(),LocalTime.now());
        return true;
    }
        
        
        
    
    


    private void clearPassFields() {
       pName.clear();
        pAge.clear();
       passportNum.clear();
       pGender.setValue("");
    }

    private void GetPassengerFields() {
       nameP = pName.getText();
      ageP = pAge.getText();
      pPortNum = passportNum.getText();
       genderP =  (String) pGender.getValue();    
    }

    private void createPTable() {
       
        TableColumn col_name = new TableColumn("Name");
        col_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger,String>,ObservableValue<String>>(){            
           public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> param) {                                                 
             return new SimpleStringProperty(param.getValue().getName());              
           }            
         });  
        passengerTable.getColumns().addAll(col_name);
        
           TableColumn col_cont = new TableColumn("Age");
        col_cont.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger,String>,ObservableValue<String>>(){            
           public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> param) {                                                 
             return new SimpleStringProperty(param.getValue().getAge());              
           }            
         });  
        passengerTable.getColumns().addAll(col_cont);
       
        TableColumn col_gender = new TableColumn("Gender");
        col_gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger,String>,ObservableValue<String>>(){            
           public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> param) {                                                 
             return new SimpleStringProperty(param.getValue().getGender());              
           }            
         }); 
        
        passengerTable.getColumns().addAll(col_gender);
        
        
           TableColumn col_pass = new TableColumn("Passport");
        col_pass.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger,String>,ObservableValue<String>>(){            
           public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> param) {                                                 
             return new SimpleStringProperty(param.getValue().getPassPort());              
           }            
         }); 
        
        passengerTable.getColumns().addAll(col_pass);
        
        
        
        
        
    }

    private void clearAllFields() {
      srcField.clear();
      destfield.clear();        
    
      tAmount = 0;
      fare =0;
     totalL.setText("Rs."+String.format("%.2f", 0.0));
      classFare.setText("Rs."+String.format("%.2f", 0.0));   
    }

    @FXML
    private void findFlight(ActionEvent event) {
         fList.clear();
         src = srcField.getText();
         dest = destfield.getText();
        
         if(src!=null){
             if(dest!=null){
         
         ResultSet rs = QueryDatabase.query("Select FlightName from FlightTable where Source='"+src+"' AND Destination='"+dest+"' ;");
        if(rs!=null){
            try {
                while(rs.next()){
                    fList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(TicketSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            cFlightName.setItems(fList);
        
        }
        
        
      
        
        }
        
    }
    }
    double fare = 0;
    @FXML
    private void setAmount(ActionEvent event) {
    
     String fName = cFlightName.getValue();
       if(fName==null || fName.isEmpty()){
       fWarnMsg.setText("Pls select flight.");
       cFlightName.requestFocus();
       return;
       }
       
       String cls = flightClass.getValue();
         if(cls==null || cls.isEmpty()){
       fWarnMsg.setText("Pls select Class.");
       flightClass.requestFocus();
       return;
       }
      
          ResultSet rs;
          if(cls.equalsIgnoreCase("Economy")){
      rs = QueryDatabase.query("Select EconomyFare from FlightTable where FlightName='"+fName+"';");
              
          }else{
              
      rs = QueryDatabase.query("Select BusinessFare from FlightTable where FlightName='"+fName+"';");
              
          }
        if(rs!=null){
            try {
                if(rs.next()){
                    fare = Double.parseDouble(rs.getString(1));
                    classFare.setText("Rs."+String.format("%.2f", fare));
                }
            } catch (SQLException ex) {
                Logger.getLogger(TicketSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         
        }
    
    }

    @FXML
    private void searchTicket(ActionEvent event) {
        
        String num = sTNum.getText();
        if(num==null || num.isEmpty()){
         ticketData.buildData(TicketTable, "Select * from ticketTable;");
        }else
             ticketData.buildData(TicketTable, "Select * from ticketTable where Id='"+num+"';");
    }
    
}
