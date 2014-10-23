/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.vote.linearcandidate;

import creamylatte.business.models.Candidate;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class LinearCandidatePresenter implements Initializable {
    @FXML
    private ImageView candidateImageView;
    @FXML
    private Label candidateNameLabel;
    @FXML
    private Label candidateGradeLevel;
    @FXML
    private Label candidatePositionLabel;
    @FXML
    private Label candidatePartyListLabel;
    
    private ObjectProperty<Candidate> candidate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        candidate = new SimpleObjectProperty<>();
        candidate.addListener((ObservableValue<? extends Candidate> observable, Candidate oldValue, Candidate newValue) -> {
            candidateNameLabel.setText(candidate.get().getVoterId().getLastName()
                    + " , " + candidate.get().getVoterId().getFirstName());
            candidatePositionLabel.setText(candidate.get().getPosition().getName());
            candidateGradeLevel.setText(candidate.get().getVoterId().getGradeLevel());
            candidatePartyListLabel.setText(candidate.get().getParty().getName());
            InputStream inputStream = new ByteArrayInputStream(newValue.getImage().getData());
            Image image = new Image(inputStream);
            candidateImageView.setImage(image);
        });
        
    }    


    public ImageView getCandidateImageView() {
        return candidateImageView;
    }

    public Label getCandidateNameLabel() {
        return candidateNameLabel;
    }

    public Label getCandidateGradeLevel() {
        return candidateGradeLevel;
    }

    public Label getCandidatePositionLabel() {
        return candidatePositionLabel;
    }

    public ObjectProperty<Candidate> getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate.set(candidate);
    }
    
}
