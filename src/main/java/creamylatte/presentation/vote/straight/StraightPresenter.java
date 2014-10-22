/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.vote.straight;

import creamylatte.business.models.Party;
import creamylatte.business.services.CandidateService;
import creamylatte.presentation.vote.linearcandidate.LinearCandidatePresenter;
import creamylatte.presentation.vote.linearcandidate.LinearCandidateView;
import creamylatte.presentation.vote.viewframework.ControlledScreen;
import creamylatte.presentation.vote.viewframework.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class StraightPresenter implements Initializable , ControlledScreen{
    
    ViewController viewController;
    @FXML
    private ComboBox<Party> selectedPartyListComboBox;
    @FXML
    private VBox candidateVerticalBox;
    
    ObservableList<Party> partylists;
    
    LinearCandidateView candidate;
    LinearCandidatePresenter candidatePresenter;
    
    
    @Inject
    CandidateService service;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        candidate = new LinearCandidateView();
        candidatePresenter = (LinearCandidatePresenter) candidate.getPresenter();
        candidatePresenter.setCandidate(service.findCandidate(15));
//        partylists.addAll(service.getAllParty());
//        selectedPartyListComboBox.setItems(partylists);
        
        candidateVerticalBox.getChildren().add(candidate.getView());
        candidateVerticalBox.getChildren().add(candidate.getView());
        candidateVerticalBox.getChildren().add(candidate.getView());
        candidateVerticalBox.getChildren().add(candidate.getView());
        candidateVerticalBox.getChildren().add(candidate.getView());
        
    }    

    @Override
    public void setScreenParent(ViewController screenParent) {
        viewController = screenParent;
    }
    
    
    
}
