/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managecandidate;

import creamylatte.presentation.admin.managecandidate.candidateoverview.CandidateOverviewView;
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
public class ManageCandidatePresenter implements Initializable {
    @FXML
    private Button viewAllCandidatesButton;
    @FXML
    private Button viewAllPartyList;
    @FXML
    private AnchorPane contentPane;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changePane(new CandidateOverviewView().getView());
    }    

    @FXML
    private void viewAllCandidatesButtonAction(ActionEvent event) {
        changePane(new CandidateOverviewView().getView());
    }

    @FXML
    private void viewAllPartyListAction(ActionEvent event) {
        
    }
    
    private void changePane(Parent parent){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(parent);
    }
    
}
