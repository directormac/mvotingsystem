/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managecandidate.partylistform;

import creamylatte.business.models.Party;
import creamylatte.business.services.CandidateService;
import creamylatte.presentation.admin.managecandidate.candidateoverview.CandidateOverviewView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class PartyListFormPresenter implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button savePartyButton;
    @FXML
    private TextField partyField;
    
    @Inject
    CandidateService service;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        savePartyButton.disableProperty().bind(partyField.textProperty().isEmpty());
    }    

    @FXML
    private void savePartyButtonAction(ActionEvent event) {        
        Party party = new Party();
        party.setName(partyField.textProperty().get());
        service.save(party);
        AnchorPane parent = (AnchorPane)mainPane.getParent();
        parent.getChildren().clear();
        parent.getChildren().add(new CandidateOverviewView().getView());
    }
    
}
