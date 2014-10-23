/*
 * Computerized Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managepartylist.partyform;

import creamylatte.business.models.Party;
import creamylatte.business.services.CandidateService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
public class PartyFormPresenter implements Initializable {
    @FXML
    private Button addPartyListButton;
    @FXML
    private TextField partyNameField;
    @FXML
    AnchorPane anchorPane;
    
    
    private ObjectProperty<Party> party;
    
    @Inject
    CandidateService service;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        party = new SimpleObjectProperty<>();
        
    }    

    @FXML
    private void addPartyListAction(ActionEvent event) {
        Party currentParty;
        if(party.get() == null){
            currentParty = new Party();
        }else{
            currentParty = party.get();
        }
        currentParty.setName(partyNameField.getText());
        service.save(currentParty);
        AnchorPane parentPane = (AnchorPane) anchorPane.getParent();
        parentPane.setVisible(false);
    }


    public Button getAddPartyListButton() {
        return addPartyListButton;
    }

    public void setAddPartyListButton(Button addPartyListButton) {
        this.addPartyListButton = addPartyListButton;
    }

    public TextField getPartyNameField() {
        return partyNameField;
    }

    public void setPartyNameField(TextField partyNameField) {
        this.partyNameField = partyNameField;
    }

    public ObjectProperty<Party> getParty() {
        return party;
    }

    public void setParty(ObjectProperty<Party> party) {
        this.party = party;
    }

    
}
