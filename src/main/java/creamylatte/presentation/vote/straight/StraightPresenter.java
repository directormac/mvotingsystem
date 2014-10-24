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
import creamylatte.business.models.Voter;
import creamylatte.business.services.CandidateService;
import creamylatte.business.services.VoterService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
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
    private FlowPane flowPane;

    ObjectProperty<Party> selectedParty;
    private ObjectProperty<Voter> voter;
    
    @FXML
    private Label voterNameLabel;
    
    @Inject
    CandidateService service;
    @Inject
    VoterService voterService;
    
    @FXML
    private Button voteButton;

    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedPartyListComboBox.setItems(FXCollections.observableArrayList(service.getAllParty()));
        selectedPartyListComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Party> observable, Party oldValue, Party newValue) -> {
            flowPane.getChildren().clear();
            List<Candidate> currentCandidates = newValue.getCandidates();
            currentCandidates.stream().forEach((candidate) -> {
                LinearCandidateView candidateView = new LinearCandidateView();
                LinearCandidatePresenter candidatePresenter = (LinearCandidatePresenter)candidateView.getPresenter();
                candidatePresenter.setCandidate(candidate);
                candidatePresenter.getVoteCandidateButton().setVisible(false);
                candidatePresenter.getSelectCandidateCBox().setVisible(false);
                flowPane.getChildren().add(candidateView.getView());
            });
        });
        
    }    

    @Override
    public void setScreenParent(ViewController screenParent) {
        viewController = screenParent;
    }

    @FXML
    private void voteAction(ActionEvent event) {
        Voter currentVoter = voter.get();
        currentVoter.setCandidates(selectedPartyListComboBox.getSelectionModel()
                                    .selectedItemProperty().get().getCandidates());
        voterService.save(currentVoter);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
    public ObjectProperty<Voter> getVoter() {
        return voter;
    }


    public void setVoter(ObjectProperty<Voter> voter) {
        this.voter = voter;
    }

    public Label getVoterNameLabel() {
        return voterNameLabel;
    }

    public void setVoterNameLabel(Label voterNameLabel) {
        this.voterNameLabel = voterNameLabel;
    }

}
