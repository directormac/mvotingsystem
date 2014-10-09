/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managevotertest.voterform;

import creamylatte.business.models.UserAccount;
import creamylatte.business.models.Voter;
import creamylatte.business.services.VoterService;
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
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class VoterFormPresenter implements Initializable {
    @FXML
    private Button addVoterButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox<String> gradeLevelCBox;
    
    ObjectProperty<Voter> currentVoter;
    
    
    @Inject
    VoterService service;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gradeLevelCBox.setItems(FXCollections.observableArrayList("Seven","Eight","Nine","Ten"));
        currentVoter = new SimpleObjectProperty<>(new Voter());
        
        
    }    

    @FXML
    private void addVoterButtonAction(ActionEvent event) {
        Voter voter = currentVoter.get();
        voter.setFirstName(firstNameField.textProperty().get().toLowerCase());
        voter.setLastName(lastNameField.textProperty().get().toLowerCase());
        voter.setGradeLevel(gradeLevelCBox.getSelectionModel().getSelectedItem());
        UserAccount ua;
        if(voter.getAccount() == null){
            ua = new UserAccount();
            ua.setUsername(voter.getFirstName()+","+voter.getLastName());
            ua.setPassword("" + (int)(Math.random() * 1001));
            System.out.println(ua.getPassword());
            voter.setAccount(ua);
        }
        service.save(voter);
    }
    
    private String firstCharToUpperCase(String string){
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
    
     private ChangeListener<String> textFieldListeners(TextField tf){
        final Pattern wholeNumberPattern = Pattern.compile("[A-Za-zñÑ_ ]*");
        return (final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) -> {
            if (!wholeNumberPattern.matcher(newValue).matches())
                tf.setText(oldValue);
        };
    }
    
    
}
