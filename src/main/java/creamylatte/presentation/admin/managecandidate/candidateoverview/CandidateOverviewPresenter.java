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
import creamylatte.business.models.Voter;
import creamylatte.business.services.CandidateService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private TextField candidateFilterField;
    @FXML
    private AnchorPane contentPane;

    @Inject
    CandidateService service;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
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
                            setText(t.getVoterId().getLastName() + " , " + t.getVoterId().getFirstName() + " - " + t.getPosition().getName());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        
    }    
    
    
    
    
}
