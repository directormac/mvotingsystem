/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.business.models;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javax.persistence.*;


/**
 *
 * @author Hadouken
 * This entity is using property access.
 * Entity for candidate table
 * Known relationships:
 * *OneToOne to UserAccount,ImageWrapper
 * **ManyToOne to Party, Position
 * ***ManyToMany to self where every candidate can also vote a candidate
 * 
 */
@Entity
@Table(name = "candidate")
@NamedQueries({
@NamedQuery(name = "Candidate.findAll", query = "SELECT c FROM Candidate c")
//        ,
//    @NamedQuery(name = "Candidate.findByGradeLevel", query = "SELECT c FROM Candidate c WHERE c. ")
})
public class Candidate implements Serializable {
    private IntegerProperty id;
    private ObjectProperty<Voter> voterId;
    private ObjectProperty<Position> position;
    private ObjectProperty<Party> partylist;
    private ImageWrapper image;
    private ListProperty<Voter> voters;
    
    //initiating properties
    public Candidate() {       
        this.id = new SimpleIntegerProperty();                
        this.voterId = new SimpleObjectProperty<>();
        this.position = new SimpleObjectProperty<>();
        this.partylist = new SimpleObjectProperty<>();
        this.voters = new SimpleListProperty<>();
    }
    
    public Candidate(Voter voterId ,Position position,Party party){
        this();
        this.voterId.set(voterId);
        this.position.set(position);
        this.partylist.set(party);
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voter_id")
    public Voter getVoterId(){
        return this.voterId.get();
    }
    
    public void setVoterId(Voter voterId){
        this.voterId.set(voterId);
    }
    
    
    @ManyToOne(optional = false, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "party", referencedColumnName = "id")
    public Party getParty(){
        return partylist.get();
    }    
    public void setParty(Party partyList){
        this.partylist.set(partyList);
    }
    

    @ManyToOne(optional = false, cascade=CascadeType.PERSIST,targetEntity=Position.class)
    @JoinColumn(name = "position", referencedColumnName = "id")
    public Position getPosition(){
        return position.get();
    }    
    public void setPosition(Position pos){
        position.set(pos);
    }
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="image")
    public ImageWrapper getImage(){
        return this.image;
    }    
    
    public void setImage(ImageWrapper image){
        this.image = image;
    }

    @ManyToMany(mappedBy = "candidates")
    public List<Voter> getVoters(){
        return this.voters.get();
    }
    
    public void setVoters(List<Voter> voters){
        this.voters.set(FXCollections.observableArrayList(voters));
    }


    
}