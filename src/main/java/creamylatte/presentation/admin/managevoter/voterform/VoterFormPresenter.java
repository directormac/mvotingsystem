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
import creamylatte.presentation.admin.managevoter.voterchart.VoterChartPresenter;
import creamylatte.presentation.admin.managevoter.voterchart.VoterChartView;

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
    private Button addVoterButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox<String> gradeLevelCBox;
    @FXML
    private AnchorPane contentPane;
    
    
    private ObjectProperty<Voter> currentVoter;
    private VoterChartPresenter voterChartPresenter;
    private VoterChartView voterChartView;
    @Inject
    VoterService service;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gradeLevelCBox.setItems(FXCollections.observableArrayList("Seven","Eight","Nine","Ten"));
        currentVoter = new SimpleObjectProperty<>();
        currentVoter.addListener(new ChangeListener<Voter>() {
            @Override
            public void changed(ObservableValue<? extends Voter> observable, Voter oldValue, Voter newValue) {
                System.out.println(newValue);
                addVoterButton.textProperty().set("Update");
                if(newValue != null){
                    firstNameField.textProperty().set(newValue.getFirstName());
                    lastNameField.textProperty().set(newValue.getLastName());
                    gradeLevelCBox.getSelectionModel().select(currentVoter.get().getGradeLevel());
                }else{
                    
                }
            }
        });
        
        
        textFieldListeners(lastNameField);
        textFieldListeners(firstNameField);
        
       
    }    
    
    public void setVoter(Voter voter){
        currentVoter.set(voter);
    }

    @FXML
    private void addVoterButtonAction(ActionEvent event) {
        Voter voter;
        if(getCurrentVoter().get() == null)
            voter = new Voter();
        else
            voter = getCurrentVoter().get();
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
        AnchorPane currentPane = (AnchorPane) contentPane.getParent();
        currentPane.getChildren().clear();
        currentPane.getChildren().add(voterChartView.getView());
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


    public VoterChartPresenter getVoterChartPresenter() {
        return voterChartPresenter;
    }

    public void setVoterChartPresenter(VoterChartPresenter voterChartPresenter) {
        this.voterChartPresenter = voterChartPresenter;
    }

    /**
     * @return the voterChartView
     */
    public VoterChartView getVoterChartView() {
        return voterChartView;
    }

    /**
     * @param voterChartView the voterChartView to set
     */
    public void setVoterChartView(VoterChartView voterChartView) {
        this.voterChartView = voterChartView;
    }

    /**
     * @return the currentVoter
     */
    public ObjectProperty<Voter> getCurrentVoter() {
        return currentVoter;
    }

    /**
     * @param currentVoter the currentVoter to set
     */
    public void setCurrentVoter(ObjectProperty<Voter> currentVoter) {
        this.currentVoter = currentVoter;
    }

    /**
     * @return the addVoterButton
     */
    public Button getAddVoterButton() {
        return addVoterButton;
    }

    /**
     * @param addVoterButton the addVoterButton to set
     */
    public void setAddVoterButton(Button addVoterButton) {
        this.addVoterButton = addVoterButton;
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        AnchorPane currentPane = (AnchorPane) contentPane.getParent();
        currentPane.getChildren().clear();
        currentPane.getChildren().add(voterChartView.getView());
    }
    
    
}
