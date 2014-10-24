/*
 * Computerized Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.blindresult;

import creamylatte.business.models.Position;
import creamylatte.business.services.CandidateService;
import creamylatte.presentation.blindresult.randomcandidate.RandomCandidatePresenter;
import creamylatte.presentation.blindresult.randomcandidate.RandomCandidateView;
import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class BlindResultPresenter implements Initializable {
    @FXML
    private FlowPane flowPane;
    
    @Inject
    CandidateService service;
    
    ObservableList<Position> positions;
    RandomCandidateView randomCandidateView;
    RandomCandidatePresenter randomCandidatePresenter;
    
    
    HashMap<Integer, RandomCandidateView> randoms = new HashMap<>();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        positions = FXCollections.observableArrayList(service.getAllPositions());
        int counter = 0;
        for(Position position: positions){
            randomCandidateView = new RandomCandidateView();
            randomCandidatePresenter = (RandomCandidatePresenter) randomCandidateView.getPresenter();
            randomCandidatePresenter.setPosition(position);
            randoms.put(counter++, randomCandidateView);
        }
        randoms.values().stream().forEach((candidate) -> {
            flowPane.getChildren().add(candidate.getView());
        });

    }    
    
    
    
}
