/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package votingsystem.presenter.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class MainPresenter implements Initializable {
    @FXML
    private MenuBar mainMenu;
    @FXML
    private MenuItem voterMenu;
    @FXML
    private MenuItem candidateMenu;
    @FXML
    private MenuItem partylistMenu;
    @FXML
    private AnchorPane contentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchVoter(ActionEvent event) {
    }

    @FXML
    private void searchCandidate(ActionEvent event) {
    }

    @FXML
    private void searchPartylist(ActionEvent event) {
    }
    
}
