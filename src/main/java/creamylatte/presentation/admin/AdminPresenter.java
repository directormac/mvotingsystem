/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin;

import creamylatte.presentation.admin.managecandidate.ManageCandidateView;
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
    private Button votersButton,manageCandidateButton;
    @FXML
    private AnchorPane centerPane;
    @FXML
    private AnchorPane topPane;

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
    private void manageCandidateButtonAction(ActionEvent event) {
        changePane(new ManageCandidateView().getView());        
    }
    

    @FXML
    private void votersButtonAction(ActionEvent event) {
        changePane(new ManageVoterView().getView());        
    }

    
    private void changePane(Parent parent){
        centerPane.getChildren().clear();
        centerPane.getChildren().add(parent);
    }
    
}
