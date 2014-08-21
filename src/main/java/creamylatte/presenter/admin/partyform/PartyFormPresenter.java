/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presenter.admin.partyform;

import creamylatte.business.models.Candidate;
import creamylatte.business.services.CandidateService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class PartyFormPresenter implements Initializable {
    @FXML
    private ComboBox<Candidate> presidentSearchBox,vicePresSearchBox,secretarySearchBox,
            treasurerSearchBox,pioSearchBox,auditorSearchBox,bus1SearchBox,bus2SearchBox,sg1SearchBox,sg2SearchBox,
            g7SearchBox,g8SearchBox;
    @FXML
    private ImageView presidentImageView,vicepImageView,secretaryImageView,pioImageView,auditorImageView,
            bus1ImageView,bus2ImageView,sg1ImageView,sg2ImageView,g7ImageView,g8ImageView,treasurerImageView;    
    @FXML
    private Button saveButton;
    
    ObservableList<Candidate> candidatesList;
    
    @Inject
    CandidateService service;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        candidatesList = FXCollections.observableArrayList();
        presidentSearchBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Candidate>() {
            @Override
            public void changed(ObservableValue<? extends Candidate> observable, Candidate oldValue, Candidate newValue) {
                if(newValue != null)
                presidentImageView.setImage(loadImage(newValue.getImage().getData()));
            }
        });
                
    }    
    
    private WritableImage loadImage(byte[] imageInByte){
        InputStream inputStream;
        BufferedImage bufferedImage;
        WritableImage writableImage;        
        try {
            inputStream = new ByteArrayInputStream(imageInByte);
            bufferedImage = ImageIO.read(inputStream);
            writableImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pw = writableImage.getPixelWriter();
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    pw.setArgb(x, y, bufferedImage.getRGB(x, y));
                }
            }
            return writableImage;
        }catch(IOException e){
            return null;
        }
    }
    
    
    @FXML
    private void savePartyList(ActionEvent event) {
        
        
    }
    
    @FXML
    private void presidentList(Event event) { 
       presidentSearchBox.setItems(FXCollections.observableArrayList(service.searchByGradeLevel("Ten")));       
    }    
    
    @FXML
    private void vicePresList(Event event) { 
       vicePresSearchBox.setItems(FXCollections.observableArrayList(service.searchByGradeLevel("Nine")));       
    }
    
    @FXML
    private void all(Event event){
        ComboBox<Candidate> current = (ComboBox)event.getSource();
        current.setItems(FXCollections.observableArrayList(service.all()));
    }
    
    private void findByName(){
        
    }
    
    private ChangeListener<Candidate> comboBoxListener(ComboBox cb){
        return (final ObservableValue<? extends Candidate> observableValue, Candidate oldValue, Candidate newValue) -> {
            if(newValue != null){
                
            }
        };
    }
}
