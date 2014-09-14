/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managevoter.searchvoter;

import creamylatte.business.models.Voter;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.admin.managevoter.voterform.VoterFormPresenter;
import creamylatte.presentation.admin.managevoter.voterform.VoterFormView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class SearchVoterPresenter implements Initializable {
    @FXML
    private TextField searchVoter;
    @FXML
    private ListView<Voter> voterListView;
    @FXML
    private AnchorPane voterFormPane;

    VoterFormPresenter voterFormPresenter;
    ObservableList<Voter> voterList;
    private ObjectProperty<Voter> selectedVoter;
    
    
    @Inject
    VoterService vs;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VoterFormView voterFormView = new VoterFormView();
        voterFormPresenter = (VoterFormPresenter) voterFormView.getPresenter();
        voterFormPresenter.getEditButton().setVisible(true);
        voterFormPresenter.getRemoveButton().setVisible(true);
        voterFormPresenter.getSaveButton().setText("Update");        
        voterFormPresenter.getSelectedVoter().bind(selectedVoter);
        voterFormPresenter.getSaveButton().setVisible(false);
        voterFormPane.getChildren().add(voterFormView.getView());        
        voterListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        voterListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Voter>() {
            @Override
            public void changed(ObservableValue<? extends Voter> observable, Voter oldValue, Voter newValue) {
                selectedVoter.set(voterListView.getSelectionModel().getSelectedItem());
                voterFormPresenter.getSelectedVoter().set(newValue);
            }
        });
        
        prepareListView();        
        searchVoter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty()){
                   voterList = FXCollections.observableArrayList(vs.search(newValue));
                }else{
                   prepareListView();
                }
            }
        });    
        
        
        
        
    }   
    
   private void prepareListView(){
       voterList = FXCollections.observableArrayList(vs.all());
       voterListView.setItems(voterList);
   }

    public ObjectProperty<Voter> getSelectedVoter() {
        return selectedVoter;
    }

    public void setSelectedVoter(ObjectProperty<Voter> selecterVoter) {
        this.selectedVoter = selecterVoter;
    }
    
}
