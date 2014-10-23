/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managepartylist;

import com.sun.javafx.collections.SortableList;
import creamylatte.business.models.Candidate;
import creamylatte.business.models.Party;
import creamylatte.business.services.CandidateService;
import creamylatte.presentation.admin.managepartylist.partyform.PartyFormPresenter;
import creamylatte.presentation.admin.managepartylist.partyform.PartyFormView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ManagePartyListPresenter implements Initializable {
    @FXML
    private Button removePartylistButton;
    @FXML
    private Button renamePartyListButton;
    @FXML
    private AnchorPane formPane;
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
    SortableList<Candidate> sortableData;
    ObjectProperty<Party> selectedParty = new SimpleObjectProperty<>();
    
    @Inject
    CandidateService service;
    
    PartyFormView partyFormView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        candidatePositionColumn.setCellValueFactory(cellData -> cellData.getValue().getPosition().nameProperty());
        candidateNameColumn.setCellValueFactory(cellData -> cellData.getValue().getVoterId().lastNameProperty().concat(" ")
                                                   .concat(cellData.getValue().getVoterId().firstNameProperty()));        
        partyListData.addAll(service.getAllParty());
        
        partylistTable.setItems(masterData);
        partyListComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Party> observable, Party oldValue, Party newValue) -> {
            masterData.clear();
                masterData.addAll(FXCollections.observableArrayList(newValue.getCandidates()));
        });
        formPane.getChildren().addListener((ListChangeListener.Change<? extends Node> c) -> {
            refreshPartyList();
        });
        removePartylistButton.disableProperty().bind(partyListComboBox.selectionModelProperty().isNull());
        renamePartyListButton.disableProperty().bind(partyListComboBox.selectionModelProperty().isNull());
    }    
    
    private void refreshPartyList(){
        partyListComboBox.getSelectionModel().clearSelection();
        partyListData.clear();
        partyListData.addAll(service.getAllParty());
        partyListComboBox.setItems(partyListData);
        partyListComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void removePartylistAction(ActionEvent event) {        
        service.removeParty(partyListComboBox.getSelectionModel().getSelectedItem());
        refreshPartyList();
    }

    @FXML
    private void renamePartyListAction(ActionEvent event) {
        partyFormView = new PartyFormView();
        PartyFormPresenter partyFormPresenter = (PartyFormPresenter) partyFormView.getPresenter();
        partyFormPresenter.setParty(new SimpleObjectProperty<>(
                partyListComboBox.getSelectionModel().getSelectedItem()
        )); 
        partyFormPresenter.getAddPartyListButton().setText("Update");
        partyFormPresenter.getPartyNameField()
                .setText(partyListComboBox.getSelectionModel().getSelectedItem().getName());
        enableFormPane(partyFormView);
    }
    

    @FXML
    private void addNewPartyAction(ActionEvent event) {
        partyFormView = new PartyFormView();
        enableFormPane(partyFormView);
    }

    @FXML
    private void partyListComboBoxShown(Event event) {
        refreshPartyList();
    }
    
    public void enableFormPane(PartyFormView partyForm){
        formPane.getChildren().clear();
        formPane.getChildren().add(partyForm.getView());
        formPane.setVisible(true);
    }
    
    
}
