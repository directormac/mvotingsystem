/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managevoter;

import creamylatte.presentation.admin.managevoter.searchvoter.SearchVoterView;
import creamylatte.presentation.admin.managevoter.voterform.VoterFormView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ManageVoterPresenter implements Initializable {
    @FXML
    private AnchorPane centerPane;
    @FXML
    private Button searchButton;
    @FXML
    private Button viewAllButton;
    @FXML
    private Button addNewButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchButtonAction(ActionEvent event) {
        changePane(new SearchVoterView().getView());
    }

    @FXML
    private void viewAllButton(ActionEvent event) {
        
        
    }

    @FXML
    private void addNewButtonAction(ActionEvent event) {
        changePane(new VoterFormView().getView());
    }
    
    public void changePane(Parent parent){
        centerPane.getChildren().clear();
        centerPane.getChildren().add(parent);
    }
    
    
    
}
