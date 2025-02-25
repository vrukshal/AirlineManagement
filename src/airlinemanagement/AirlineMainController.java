/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinemanagement;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author tanzeem
 */
public class AirlineMainController implements Initializable {
    
    @FXML
    private BorderPane rootLayout;
    @FXML
    private ToggleButton setPpSceneBtn;
    @FXML
    private ToggleButton setTicketScene;
    @FXML
    private ToggleButton setFlightScene;
    @FXML
    private ToggleGroup g1;
    @FXML
    private JFXButton button1;
    @FXML
    private AnchorPane navPane;
    
  public  void changeScene(String scenePath){
        
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource(scenePath));
        AnchorPane pane = new AnchorPane();
    try{
            pane = (AnchorPane) loader.load();
            rootLayout.setCenter(pane);
        }
        catch(Exception e){
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeScene("TicketScene.fxml");
        // TODO
    }    

    @FXML
    private void setTicketScene(ActionEvent event) {
        changeScene("TicketScene.fxml");
    }

    @FXML
    private void setFlightScene(ActionEvent event) {
        changeScene("FlightScene.fxml");
    }

    @FXML
    private void setPpScene(ActionEvent event) {
        changeScene("PpScene.fxml");
    }

   int x = 0;
    @FXML
    private void nav(ActionEvent event) {
        
        x++;
        if(x==1){
            TranslateTransition transition = new TranslateTransition();
         transition.setNode(navPane);
         transition.setDuration(Duration.seconds(0.30));
         transition.setToX(160);
         transition.play();
          navPane.setTranslateX(160);
         
        }else{
        TranslateTransition transition = new TranslateTransition();
         transition.setNode(navPane);
         transition.setDuration(Duration.seconds(0.30));
         transition.setToX(-160);
         transition.play();
        x=0;
        }
        
        
         
    }
    
}
