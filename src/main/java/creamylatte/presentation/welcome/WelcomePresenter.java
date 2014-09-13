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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToLogin(ActionEvent event) {
        AnchorPane p = (AnchorPane)currentPane.getParent();
        p.getChildren().clear();
        p.getChildren().add(new LoginView().getView());
    }

    @FXML
    private void goToResults(ActionEvent event) {
        
        
    }

    /**
     * @return the user
     */
    public ObjectProperty<UserAccount> getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(ObjectProperty<UserAccount> user) {
        this.user = user;
    }
    
}