/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.managevotertest.voterchart;

import creamylatte.business.models.Voter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class VoterChartPresenter implements Initializable {
    @FXML
    private PieChart voterChart;
    
    PieChart.Data pdGrade7,pdGrade8,pdGrade9,pdGrade10;
    
    private ObservableList<Voter> masterData = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> pieChartData;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pdGrade7 = new PieChart.Data("Grade 7", gradeSize("Seven"));
        pdGrade8 = new PieChart.Data("Grade 8", gradeSize("Eight"));
        pdGrade9 = new PieChart.Data("Grade 9", gradeSize("Nine"));
        pdGrade10 = new PieChart.Data("Grade 10", gradeSize("Ten"));
        pieChartData = FXCollections.observableArrayList(pdGrade7,pdGrade8,pdGrade9,pdGrade10);         
        voterChart.setData(pieChartData);
        
        getMasterData().addListener(new ListChangeListener<Voter>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Voter> c) {
                pdGrade7.setPieValue(gradeSize("Seven"));
                pdGrade8.setPieValue(gradeSize("Eight"));
                pdGrade9.setPieValue(gradeSize("Nine"));
                pdGrade10.setPieValue(gradeSize("Ten"));
            }
        });
 
    }   
    
    public int gradeSize(String string){
        int size = 0;
        for(Voter voter : getMasterData()){
            
            if(voter.getGradeLevel().equalsIgnoreCase(string))
                size++;
        }
//        size = masterData.stream().filter((voter) -> (voter.getGradeLevel().equalsIgnoreCase(string))).map((_item) -> 1).reduce(size, Integer::sum);
        return size;
    }


    public ObservableList<Voter> getMasterData() {
        return masterData;
    }

    public void setMasterData(ObservableList<Voter> masterData) {
        this.masterData = masterData;
    }
    
    
    
}
