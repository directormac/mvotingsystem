/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.presentation.main;

import creamylatte.business.models.UserAccount;
import creamylatte.business.services.Authenticator;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.admin.voterform.VoterFormPresenter;
import creamylatte.presentation.login.LoginPresenter;
import creamylatte.presentation.welcome.WelcomePresenter;
import creamylatte.presentation.welcome.WelcomeView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;


/**
 * FXML Controller class
 * 
 * @author Hadouken
 */
public class MainPresenter implements Initializable {
    @FXML
    private AnchorPane contentPane;
    
    private ObjectProperty<UserAccount> user;
    private ObservableList<UserAccount> userList;
    private LoginPresenter loginPresenter;
    private VoterFormPresenter voterPresenter;
    
    @Inject
    private VoterService vs;    
    @Inject
    private Authenticator auth;
    @FXML
    private MenuItem partylistMenu;
    @FXML
    private Button closeButton;
    private WelcomePresenter welcomePresenter;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = new SimpleObjectProperty<>();
        
        
        WelcomeView wc = new WelcomeView();
        welcomePresenter = (WelcomePresenter)wc.getPresenter();
        contentPane.getChildren().add(wc.getView());
        
        
        
        
//        LoginView loginView = new LoginView();
//        this.loginPresenter = (LoginPresenter) loginView.getPresenter();
//        this.user.bindBidirectional(this.loginPresenter.getUser());
//        contentPane.getChildren().clear();
//        contentPane.getChildren().add(loginView.getView());
    }    


    public ObjectProperty<UserAccount> getUser() {
        return user;
    }

    @FXML
    private void searchCandidate(ActionEvent event) {
    }

    @FXML
    private void searchPartylist(ActionEvent event) {
      
    }

    @FXML
    private void closeStage(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
