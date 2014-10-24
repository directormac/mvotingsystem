/*
 * Computerized Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */
package creamylatte.presentation.admin.reports.results;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import creamylatte.business.models.Candidate;
import creamylatte.business.services.CandidateService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ResultsPresenter implements Initializable {

    @FXML
    private TableView<Candidate> candidateTableView;
    @FXML
    private TableColumn<Candidate, String> partyColumn;
    @FXML
    private TableColumn<Candidate, String> positionColumn;
    @FXML
    private TableColumn<Candidate, String> nameColumn;
    @FXML
    private TableColumn<Candidate, String> votesColumn;
    @FXML
    private Button saveReportButton;

    ObservableList<Candidate> masterData;

    @Inject
    CandidateService service;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        masterData = FXCollections.observableArrayList(service.getAllCandidates());        
        candidateTableView.setItems(masterData);
        partyColumn.setCellValueFactory((cellData)-> cellData.getValue().partyNameProperty());
        positionColumn.setCellValueFactory((cellData) -> cellData.getValue().getPosition().nameProperty());
        nameColumn.setCellValueFactory((cellData) -> cellData.getValue().getVoterId().lastNameProperty()
                .concat(" ").concat(cellData.getValue().getVoterId().firstNameProperty()));
        votesColumn.setCellValueFactory((cellData) -> new SimpleStringProperty(cellData.getValue().getVoters().size()+ ""));
    }

    @FXML
    private void saveReportButtonAction(ActionEvent event) throws DocumentException, FileNotFoundException{
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, 
                new FileOutputStream("Results.pdf"));
        document.open();
        Paragraph title1 = new Paragraph("Election Results",
                FontFactory.getFont(FontFactory.HELVETICA,
                        18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);
        Paragraph title11 = new Paragraph
                ("This table represents the result of the election",
                FontFactory.getFont(FontFactory.HELVETICA,16,Font.BOLD,
                        new CMYKColor(0, 255, 255, 17)));
        Section section = chapter1.addSection(title11);
        PdfPTable t = new PdfPTable(4);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);
        PdfPCell c1 = new PdfPCell(new Phrase("Party"));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Position"));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("Name"));
        t.addCell(c3);
        PdfPCell c4 = new PdfPCell(new Phrase("Votes"));
        t.addCell(c4);
        for(Candidate candidate: masterData){
            t.addCell(candidate.partyNameProperty() + "");
            t.addCell(candidate.getPosition().getName() + "");
            t.addCell(candidate.getVoterId().getLastName() 
                    + " , " + candidate.getVoterId().getFirstName());
            t.addCell(candidate.getVoters().size() + "");
        }
        section.add(t);
        document.add(chapter1);
        document.close();
        
    }

}
