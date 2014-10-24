/*
 * Computerized Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */

package creamylatte.presentation.admin.reports.useraccounts;

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
import creamylatte.business.models.Voter;
import creamylatte.business.services.VoterService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
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
public class UserAcountsPresenter implements Initializable {
    @FXML
    private TableView<Voter> voterTableView;
    @FXML
    private TableColumn<Voter, String> gradeLevelColumn;
    @FXML
    private TableColumn<Voter, String> nameColumn;
    @FXML
    private TableColumn<Voter, String> passwordColumn;
    @FXML
    private Button saveAsPDFButton;

    ObservableList<Voter> masterData;
    
    @Inject
    VoterService service;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        masterData = FXCollections.observableArrayList(service.all());
        voterTableView.setItems(masterData);
        nameColumn.setCellValueFactory((cellData) -> cellData.getValue().lastNameProperty()
                .concat(" , ").concat(cellData.getValue().firstNameProperty()));
        gradeLevelColumn.setCellValueFactory((cellData) -> cellData.getValue().gradeLEvelProperty());
        passwordColumn.setCellValueFactory((cellData) -> cellData.getValue().getAccount().passwordProperty());
        
    }    

    @FXML
    private void userReportsButtonAction(ActionEvent event) throws DocumentException, FileNotFoundException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, 
                new FileOutputStream("Accounts.pdf"));
        document.open();
        Paragraph title1 = new Paragraph("User Accounts",
                FontFactory.getFont(FontFactory.HELVETICA,
                        18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));
        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);
        Paragraph title11 = new Paragraph
                ("Voters login information",
                FontFactory.getFont(FontFactory.HELVETICA,16,Font.BOLD,
                        new CMYKColor(0, 255, 255, 17)));
        Section section = chapter1.addSection(title11);
        PdfPTable t = new PdfPTable(3);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);
        PdfPCell c1 = new PdfPCell(new Phrase("Grade Level"));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Name"));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("Password"));
        t.addCell(c3);
        for(Voter voter: masterData){
            t.addCell(voter.getGradeLevel());
            t.addCell(voter.getLastName() + " , " + voter.getFirstName());
            t.addCell(voter.getAccount().getPassword());
        }
        section.add(t);
        document.add(chapter1);
        document.close();
        
    }
    
}
