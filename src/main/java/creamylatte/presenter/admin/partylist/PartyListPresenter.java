/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presenter.admin.partylist;

import creamylatte.business.models.Party;
import creamylatte.presenter.admin.partyform.PartyFormView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class PartyListPresenter implements Initializable {
    @FXML
    private ComboBox<Party> partyListCBox;
    @FXML
    private Button newPartyButton;
    @FXML
    private AnchorPane currentPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void partyListCBoxShown(Event event) {
        
    }

    @FXML
    private void createPartyList(ActionEvent event) {
        currentPane.getChildren().clear();
        currentPane.getChildren().add(new PartyFormView().getView());
    }
    
}
