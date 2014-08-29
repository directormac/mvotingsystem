/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.presenter.main;

import creamylatte.business.models.UserAccount;
import creamylatte.business.services.Authenticator;
import creamylatte.business.services.CandidateService;
import creamylatte.presenter.login.LoginPresenter;
import creamylatte.presenter.login.LoginView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javax.inject.Inject;


/**
 * FXML Controller class
 * 
 * @author Hadouken
 */
public class MainPresenter implements Initializable {
    @FXML
    private MenuItem voterMenu,candidateMenu;    
    @FXML
    private MenuBar mainMenu;
    
    @Inject
    private CandidateService vs;    
    @Inject
    private Authenticator auth;
    @FXML
    private MenuItem partylistMenu;
    @FXML
    private BorderPane mainPane;
    
    private ObjectProperty<UserAccount> user;
    private ObservableList<UserAccount> userList;
    private LoginPresenter loginPresenter;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = new SimpleObjectProperty<>();
        this.user.addListener((ObservableValue<? extends UserAccount> observable, UserAccount oldValue, UserAccount newValue) -> {
            if(newValue.getUsername().equals("admin")){
                 mainMenu.setVisible(true);
            }
        });
        LoginView loginView = new LoginView(); 
        loginPresenter = (LoginPresenter)loginView.getPresenter();
        this.user.bindBidirectional(loginPresenter.getUser());
        mainPane.setCenter(loginView.getView());
    }    

    @FXML
    private void searchVoter(ActionEvent event) {

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

    private void closeStage(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    

}
