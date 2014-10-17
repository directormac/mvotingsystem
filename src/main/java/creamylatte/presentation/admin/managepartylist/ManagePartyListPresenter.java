/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managepartylist;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.Party;
import creamylatte.business.services.CandidateService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ManagePartyListPresenter implements Initializable {
    @FXML
    private TreeTableView<Candidate> partylistTable;
    @FXML
    private TreeTableColumn<Candidate, String> positionColumn;
    @FXML
    private TreeTableColumn<Candidate, String>  nameColumn;
    @FXML
    private TreeTableColumn<Candidate, String>  gradeColumn;
    @FXML
    private Button addNewPartyButton;
    @FXML
    private AnchorPane addPartyPane;
    @FXML
    private Button addPartyListButton;
    @FXML
    private TextField partyNameField;
    @FXML
    private Button candidateProfileButton;
    @FXML
    private Button renamePartyListButton;
    @FXML
    private Button removeCandidateButton;
    @FXML
    private Button editCandidateButton;
    @FXML
    private Button removePartylistButton;
    
    @Inject
    CandidateService candidateService;
    
    ObservableList<Candidate> sampleData;
    
    TreeItem<Candidate> partyList = new TreeItem<>(new Candidate());
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Party> partys = FXCollections.observableArrayList(candidateService.getAllParty());
        sampleData = FXCollections.observableArrayList(candidateService.getAllCandidates());

        
        sampleData.stream().forEach((candidate) -> {
                partyList.getChildren().add(new TreeItem<>(candidate));

        });
        
        positionColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Candidate, String> param) ->
                new ReadOnlyStringWrapper(param.getValue().getValue().getPosition().getName())
        );
        
        nameColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Candidate, String> param) ->                
                new ReadOnlyStringWrapper(param.getValue().getValue().getVoterId().getLastName() 
                        + " , " + 
                        param.getValue().getValue().getVoterId().getFirstName())
        );
        
        gradeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Candidate, String> param) ->
                new ReadOnlyStringWrapper(param.getValue().getValue().getVoterId().getGradeLevel())
        );
        
        partyList.setExpanded(true);
        
        partylistTable.setRoot(partyList);
        partylistTable.setShowRoot(true);
    }    

    @FXML
    private void addNewPartyAction(ActionEvent event) {
    }

    @FXML
    private void addPartyListAction(ActionEvent event) {
    }

    @FXML
    private void candidateProfileAction(ActionEvent event) {
    }

    @FXML
    private void renamePartyListAction(ActionEvent event) {
    }

    @FXML
    private void removeCandidateAction(ActionEvent event) {
    }

    @FXML
    private void editCandidateAction(ActionEvent event) {
    }

    @FXML
    private void removePartylistAction(ActionEvent event) {
    }
    
}
