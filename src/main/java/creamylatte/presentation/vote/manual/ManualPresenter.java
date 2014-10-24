/*
 * Computerized Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.vote.manual;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.Position;
import creamylatte.business.models.Voter;
import creamylatte.business.services.CandidateService;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.vote.linearcandidate.LinearCandidatePresenter;
import creamylatte.presentation.vote.linearcandidate.LinearCandidateView;
import creamylatte.presentation.vote.viewframework.ControlledScreen;
import creamylatte.presentation.vote.viewframework.ViewController;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ManualPresenter implements Initializable , ControlledScreen{
    @FXML
    private Label voterNameLabel;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Button voteButton;
    
    private ObjectProperty<Voter> voter;
    @Inject
    CandidateService service;
    @Inject
    VoterService voterService;
    
    HashMap<Integer, LinearCandidateView> candidates = new HashMap<>();
    List<Position> positions;
    LinearCandidateView linearCandidateView;
    LinearCandidatePresenter linearCandidatePresenter;
    
    ViewController viewController;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        positions = service.getAllPositions();
        int counter = 0;        
        for(Position position : positions){            
            if(position.getName().equalsIgnoreCase("Bus. Manager") || position.getName().equalsIgnoreCase("Sgt. at Arms")){
                linearCandidateView = new LinearCandidateView();
                linearCandidatePresenter = (LinearCandidatePresenter)linearCandidateView.getPresenter();
                linearCandidatePresenter.getTitledPane().setText(position.getName());
                linearCandidatePresenter.getTitledPane().setExpanded(false);
                linearCandidatePresenter.getSelectCandidateCBox().setItems(FXCollections.observableArrayList(position.getCandidates()));                
                candidates.put(counter++, linearCandidateView);
                linearCandidateView = new LinearCandidateView();
                linearCandidatePresenter = (LinearCandidatePresenter)linearCandidateView.getPresenter();
                linearCandidatePresenter.getTitledPane().setText(position.getName());
                linearCandidatePresenter.getTitledPane().setExpanded(false);
                linearCandidatePresenter.getSelectCandidateCBox().setItems(FXCollections.observableArrayList(position.getCandidates()));
                candidates.put(counter++, linearCandidateView);                
            }else{
                linearCandidateView = new LinearCandidateView();
                linearCandidatePresenter = (LinearCandidatePresenter)linearCandidateView.getPresenter();
                linearCandidatePresenter.getTitledPane().setText(position.getName());
                linearCandidatePresenter.getTitledPane().setExpanded(false);
                linearCandidatePresenter.getSelectCandidateCBox().setItems(FXCollections.observableArrayList(position.getCandidates()));
                candidates.put(counter++, linearCandidateView);
            }            
        }
        candidates.values().stream().forEach((candidate) -> {
            flowPane.getChildren().add(candidate.getView());
        });
    }    

    @FXML
    private void voteButtonAction(ActionEvent event) {
        List<Candidate> chosenCandidates = new ArrayList<>();
        candidates.values().stream().map((lcv) -> (LinearCandidatePresenter) lcv.getPresenter()).filter((lcp) -> (lcp.getCandidate().get() != null)).forEach((lcp) -> {
            chosenCandidates.add(lcp.getCandidate().get());
        });
        this.voter.get().setCandidates(chosenCandidates);
        voterService.save(this.voter.get());
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public ObjectProperty<Voter> getVoter() {
        return voter;
    }

    public void setVoter(ObjectProperty<Voter> voter) {
        this.voter = voter;
    }

    /**
     * @return the voterNameLabel
     */
    public Label getVoterNameLabel() {
        return voterNameLabel;
    }

    @Override
    public void setScreenParent(ViewController screenParent) {
        viewController = screenParent;
    }
    
    
    
}
