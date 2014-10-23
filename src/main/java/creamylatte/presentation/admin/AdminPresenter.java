/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin;

import creamylatte.presentation.admin.managecandidate.ManageCandidateView;
import creamylatte.presentation.admin.managepartylist.ManagePartyListView;
import creamylatte.presentation.admin.managepartylisttest.ManagePartyListTestView;
import creamylatte.presentation.admin.managevotertest.ManageVoterTestView;
import creamylatte.presentation.login.LoginView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class AdminPresenter implements Initializable {
    @FXML
    private Button votersButton,manageCandidateButton,partyListButton;
    @FXML
    private AnchorPane centerPane;
    @FXML
    private AnchorPane topPane;
    @FXML
    private Button logoutButton;
    @FXML
    private Button closeButton;
    @FXML
    private BorderPane mainPane;

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
    private void manageCandidateButtonAction(ActionEvent event) {
        changePane(new ManageCandidateView().getView());        
    }
    
    @FXML
    private void partyListAction(ActionEvent event) {
        changePane(new ManagePartyListTestView().getView());        
    }

    @FXML
    private void votersButtonAction(ActionEvent event) {
        changePane(new ManageVoterTestView().getView());        
    }

    
    private void changePane(Parent parent){
        centerPane.getChildren().clear();
        centerPane.getChildren().add(parent);
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        AnchorPane anchorPane = (AnchorPane)mainPane.getParent();
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(new LoginView().getView());
    }

    @FXML
    private void closeAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();        
    }
    
}
