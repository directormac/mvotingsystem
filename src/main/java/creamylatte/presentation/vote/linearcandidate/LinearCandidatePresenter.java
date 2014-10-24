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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class LinearCandidatePresenter implements Initializable {
    @FXML
    private ImageView candidateImageView;
    @FXML
    private Label candidateGradeLevel,candidatePartyListLabel,candidateNameLabel;
    @FXML
    private TitledPane titledPane;
    @FXML
    AnchorPane currentPane;
    @FXML
    private ComboBox<Candidate> selectCandidateCBox;
    @FXML
    private Button voteCandidateButton;
    
    private ObjectProperty<Candidate> candidate;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        candidate = new SimpleObjectProperty<>();
        candidate.addListener((ObservableValue<? extends Candidate> observable, Candidate oldValue, Candidate newValue) -> {
            titledPane.setText(newValue.getPosition().getName()
                                .concat(" - ").concat(newValue.getVoterId().getLastName()
                                .concat(" , ").concat(newValue.getVoterId().getFirstName())));
            candidateNameLabel.setText(candidate.get().getVoterId().getLastName()
                    + " , " + candidate.get().getVoterId().getFirstName());
            candidateGradeLevel.setText(candidate.get().getVoterId().getGradeLevel());
            candidatePartyListLabel.setText(candidate.get().getParty().getName());
            InputStream inputStream = new ByteArrayInputStream(newValue.getImage().getData());
            Image image = new Image(inputStream);
            candidateImageView.setImage(image);
        });
        selectCandidateCBox.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends Candidate> observable, Candidate oldValue, Candidate newValue) -> {
                    candidate.set(newValue);
        });
        selectCandidateCBox.setCellFactory(new Callback<ListView<Candidate>,ListCell<Candidate>>(){ 
            @Override
            public ListCell<Candidate> call(ListView<Candidate> p) {                 
                final ListCell<Candidate> cell = new ListCell<Candidate>(){ 
                    @Override
                    protected void updateItem(Candidate t, boolean bln) {
                        super.updateItem(t, bln);                         
                        if(t != null){
                            setText(t.getVoterId().getLastName().concat(" , ").concat(t.getVoterId().getFirstName()));
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
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


    public ObjectProperty<Candidate> getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate.set(candidate);
    }

    public TitledPane getTitledPane() {
        return titledPane;
    }



    @FXML
    private void voteCandidateAction(ActionEvent event) {

    }

    public ComboBox<Candidate> getSelectCandidateCBox() {
        return selectCandidateCBox;
    }

    public Button getVoteCandidateButton() {
        return voteCandidateButton;
    }
    
}
