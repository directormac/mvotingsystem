/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.presentation.admin.managevotertest;


import creamylatte.business.models.Candidate;
import creamylatte.business.models.Voter;
import creamylatte.business.services.CandidateService;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.admin.managevotertest.voterchart.VoterChartPresenter;
import creamylatte.presentation.admin.managevotertest.voterchart.VoterChartView;
import creamylatte.presentation.admin.managevotertest.voterform.VoterFormPresenter;
import creamylatte.presentation.admin.managevotertest.voterform.VoterFormView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
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
public class ManageVoterTestPresenter implements Initializable {
    @FXML
    private AnchorPane currentPane;
    @FXML
    private TableView<Voter> voterTable;
    @FXML
    private TableColumn<Voter, String> firstNameColumn;
    @FXML
    private TableColumn<Voter, String> lastNameColumn;
    @FXML
    private TableColumn<Voter, String> gradeLevelColumn;
    @FXML
    private TableColumn<Voter, String> passwordColumn;
    @FXML
    private TextField filterTextField;
    @FXML
    private Button addVoterButton;
    @FXML
    private Button removeVoterButton;
    @FXML
    private Button editVoterButton;
    @FXML
    private AnchorPane rightPane;

    @Inject
    VoterService service;
    @Inject
    CandidateService candidateService;
    
    VoterChartPresenter voterChartPresenter;
    VoterChartView voterChartView;
    VoterFormPresenter voterFormPresenter;
    VoterFormView voterFormView;
    
    
    ObservableList<Voter> masterData = FXCollections.observableArrayList();
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      masterData.addAll(service.all());
      firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
      lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
      gradeLevelColumn.setCellValueFactory(cellData -> cellData.getValue().gradeLEvelProperty());
      passwordColumn.setCellValueFactory(cellData -> cellData.getValue().getAccount().passwordProperty());
    
      FilteredList<Voter> filteredData = new FilteredList<>(masterData, p -> true);
      filterTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          filteredData.setPredicate(voter ->{
                // If filter text is empty, display all voter.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (voter.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (voter.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
       SortedList<Voter> sortedData = new SortedList<>(filteredData);
       sortedData.comparatorProperty().bind(voterTable.comparatorProperty());
       voterTable.setItems(sortedData);
       
       voterChartView = new VoterChartView();
       voterChartPresenter = (VoterChartPresenter) voterChartView.getPresenter();
       voterChartPresenter.getMasterData().addAll(service.all());
       
       rightPane.getChildren().clear();
       rightPane.getChildren().add(voterChartView.getView());
       
       
       voterTable.itemsProperty().addListener(new ChangeListener<ObservableList<Voter>>() {
          @Override
          public void changed(ObservableValue<? extends ObservableList<Voter>> observable, ObservableList<Voter> oldValue, ObservableList<Voter> newValue) {
              voterChartPresenter.getMasterData().clear();
              voterChartPresenter.getMasterData().addAll(service.all());
          }
      });
       
       
       
    }

    @FXML
    private void addVoterButtonAction(ActionEvent event) {
       rightPane.getChildren().clear();
       rightPane.getChildren().add(new VoterFormView().getView());
    }

    @FXML
    private void removeVoterButtonAction(ActionEvent event) {
        List<Candidate> candidates = candidateService.getAllCandidates();
            for(Candidate candidate : candidates){
                if(candidate.getVoterId().equals(voterTable.getSelectionModel().getSelectedItem())){
                    service.remove(candidate);
                }else{
                    service.remove(voterTable.getSelectionModel().getSelectedItem());
                }
            }
    }

    @FXML
    private void editVoterButtonAction(ActionEvent event) {
        
        
    }
    
    
}
