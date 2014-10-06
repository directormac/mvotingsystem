/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managecandidate.candidateoverview;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.Party;
import creamylatte.business.services.CandidateService;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class CandidateOverviewPresenter implements Initializable {
    @FXML
    private ListView<Candidate> candidateListView;
    @FXML
    private ComboBox<Party> partyListComboBox;
    @FXML
    private AnchorPane contentPane;

    ObjectProperty<Candidate> selectedCandidate;
    
    
    
    
    @Inject
    CandidateService service;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        candidateListView.setItems(FXCollections.observableArrayList(service.getAllCandidates()));
        candidateListView.setCellFactory(new Callback<ListView<Candidate>,ListCell<Candidate>>(){ 
            @Override
            public ListCell<Candidate> call(ListView<Candidate> p) {                 
                final ListCell<Candidate> cell = new ListCell<Candidate>(){ 
                    @Override
                    protected void updateItem(Candidate t, boolean bln) {
                        super.updateItem(t, bln);                         
                        if(t != null){
                            setText(t.getParty().getName() + " - " +  t.getPosition().getName() + " " + t.getVoterId().getLastName() + " , " + 
                                    t.getVoterId().getFirstName());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        
    }    
    
    @FXML
    private void partyListComboBoxShown(Event event){
        
    }
    
    
}
