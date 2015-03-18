
package creamylatte.presentation.admin.reports.graphresult;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.Position;
import creamylatte.business.services.CandidateService;
import creamylatte.business.services.VoterService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javax.inject.Inject;


public class GraphResultPresenter implements Initializable{
    @FXML
    private Button saveReportButton;
    @FXML
    private PieChart resultChart;
    @FXML
    private ComboBox<Position> positionComboBox;

    @Inject
    private VoterService voterService;
    
    @Inject
    private CandidateService candidateService;
    
    ObservableList<PieChart.Data> pieChartData;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       positionComboBox.setItems(FXCollections.observableList(candidateService.getAllPositions()));
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
       
       
       positionComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Position>() {
           @Override
           public void changed(ObservableValue<? extends Position> observable, Position oldValue, Position newValue) {
                fillChart(newValue);
           }
       });
       
       
       
    }

    private void fillChart(Position position){
        pieChartData = FXCollections.observableArrayList();         
        for(Candidate candidate: position.getCandidates()){
            pieChartData.add(new PieChart.Data(
                    candidate.getVoterId().getLastName() + " , " + candidate.getVoterId().getFirstName()
                            , candidate.getVoters().size())
                            );
        }
        
        resultChart.setData(pieChartData);
        
    }
    
    private int voteSize(String s){
        int size = 0;
        
        return size;
    }
    
    
    @FXML
    private void saveReportButtonAction(ActionEvent event) {
    }

    @FXML
    private void partyListComboBoxShown(Event event) {
        
        
    }
    
}
