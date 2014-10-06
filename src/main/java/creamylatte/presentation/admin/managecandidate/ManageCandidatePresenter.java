/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managecandidate;

import creamylatte.presentation.admin.managecandidate.candidateform.CandidateFormView;
import creamylatte.presentation.admin.managecandidate.candidateoverview.CandidateOverviewView;
import creamylatte.presentation.admin.managecandidate.partylistform.PartyListFormView;
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
    private Button addNewCandidate;
    @FXML
    private Button viewAllPartyList;
    @FXML
    private Button addNewPartyList;
    @FXML
    private AnchorPane contentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewAllCandidatesButtonAction(ActionEvent event) {
        changePane(new CandidateOverviewView().getView());
    }

    @FXML
    private void addNewCandidateAction(ActionEvent event) {
        changePane(new CandidateFormView().getView());
    }

    @FXML
    private void viewAllPartyListAction(ActionEvent event) {
    }

    @FXML
    private void addNewPartyListAction(ActionEvent event) {
        changePane(new PartyListFormView().getView());
    }
    
    
    private void changePane(Parent parent){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(parent);
    }
}
