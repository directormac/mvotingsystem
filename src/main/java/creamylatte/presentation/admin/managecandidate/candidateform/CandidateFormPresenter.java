
package creamylatte.presentation.admin.managecandidate.candidateform;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.Party;
import creamylatte.business.models.Position;
import creamylatte.business.models.Voter;
import creamylatte.business.services.CandidateService;
import creamylatte.business.services.VoterService;
import creamylatte.presentation.admin.managecandidate.candidateoverview.CandidateOverviewView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.inject.Inject;


public class CandidateFormPresenter implements Initializable {
    @FXML
    private ComboBox<Position> positionComboBox;
    @FXML
    private ComboBox<Party> partylistComboBox;

    @FXML
    private Button saveButton;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label gradeLevelLabel;

    @FXML
    private ListView<Voter> studentListView;

    @FXML
    private TextField filterStudentField;
    
    private AnchorPane anchorPane;
    
    @Inject
    CandidateService service;
    @Inject
    VoterService voterService;
    
    ObservableList<Party> partyList;
    ObservableList<Position> positionList;
    
    ObservableList<Voter> votersData;
    FilteredList<Voter> filteredVotersData;

    ObservableList<Position> positionsData;
    FilteredList<Position> filteredPositionsData;
    


    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button cancelButton;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        votersData = FXCollections.observableArrayList();
        filteredVotersData = new FilteredList<>(votersData, p -> true);
        filterStudentField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredVotersData.setPredicate(voter ->{
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (voter.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (voter.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            }
        });
        studentListView.setItems(filteredVotersData);
        
        positionsData = FXCollections.observableArrayList();
        filteredPositionsData = new FilteredList<>(positionsData, p -> true);
        partylistComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Party>() {
            public void changed(ObservableValue<? extends Party> observable, Party oldValue, Party newValue) {
                
                positionComboBox.setItems(FXCollections.observableArrayList(getAvailablePositions(newValue)));
            }
        });
        
        positionComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Position> observable, Position oldValue, Position newValue) -> {
               votersData.clear(); 
               if(newValue.getName().equalsIgnoreCase("President")){                   
                   votersData.addAll(getAvailableCandidates(service.searchByYearLevel("Fourth Year")));
               }else if(newValue.getName().equalsIgnoreCase("Vice President")){
                   votersData.addAll(getAvailableCandidates(service.searchByYearLevel("Third Year")));
                }else if(newValue.getName().equalsIgnoreCase("First Year")){
                   votersData.addAll(getAvailableCandidates(service.searchByYearLevel("First Year")));
                }else if(newValue.getName().equalsIgnoreCase("Second Year")){
                   votersData.addAll(getAvailableCandidates(service.searchByYearLevel("Second Year")));
                }else if(newValue.getName().equalsIgnoreCase("Third Year")){
                   votersData.addAll(getAvailableCandidates(service.searchByYearLevel("Third Year")));
                }else if(newValue.getName().equalsIgnoreCase("Fourth Year")){
                   votersData.addAll(getAvailableCandidates(service.searchByYearLevel("Fourth Year")));
                }else{
                    votersData.addAll(getAvailableCandidates(service.getAllVoter()));
                }
        });

        partylistComboBox.setCellFactory(new Callback<ListView<Party>,ListCell<Party>>(){ 
            @Override
            public ListCell<Party> call(ListView<Party> p) {                 
                final ListCell<Party> cell = new ListCell<Party>(){ 
                    @Override
                    protected void updateItem(Party t, boolean bln) {
                        super.updateItem(t, bln);                         
                        if(t != null){
                            setText(t.getName());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        positionComboBox.setCellFactory(new Callback<ListView<Position>,ListCell<Position>>(){ 
            @Override
            public ListCell<Position> call(ListView<Position> p) {                 
                final ListCell<Position> cell = new ListCell<Position>(){ 
                    @Override
                    protected void updateItem(Position t, boolean bln) {
                        super.updateItem(t, bln);                         
                        if(t != null){
                            setText(t.getName());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        
        

       studentListView.setCellFactory(new Callback<ListView<Voter>,ListCell<Voter>>(){ 
            @Override
            public ListCell<Voter> call(ListView<Voter> p) {                 
                final ListCell<Voter> cell = new ListCell<Voter>(){ 
                    @Override
                    protected void updateItem(Voter t, boolean bln) {
                        super.updateItem(t, bln);                         
                        if(t != null){
                            setText(t.getFirstName() + ", " + t.getLastName() +  " : " + t.getYearLevel());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        
        

        studentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Voter>() {
           @Override
           public void changed(ObservableValue<? extends Voter> observable, Voter oldValue, Voter newValue) {
               firstNameLabel.setText(newValue.getFirstName());
               lastNameLabel.setText(newValue.getLastName());
               gradeLevelLabel.setText(newValue.getYearLevel());
           }
       });

       filterStudentField.disableProperty().bind(positionComboBox.getSelectionModel().selectedItemProperty().isNull());
       studentListView.disableProperty().bind(positionComboBox.getSelectionModel().selectedItemProperty().isNull());
       positionComboBox.disableProperty().bind(partylistComboBox.getSelectionModel().selectedItemProperty().isNull());       
       saveButton.disableProperty().bind(studentListView.getSelectionModel().selectedItemProperty().isNull());
       
    }  
    
    public List<Voter> getAvailableCandidates(List<Voter> voters){
        List<Candidate> candidates = service.getAllCandidates();
        candidates.stream().filter((candidate) -> 
           (voters.contains(candidate.getVoterId()))).forEach((candidate) -> {
            voters.remove(candidate.getVoterId());
        });
        return voters;
    }
    
    
    
    @FXML
    private void showPartyList(Event event) { 
       partyList = FXCollections.observableArrayList(service.getAllParty());
       partylistComboBox.setItems(partyList);
    }
    
    @FXML
    private void showPositions(Event event) { 
//        System.out.println(partylistComboBox.getSelectionModel().getSelectedItem());
//        
//        positionComboBox.setItems(
//                FXCollections.observableArrayList(
//                        getAvailablePositions(partylistComboBox.getSelectionModel().getSelectedItem())
//                )
//        );
        
    }


    @FXML
    private void saveButtonAction(ActionEvent event) {
        Candidate candidate = new Candidate();
        candidate.setParty(partylistComboBox.getSelectionModel().getSelectedItem());
        candidate.setPosition(positionComboBox.getSelectionModel().getSelectedItem());
        candidate.setVoterId(studentListView.getSelectionModel().getSelectedItem());
        
        service.save(candidate);
        
        changePane(new CandidateOverviewView().getView());
    }
    
    
    private List<Position> getAvailablePositions(Party party){
        service.refresh(party);
        List<Position> positions = service.getAllPositions();
        int sargeantCounter = 0;
        int bmanagerCounter = 0;        
        
        
        for(Position position: positions){
            try{                
                if(position.getName().equalsIgnoreCase("Sgt. at Arms")){
                    sargeantCounter++;
                    System.out.println(sargeantCounter++);
                }else if(position.getName().equalsIgnoreCase("Bus. Manager")){
                    bmanagerCounter++;
                    System.out.println(bmanagerCounter++);
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }
            
        }
        List<Candidate> candidates = party.getCandidates();
        if(candidates.isEmpty()){
            return positions;
        }else{
            for(Candidate candidate : candidates){
                try{
                    if(candidate.getPosition().getName().equalsIgnoreCase("Sgt. at Arms") 
                        || candidate.getPosition().getName().equalsIgnoreCase("Bus. Manager") ){
                        if(sargeantCounter == 2 || bmanagerCounter == 2){
                            positions.remove(candidate.getPosition());
                        }                    
                    }                    
                    else{
                       positions.remove(candidate.getPosition()); 
                    }
                     
                }catch(NullPointerException e){
                    e.printStackTrace();
                }                
            }
            return positions;
        }
    }
    
    
    
    private void changePane(Parent parent){
        AnchorPane mainPane1 = (AnchorPane)mainPane.getParent();
        mainPane1.getChildren().clear();
        mainPane1.getChildren().add(parent);
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        
        
        
    }
    
}
