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
import creamylatte.business.services.CandidateService;
import creamylatte.presentation.login.LoginPresenter;
import creamylatte.presentation.welcome.WelcomePresenter;
import creamylatte.presentation.welcome.WelcomeView;
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
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javax.inject.Inject;


/**
 * FXML Controller class
 * 
 * @author Hadouken
 */
public class MainPresenter implements Initializable {

    @Inject
    private CandidateService vs;    
    @Inject
    private Authenticator auth;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane contentPane;
    
    @FXML
    private MenuButton menuButton;
    
    
    private ObjectProperty<UserAccount> user;
    private ObservableList<UserAccount> userList;
    private WelcomePresenter welcomePresenter;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = new SimpleObjectProperty<>();
//        this.user.addListener((ObservableValue<? extends UserAccount> observable, UserAccount oldValue, UserAccount newValue) -> {
//            if(newValue.getUsername().equals("admin")){
//                 menuButton.setVisible(true);
//            }else{
//                menuButton.setVisible(false);
//            }
//        });
        
        WelcomeView wc = new WelcomeView();
        welcomePresenter = (WelcomePresenter)wc.getPresenter();

        contentPane.getChildren().add(wc.getView());
    }    


    public ObjectProperty<UserAccount> getUser() {
        return user;
    }


    private void closeStage(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    @FXML
    private void homeAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    private void loginAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }    
}
