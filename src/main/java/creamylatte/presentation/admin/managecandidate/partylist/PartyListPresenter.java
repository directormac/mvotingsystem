/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managecandidate.partylist;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.Party;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class PartyListPresenter implements Initializable {
    @FXML
    private ComboBox<Party> partyListComboBox;
    
    
    ComboBox<Candidate> candidate;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void partyListComboBox(ActionEvent event) {
        
        
    }
    
    
    
    
}
