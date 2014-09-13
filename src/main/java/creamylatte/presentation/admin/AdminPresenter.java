/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin;

import creamylatte.presentation.admin.managevoter.ManageVoterView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class AdminPresenter implements Initializable {
    @FXML
    private AnchorPane leftPane;
    @FXML
    private Button votersButton;
    @FXML
    private Button candidatesButton;
    @FXML
    private Button partylistsButton;
    @FXML
    private Button resultsButton;
    @FXML
    private AnchorPane centerPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void votersButtonAction(ActionEvent event) {
        changePane(new ManageVoterView().getView());        
    }

    @FXML
    private void candidatesButtonAction(ActionEvent event) {
        
    }

    @FXML
    private void partylistsButtonAction(ActionEvent event) {
        
    }

    @FXML
    private void resultsButtonAction(ActionEvent event) {
        
    }
    
    public void changePane(Parent parent){
        centerPane.getChildren().clear();
        centerPane.getChildren().add(parent);
    }
    
}
