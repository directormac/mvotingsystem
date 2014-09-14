/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managevoter.voterform;

import creamylatte.business.models.UserAccount;
import creamylatte.business.models.Voter;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.admin.managevoter.searchvoter.SearchVoterPresenter;
import creamylatte.presentation.admin.managevoter.searchvoter.SearchVoterView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class VoterFormPresenter implements Initializable {
    @FXML
    private Button saveButton,editButton,removeButton;
    @FXML
    private ComboBox<String> gradeLevelCBox;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;

    private ObjectProperty<Voter> selectedVoter;
    
    SearchVoterPresenter searchVoterPresenter;
    
    @Inject
    VoterService vs;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setSelectedVoter(new SimpleObjectProperty<>(new Voter()));
        this.selectedVoter.addListener(this.selectedVoterListener());
        gradeLevelCBox.setItems(FXCollections.observableArrayList("Seven","Eigth","Nine","Ten"));
        this.firstNameField.textProperty().addListener(textFieldListeners(this.firstNameField));
        
    }    

    @FXML
    private void saveButtonAction(ActionEvent event) {
        Voter voter = this.selectedVoter.get();
        voter.setFirstName(firstNameField.textProperty().get().toLowerCase());
        voter.setLastName(lastNameField.textProperty().get().toLowerCase());
        voter.setGradeLevel(gradeLevelCBox.getSelectionModel().getSelectedItem());
        UserAccount userAccount;
        if(voter.getAccount() == null){
            userAccount = new UserAccount();
            userAccount.setUsername(voter.getLastName().concat(",").concat(voter.getFirstName()));
            userAccount.setPassword("" + (Math.random() * 1001));
            voter.setAccount(userAccount);
        }
        vs.save(voter);
        SearchVoterView searchVoterView = new SearchVoterView();
        searchVoterPresenter = (SearchVoterPresenter) searchVoterView.getPresenter();
        searchVoterPresenter.getSelecterVoter().bindBidirectional(selectedVoter);
        AnchorPane parent = 
    }
    
    
    
    @FXML
    private void editButtonAction(ActionEvent event) {
    }
    @FXML
    private void removeButtonAction(ActionEvent event) {
    }
    
    private ChangeListener<Voter> selectedVoterListener(){
       ChangeListener<Voter> selectedCandidateListener = new ChangeListener<Voter>() {
            @Override
            public void changed(ObservableValue<? extends Voter> observable, Voter oldValue, Voter newValue) {
                if (newValue != null) {                    
                    firstNameField.setText(firstCharToUpperCase(newValue.getFirstName()));
                    lastNameField.setText(firstCharToUpperCase(newValue.getLastName()));
                    gradeLevelCBox.getSelectionModel().select(newValue.getGradeLevel()); 
                } else {
                    
                }
            }
        };
       return selectedCandidateListener;
    }
    
    
    
    private ChangeListener<String> textFieldListeners(TextField tf){
       final Pattern wholeNumberPattern = Pattern.compile("[A-Za-zñÑ]*");
        return new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue,
                                final String newValue) {
                if (!wholeNumberPattern.matcher(newValue).matches())
                    tf.setText(oldValue);
           }
        };
    }
    
    private String firstCharToUpperCase(String string){
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
    
    public Button getSaveButton() {
        return saveButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }


    public ObjectProperty<Voter> getSelectedVoter() {
        return selectedVoter;
    }

    public void setSelectedVoter(ObjectProperty<Voter> selectedVoter) {
        this.selectedVoter = selectedVoter;
    }
    
    
    
}
