/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.presentation.admin.managevoter;

import creamylatte.business.models.Voter;
import creamylatte.business.services.CandidateService;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.admin.managevoter.voterform.VoterFormPresenter;
import creamylatte.presentation.admin.managevoter.voterform.VoterFormView;
import java.lang.reflect.InvocationTargetException;
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
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ManageVoterPresenter implements Initializable {
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
    

    VoterFormPresenter voterFormPresenter;
    VoterFormView voterFormView;
    
    
    ObservableList<Voter> masterData = FXCollections.observableArrayList();
    ObjectProperty<Voter> currentVoter = new SimpleObjectProperty<>();
    FilteredList<Voter> filteredData;
    SortedList<Voter> sortedData;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      masterData.addAll(service.all());
      firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
      lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
      gradeLevelColumn.setCellValueFactory(cellData -> cellData.getValue().yearLevelProperty());
      passwordColumn.setCellValueFactory(cellData -> cellData.getValue().getAccount().passwordProperty());
      filteredData = new FilteredList<>(masterData, p -> true);
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
      
      
      
      
      sortedData = new SortedList<>(filteredData);
       sortedData.comparatorProperty().bind(voterTable.comparatorProperty());        

       voterTable.setItems(sortedData);
       
       
       voterFormView = new VoterFormView();
       voterFormPresenter = (VoterFormPresenter) voterFormView.getPresenter();

       
       
       
       masterData.addListener(new ListChangeListener<Voter>() {
          @Override
          public void onChanged(ListChangeListener.Change<? extends Voter> c) {
            
          }
        }
       );

       
       removeVoterButton.disableProperty().bind(voterTable.getSelectionModel().selectedItemProperty().isNull());
       editVoterButton.disableProperty().bind(voterTable.getSelectionModel().selectedItemProperty().isNull());
       
        voterTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Voter>() {
          @Override
          public void changed(ObservableValue<? extends Voter> observable, Voter oldValue, Voter newValue) {
              currentVoter.set(voterTable.getSelectionModel().getSelectedItem());
          }
        }
       );
        
         rightPane.getChildren().addListener(new ListChangeListener<Node>() {
          @Override
          public void onChanged(ListChangeListener.Change<? extends Node> c) {
                  masterData.clear();
                  masterData.addAll(service.all());
          }
      });
      
        
    }

    @FXML
    private void addVoterButtonAction(ActionEvent event) {
       voterFormView = new VoterFormView();
       voterFormPresenter = (VoterFormPresenter)voterFormView.getPresenter();
       rightPane.getChildren().clear();
       rightPane.getChildren().add(voterFormView.getView());
       voterTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeVoterButtonAction(ActionEvent event) {
        try{
            service.remove(currentVoter.get());
            voterTable.getSelectionModel().clearSelection();
            masterData.clear();
            masterData.addAll(service.all());
        }catch(DatabaseException e){
            Action response = Dialogs.create()           
                .title("Fail to remove voter")            
                .message( "The voter belongs to a party list")
                .showConfirm();
        }
        
        
        
    }

    @FXML
    private void editVoterButtonAction(ActionEvent event) {
       voterFormPresenter.setVoter(currentVoter.get());
       rightPane.getChildren().clear();
       rightPane.getChildren().add(voterFormView.getView());       
//       voterTable.getSelectionModel().clearSelection();     
    }

   
    
    
}
