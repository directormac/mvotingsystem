/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.vote;

import creamylatte.business.models.UserAccount;
import creamylatte.business.models.Voter;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.vote.manual.ManualPresenter;
import creamylatte.presentation.vote.manual.ManualView;
import creamylatte.presentation.vote.straight.StraightPresenter;
import creamylatte.presentation.vote.straight.StraightView;
import creamylatte.presentation.vote.viewframework.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class VotePresenter implements Initializable {
    @FXML
    private Button voteStraightButton;
    @FXML
    private Button voteManuallyButton;
    @FXML
    private AnchorPane mainPane;
    
    private ObjectProperty<UserAccount> user;
    
    
    
    Group root;
    ViewController viewController;
    
    private static final String straightPresenter = "voteStraightPresenter";
    StraightView straightView;
    private static final String manualViewPresenter = "voteManualPresenter";
    ManualView manualView;
    

    @Inject
    VoterService service;
    
    Voter voter;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        straightView = new StraightView();
        manualView = new ManualView();
        user = new SimpleObjectProperty<>();
        viewController = new ViewController();        
        root = new Group();
        root.getChildren().addAll(viewController);
        viewController.loadScreen(straightPresenter, straightView);
        viewController.loadScreen(manualViewPresenter, manualView);
    }    

    @FXML
    private void voteStraightAction(ActionEvent event) {
        
        StraightPresenter voteStraightPresenter = (StraightPresenter) straightView.getPresenter();
        voter = service.searchByAccount(user.get());
        voteStraightPresenter.getVoterNameLabel().setText(voter.getLastName().toUpperCase()+" , " + voter.getFirstName().toUpperCase());
        voteStraightPresenter.setVoter(new SimpleObjectProperty<>(voter));
        viewController.setScreen(VotePresenter.straightPresenter);
        changePane(root);
    }

    @FXML
    private void voteManuallyAction(ActionEvent event) {
        
        voter = service.searchByAccount(user.get());
        ManualPresenter mp = (ManualPresenter)manualView.getPresenter();
        mp.getVoterNameLabel().setText(voter.getLastName().toUpperCase()+" , " + voter.getFirstName().toUpperCase());
        mp.setVoter(new SimpleObjectProperty<>(voter));
        viewController.setScreen(manualViewPresenter);
        changePane(root);
    }
    
    private void changePane(Group group){
        AnchorPane currentPane = (AnchorPane) mainPane.getParent();
        currentPane.getChildren().clear();
        currentPane.getChildren().add(group);
    }
    
    public ObjectProperty<UserAccount> getUser() {
        return user;
    }

    
}
