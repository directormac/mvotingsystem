/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presenter.admin.admin;

import creamylatte.business.models.UserAccount;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author Hadouken
 * 
 */
public class AdminPresenter implements Initializable {
    private ObjectProperty<UserAccount> user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new SimpleObjectProperty<>();
    }    

    /**
     * @return the user
     */
    public ObjectProperty<UserAccount> getUser() {
        return user;
    }
    
}
