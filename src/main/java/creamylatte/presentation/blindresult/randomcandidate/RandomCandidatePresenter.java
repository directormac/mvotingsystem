/*
 * Computerized Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */
package creamylatte.presentation.blindresult.randomcandidate;


import creamylatte.business.models.Candidate;
import creamylatte.business.models.Position;
import creamylatte.business.services.CandidateService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class RandomCandidatePresenter implements Initializable {

    @FXML
    private AnchorPane imagePane;
    @FXML
    private Label positonLabel;
    @FXML
    private Label candidateName3;
    @FXML
    private Label votesLabel1,votesLabel2,votesLabel3;
    @FXML
    private Label candidateName2;
    @FXML
    private Label candidateName1;

    private ObjectProperty<Position> position;

    @Inject
    CandidateService service;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        position = new SimpleObjectProperty<>();
        position.addListener((ObservableValue<? extends Position> observable, Position oldValue, Position newValue) -> {
            Candidate candidate1 = position.get().getCandidates().get(0);
            Candidate candidate2 = position.get().getCandidates().get(1);
            Candidate candidate3 = position.get().getCandidates().get(2);
            Task task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    int i = 0;
                    while (true) {
                        final int finalI = i;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("i is" + finalI );
                                service.refresh(candidate1);
                                service.refresh(candidate2);
                                service.refresh(candidate3);
                                votesLabel1.setText("Total Votes:" + candidate1.getVoters().size());
                                votesLabel2.setText("Total Votes:" + candidate2.getVoters().size());
                                votesLabel3.setText("Total Votes:" + candidate3.getVoters().size());
                                votesLabel1.setVisible(true);
                                votesLabel2.setVisible(true);
                                votesLabel3.setVisible(true);
                                candidateName1.setVisible(true);
                                candidateName2.setVisible(true);
                                candidateName3.setVisible(true);
                            }
                        });
                        i++;
                        Thread.sleep(10000);
                    }
                }
            };
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        });
    }

    /**
     * @return the position
     */
    public ObjectProperty<Position> getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(ObjectProperty<Position> position) {
        this.position = position;
    }
    
    
    public void setPosition(Position position){
        this.position.set(position);
    }

    /**
     * @return the positonLabel
     */
    public Label getPositonLabel() {
        return positonLabel;
    }
}
