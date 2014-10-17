/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.welcome;

import creamylatte.business.models.UserAccount;
import creamylatte.presentation.login.LoginView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class WelcomePresenter implements Initializable {
    
    
    @FXML
    private AnchorPane currentPane;
    private ObjectProperty<UserAccount> user;
    @FXML
    private Hyperlink voteHyperlink;
    @FXML
    private Hyperlink monitorHyperlink;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void loginButtonAction(ActionEvent event) {
        AnchorPane p = (AnchorPane)currentPane.getParent();
        p.getChildren().clear();
        p.getChildren().add(new LoginView().getView());
    }

    @FXML
    private void voteHyperlinkAction(ActionEvent event) {
        AnchorPane p = (AnchorPane)currentPane.getParent();
        p.getChildren().clear();
        p.getChildren().add(new LoginView().getView());
    }

    @FXML
    private void monitorHyperlinkAction(ActionEvent event) {
        AnchorPane p = (AnchorPane)currentPane.getParent();
        p.getChildren().clear();
        p.getChildren().add(new LoginView().getView());
    }
    
    
}
