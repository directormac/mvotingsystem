/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presenter.login;

import creamylatte.business.models.UserAccount;
import creamylatte.business.services.Authenticator;
import creamylatte.presenter.admin.admin.AdminPresenter;
import creamylatte.presenter.admin.admin.AdminView;
import creamylatte.presenter.vote.vote.VotePresenter;
import creamylatte.presenter.vote.vote.VoteView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.ScrollPane;
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

                    if(this.getUser().get().getUsername().equals("admin")){
                        AdminView adminView = new AdminView();
                        this.adminPresenter = (AdminPresenter) adminView.getPresenter();                
                        this.adminPresenter.getUser().bindBidirectional(user);
                        changeContentPane(adminView.getView());
                    }else{
                        VoteView voteView = new VoteView();
                        this.votePresenter = (VotePresenter) voteView.getPresenter();
                        this.votePresenter.getUser().bindBidirectional(user);
                        changeContentPane(voteView.getView());
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
        ScrollPane contentPane = (ScrollPane)currentPane.getParent().getParent();
        contentPane.setContent(parent);
        
    }
    
}
