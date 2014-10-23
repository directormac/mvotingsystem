/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.vote.straight;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.Party;
import creamylatte.business.services.CandidateService;
import creamylatte.presentation.vote.linearcandidate.LinearCandidatePresenter;
import creamylatte.presentation.vote.linearcandidate.LinearCandidateView;
import creamylatte.presentation.vote.viewframework.ControlledScreen;
import creamylatte.presentation.vote.viewframework.ViewController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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

    ObjectProperty<Party> selectedParty;

    
    
    @Inject
    CandidateService service;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedPartyListComboBox.setItems(FXCollections.observableArrayList(service.getAllParty()));
        selectedPartyListComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Party> observable, Party oldValue, Party newValue) -> {
            candidateVerticalBox.getChildren().clear();
            List<Candidate> currentCandidates = newValue.getCandidates();
            currentCandidates.stream().forEach((candidate) -> {
                LinearCandidateView candidateView = new LinearCandidateView();
                LinearCandidatePresenter candidatePresenter = (LinearCandidatePresenter)candidateView.getPresenter();
                candidatePresenter.setCandidate(candidate);
                candidateVerticalBox.getChildren().add(candidateView.getView());
            });
        });
        
        
    }    

    @Override
    public void setScreenParent(ViewController screenParent) {
        viewController = screenParent;
    }
    
    
    
}
