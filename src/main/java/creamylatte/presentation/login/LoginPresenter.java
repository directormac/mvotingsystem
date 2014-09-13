/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.login;

import creamylatte.business.models.UserAccount;
import creamylatte.business.services.Authenticator;
import creamylatte.presentation.admin.AdminPresenter;
import creamylatte.presentation.admin.AdminView;

import creamylatte.presentation.vote.vote.VotePresenter;
import creamylatte.presentation.vote.vote.VoteView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;


/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class LoginPresenter implements Initializable {

    
    @FXML
    private ComboBox<UserAccount> accountCBox;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordField;
    @FXML
    private AnchorPane currentPane;
    
    @Inject
    Authenticator auth;
    
    VotePresenter votePresenter;
    AdminPresenter adminPresenter;
    ObservableList<UserAccount> userList;
    private ObjectProperty<UserAccount> user;

    
    @FXML
    private Label incorrectPasswordLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new SimpleObjectProperty<>();
        loginButton.disableProperty().bind(passwordField.textProperty().isEmpty()); 
        passwordField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(newValue.isEmpty()){
                incorrectPasswordLabel.setVisible(false);
            }
        });

    }    

    @FXML
    private void loginAccount(ActionEvent event) {          
        login();
    }
    
    private void login(){
        if(!accountCBox.getEditor().textProperty().get().isEmpty()){          
            try{
                this.user.set(auth.checkCredentials(accountCBox.getEditor().textProperty().get(), passwordField.getText()));
                System.out.println("username:" + this.user.get().getUsername());
                    if(this.getUser().get().getUsername().equals("admin")){
                        AdminView adminView = new AdminView();
                        adminPresenter = (AdminPresenter)adminView.getPresenter();
                        changeContentPane(adminView.getView());
                    }else{
                        changeContentPane(new VoteView().getView());
                    }
            }catch(NullPointerException e){
                incorrectPasswordLabel.setVisible(true);                
            }
        }
    }
    
    @FXML
    private void comboAction(Event event) { 
       userList = FXCollections.observableList(auth.findByUserName(accountCBox.getEditor().textProperty().get()));
       accountCBox.setItems(userList); 
    }
    
    private void mooose(ActionEvent event){
        accountCBox.setEditable(true);
    }

    /**
     * @return the user
     */
    public ObjectProperty<UserAccount> getUser() {
        return user;
    }
    
    public void changeContentPane(Parent parent){
        AnchorPane mainPane = (AnchorPane) currentPane.getParent();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(parent);
    }
    
}
