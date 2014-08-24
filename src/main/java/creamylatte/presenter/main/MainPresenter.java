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
import creamylatte.presenter.admin.partylist.PartyListView;
import creamylatte.presenter.admin.voter.VoterPresenter;
import creamylatte.presenter.admin.voter.VoterView;
import creamylatte.presenter.login.LoginPresenter;
import creamylatte.presenter.login.LoginView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private ScrollPane contentPane;
    
    
    private ObjectProperty<UserAccount> user;
    private ObservableList<UserAccount> userList;
    private LoginPresenter loginPresenter;
    private VoterPresenter voterPresenter;
    
    @Inject
    private CandidateService vs;    
    @Inject
    private Authenticator auth;
    @FXML
    private MenuItem partylistMenu;
    @FXML
    private AnchorPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = new SimpleObjectProperty<>();
        this.user.addListener((ObservableValue<? extends UserAccount> observable, UserAccount oldValue, UserAccount newValue) -> {
            if(newValue.getUsername().equals("admin")){
                 mainMenu.setVisible(true);
            }
        });
        LoginView loginView = new LoginView();
//        this.loginPresenter = (LoginPresenter) loginView.getPresenter();
//        this.user.bindBidirectional(this.loginPresenter.getUser());
//        contentPane.setContent(loginView.getView());          
        mainPane.getChildren().clear();
        AnchorPane.setTopAnchor(loginView.getView(), 10.0);        
        mainPane.getChildren().add(loginView.getView());
        
    }    

    @FXML
    private void searchVoter(ActionEvent event) {
        VoterView voterView = new VoterView();
        voterPresenter = (VoterPresenter) voterView.getPresenter();        
        contentPane.setContent(voterView.getView());
    }    
    
    /**
     * @return the currentUser
     */
    public ObjectProperty<UserAccount> getUser() {
        return user;
    }

    @FXML
    private void searchCandidate(ActionEvent event) {
    }

    @FXML
    private void searchPartylist(ActionEvent event) {        
        contentPane.setContent(new PartyListView().getView());
    }

    private void closeStage(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
