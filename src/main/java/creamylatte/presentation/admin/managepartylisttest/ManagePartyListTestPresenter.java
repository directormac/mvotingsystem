/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managepartylisttest;

import com.sun.javafx.collections.SortableList;
import creamylatte.business.models.Candidate;
import creamylatte.business.models.Party;
import creamylatte.business.models.Position;
import creamylatte.business.models.Voter;
import creamylatte.business.services.CandidateService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ManagePartyListTestPresenter implements Initializable {
    @FXML
    private Button removePartylistButton;
    @FXML
    private Button renamePartyListButton;
    @FXML
    private AnchorPane formPane;
    @FXML
    private TextField partyNameField;
    @FXML
    private Button addPartyListButton;
    @FXML
    private Button addNewPartyButton;
    @FXML
    private ComboBox<Party> partyListComboBox;
    @FXML
    private TableView<Candidate> partylistTable;

    @FXML
    private TableColumn<Candidate, String> candidatePositionColumn;
    @FXML
    private TableColumn<Candidate, String> candidateNameColumn;

    ObservableList<Party> partyListData = FXCollections.observableArrayList();;
    ObservableList<Candidate> masterData= FXCollections.observableArrayList();;
    FilteredList<Candidate> filteredData;
    SortableList<Candidate> sortableData;
    ObjectProperty<Party> selectedParty = new SimpleObjectProperty<>();
    ObjectProperty<Party> formParty = new SimpleObjectProperty<>();
    
    @Inject
    CandidateService service;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        candidatePositionColumn.setCellValueFactory(cellData -> cellData.getValue().getPosition().nameProperty());
        candidateNameColumn.setCellValueFactory(cellData -> cellData.getValue().getVoterId().lastNameProperty().concat(" ")
                                                   .concat(cellData.getValue().getVoterId().firstNameProperty()));        
        partyListData.addAll(service.getAllParty());
        partyListComboBox.setItems(partyListData);
        filteredData = new FilteredList<>(masterData, p -> true);        
        partyListComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Party> observable, Party oldValue, Party newValue) -> {
            masterData.clear();
            if(newValue == null){
                masterData.addAll(FXCollections.observableArrayList(service.getAllCandidates()));
            }else{
                masterData.addAll(FXCollections.observableArrayList(newValue.getCandidates()));
            }
        });
        
        
        
        addPartyListButton.disableProperty().bind(partyNameField.textProperty().isEmpty());
    }    

    @FXML
    private void removePartylistAction(ActionEvent event) {
        partyListData.remove(selectedParty.get());
    }

    @FXML
    private void renamePartyListAction(ActionEvent event) {
        formPane.setVisible(true);
        Party party = selectedParty.get();
        partyNameField.setText(party.getName());
        
    }

    @FXML
    private void addPartyListAction(ActionEvent event) {
        formPane.setVisible(false);
        
        
    }

    @FXML
    private void addNewPartyAction(ActionEvent event) {
        formPane.setVisible(true);
        
    }

    @FXML
    private void partyListComboBoxShown(Event event) {
        
    }
    
    
}
