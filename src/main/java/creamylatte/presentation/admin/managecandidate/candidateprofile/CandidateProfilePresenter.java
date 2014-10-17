/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managecandidate.candidateprofile;

import creamylatte.business.models.Candidate;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class CandidateProfilePresenter implements Initializable {
    @FXML
    private ImageView candidateImageView;
    @FXML
    private Label candidateNameLabel;
    @FXML
    private Label positionPartyLabel;
    @FXML
    private Button editCandidateButton;
    @FXML
    private Label totalVotesLabel;
    
    private ObjectProperty<Candidate> selectedCandidate;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCandidate = new SimpleObjectProperty<>();
        getSelectedCandidate().addListener(new ChangeListener<Candidate>() {
            @Override
            public void changed(ObservableValue<? extends Candidate> observable, Candidate oldValue, Candidate newValue) {
//                InputStream inputStream = new ByteArrayInputStream(newValue.getImage().getData());
//                Image image = new Image(inputStream);
//                candidateImageView.setImage(image);
                candidateNameLabel.setText(firstCharToUpperCase(newValue.getVoterId().getLastName()).concat(" , ")
                                            .concat(firstCharToUpperCase(newValue.getVoterId().getFirstName())));
                positionPartyLabel.setText(newValue.getPosition().getName().concat(" , ")
                                            .concat(newValue.getParty().getName()));
                totalVotesLabel.setText(newValue.getVoters().size() + " votes ");
                
            }
        });
    }    
    
    private String firstCharToUpperCase(String string){
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
    
    
    public void setCandidate(Candidate candidate){
       selectedCandidate.set(candidate);
    }

    @FXML
    private void editCandidateButtonAction(ActionEvent event) {
        
    }

    public ObjectProperty<Candidate> getSelectedCandidate() {
        return selectedCandidate;
    }

    public void setSelectedCandidate(ObjectProperty<Candidate> selectedCandidate) {
        this.selectedCandidate = selectedCandidate;
    }
    
}
