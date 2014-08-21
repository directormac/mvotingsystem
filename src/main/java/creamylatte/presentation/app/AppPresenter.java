/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package creamylatte.presentation.app;

import creamylatte.business.models.UserAccount;
import creamylatte.presentation.login.LoginPresenter;
import creamylatte.presentation.login.LoginView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class AppPresenter implements Initializable {
    @FXML
    private AnchorPane topPane;
    @FXML
    private AnchorPane centerPane;
    
    
    ObjectProperty<UserAccount> user;
    LoginPresenter loginPresenter;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = new SimpleObjectProperty<>();
        this.user.addListener((ObservableValue<? extends UserAccount> observable, UserAccount oldValue, UserAccount newValue) -> {
            if(newValue.getUsername().equals("admin")){
//                 mainMenu.setVisible(true);
            }
        });
        LoginView loginView = new LoginView();
        this.loginPresenter = (LoginPresenter) loginView.getPresenter();
        this.user.bindBidirectional(this.loginPresenter.getUser());
        centerPane.getChildren().clear();
        centerPane.getChildren().add(loginView.getView());
    }    
    
    
    
    
}
