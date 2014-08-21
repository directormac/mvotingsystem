/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.presenter.admin.voter;

import creamylatte.business.models.Candidate;
import creamylatte.business.models.ImageWrapper;
import creamylatte.business.models.UserAccount;
import creamylatte.business.services.CandidateService;
import creamylatte.presenter.admin.voterform.VoterFormPresenter;
import creamylatte.presenter.admin.voterform.VoterFormView;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class VoterPresenter implements Initializable {
    
    @FXML
    private Button searchButton,deleteButton,editButton;    
    @FXML
    private TextField searchField;    
    @FXML
    private AnchorPane currentPane;
    @FXML
    private StackPane stackPane;    
    private TableView<Candidate> voterTable;  
    
    ProgressIndicator progressIndicator;    
    Region veil;    
    Task<ObservableList<Candidate>> task;
    ObservableList<Candidate> candidates;
    ObjectProperty<Candidate> selectedCandidate;    
    VoterFormPresenter voterFormPresenter;
    VoterFormView voterFormView;
    StringProperty label;
    @Inject
    CandidateService service;
    
    
    @FXML
    private Button addVoter;
    
    
    @FXML
    private PieChart voterChart;
    @FXML
    private AnchorPane dynamicPane;
    @FXML
    private Label dyanmicLabel;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private ComboBox<String> gradeLevelCBox;
    @FXML
    private Button saveButton,iSelectButton;
    
    File file;
    BufferedImage bufferedImage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.label = new SimpleStringProperty();
        this.candidates = FXCollections.observableArrayList();
        this.selectedCandidate = new SimpleObjectProperty<>(new Candidate());              
        prepareTable();        
        prepareChart();
        loadAllVoters();    
        rebindProperty();           
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(newValue.isEmpty()){
                loadAllVoters(); 
            }
        });        
        gradeLevelCBox.setItems(FXCollections.observableArrayList("Seven","Eight","Nine","Ten"));
        this.firstNameField.textProperty().addListener(textFieldListeners(this.firstNameField));        
        this.lastNameField.textProperty().addListener(textFieldListeners(this.lastNameField)); 
        
    }   

    private void prepareChart(){
         ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                         new PieChart.Data("Grade 7", service.searchByGradeLevel("Seven").size()),
                         new PieChart.Data("Grade 8", service.searchByGradeLevel("Eight").size()),
                         new PieChart.Data("Grade 9", service.searchByGradeLevel("Nine").size()),
                         new PieChart.Data("Grade 10", service.searchByGradeLevel("Ten").size())
                         );         
         voterChart.setData(pieChartData);
    }
    
    private void rebindProperty(){
        deleteButton.disableProperty().bind(voterTable.getSelectionModel().selectedItemProperty().isNull()); 
        editButton.disableProperty().bind(voterTable.getSelectionModel().selectedItemProperty().isNull()); 
        searchButton.disableProperty().bind(searchField.textProperty().isEmpty());
//        firstNameField.disableProperty().bind(voterTable.getSelectionModel().selectedItemProperty().isNull());
//        lastNameField.disableProperty().bind(voterTable.getSelectionModel().selectedItemProperty().isNull());
//        gradeLevelCBox.disableProperty().bind(voterTable.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void searchVoter(ActionEvent event) {        
        loadAllVoters();
        rebindProperty();
    }
    
    @FXML
    private void deleteVoter(ActionEvent event) {
        if(voterTable.getSelectionModel().getSelectedItem() != null){
            service.remove(voterTable.getSelectionModel().getSelectedItem());
            stackPane.getChildren().clear();
            loadAllVoters();
            prepareChart();
            voterTable.getSelectionModel().clearSelection();
        }
        
    }
    
    @FXML
    private void editVoter(ActionEvent event) {        
        if(voterTable.getSelectionModel().getSelectedItem() != null){
            firstNameField.setText(this.voterTable.getSelectionModel().getSelectedItem().getFirstName());
            lastNameField.setText(this.voterTable.getSelectionModel().getSelectedItem().getLastName());
            if(!this.voterTable.getSelectionModel().getSelectedItem().getGradeLevel().equals(""))
                gradeLevelCBox.getSelectionModel().select(this.voterTable.getSelectionModel().getSelectedItem().getGradeLevel());
            enableForm();
        }
    }
    
    @FXML
    private void selectPhoto(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png)(*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(iSelectButton.getScene().getWindow());

    }
    
        @FXML
    private void saveVoter(ActionEvent event) {
        Candidate c;
        if(this.voterTable.getSelectionModel().getSelectedItem() == null){
            c = new Candidate();
        }else{
            c = voterTable.getSelectionModel().getSelectedItem();
        }        
        c.setFirstName(firstNameField.textProperty().get().toLowerCase());
        c.setLastName(lastNameField.textProperty().get().toLowerCase());
        c.setGradeLevel(gradeLevelCBox.getSelectionModel().getSelectedItem());
        UserAccount ua;
        if(c.getAccount() == null){
            ua = new UserAccount();
            ua.setUsername(c.getFirstName()+","+c.getLastName());
            ua.setPassword("" + (int)(Math.random() * 1001));
            System.out.println(ua.getPassword());
            c.setAccount(ua);
        }
        
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(VoterPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException ex) {
            Logger.getLogger(VoterPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageWrapper image = new ImageWrapper();            
        image.setImageName(c.getFirstName()+c.getLastName() + ".jpg");
        image.setData(baos.toByteArray()); 
        c.setImage(image);  
        service.save(c);
        loadAllVoters();
        prepareChart();
        disableForm();
    }
    
    
    
    
    //Prepare the table and columns
    private void prepareTable(){
        voterTable = new TableView<>();
        this.voterTable.setEditable(false);        
        ObservableList columns = voterTable.getColumns();
        final TableColumn firstNameColumn = createTextColumn("firstName", "First Name");
        columns.add(firstNameColumn);
        final TableColumn lastNameColumn = createTextColumn("lastName", "Last Name");
        columns.add(lastNameColumn);
        final TableColumn gradeLevelColumn = createTextColumn("gradeLevel", "Grade Level");
        columns.add(gradeLevelColumn);
        final TableColumn<Candidate, UserAccount> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
        passwordColumn.setCellFactory(new Callback<TableColumn<Candidate, UserAccount>, TableCell<Candidate, UserAccount>>() {
                @Override
                public TableCell<Candidate, UserAccount> call(TableColumn<Candidate, UserAccount> param) {
                    return new TableCell<Candidate, UserAccount>() {
                        @Override
                        protected void updateItem(UserAccount item, boolean empty) {
                            super.updateItem(item, empty);
                          if (!empty) {
                            // Use a SimpleDateFormat or similar in the format method
                            setText(item.getPassword());
                          } else {
                            setText(null);
                          }
                        }
                    };
                }
            });
        columns.add(passwordColumn);
        voterTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        voterTable.setItems(this.candidates);
        voterTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);        
    }
    
    //create text column
    private TableColumn createTextColumn(String name, String caption) {
        TableColumn column = new TableColumn(caption);
        column.setCellValueFactory(new PropertyValueFactory<>(name));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        return column;
    }
    
    //load all candidates from DB
    private void loadAllVoters() {
        stackPane.getChildren().clear();
        progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxSize(150, 150);
        veil = new Region();
        veil.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
        task = new Task<ObservableList<Candidate>>() {
            @Override
            protected ObservableList<Candidate> call() throws Exception {                
                    List<Candidate> votersList= service.all();
                if(!searchField.getText().isEmpty()){
                    votersList = service.search(searchField.textProperty().get());
                }                
                Thread.sleep(5);
                candidates = FXCollections.observableArrayList(votersList);                
                return candidates;
            }
        };
        progressIndicator.progressProperty().bind(task.progressProperty());
        veil.visibleProperty().bind(task.runningProperty());
        progressIndicator.visibleProperty().bind(task.runningProperty());
        voterTable.itemsProperty().bind(task.valueProperty()); 
        stackPane.getChildren().addAll(voterTable,veil,progressIndicator);
        new Thread(task).start();
    }

    @FXML
    private void addVoter(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        gradeLevelCBox.getSelectionModel().clearSelection();
        enableForm();
    }


    
    private void enableForm(){
        firstNameField.setVisible(true);
        lastNameField.setVisible(true);
        gradeLevelCBox.setVisible(true);
        saveButton.setVisible(true);
    }
    
    private void disableForm(){
        firstNameField.setVisible(false);
        lastNameField.setVisible(false);
        gradeLevelCBox.setVisible(false);
        saveButton.setVisible(false);
    }
    
    
    private String firstCharToUpperCase(String string){
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
    
     private ChangeListener<String> textFieldListeners(TextField tf){
        final Pattern wholeNumberPattern = Pattern.compile("[A-Za-zñÑ]*");
        return (final ObservableValue<? extends String> observableValue, final String oldValue, final String newValue) -> {
            if (!wholeNumberPattern.matcher(newValue).matches())
                tf.setText(oldValue);
        };
    }
}
