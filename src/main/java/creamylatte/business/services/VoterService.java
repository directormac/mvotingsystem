/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.business.services;


import creamylatte.business.models.Voter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;



/**
 *
 * @author Hadouken
 * This class handles all transaction for the Candidate Entity
 */
public class VoterService {
    
    @Inject
    private DBService service;

    
    public List<Voter> all(){
        return service.getManager().createNamedQuery("Voter.findAll").getResultList();
    }
    
    public List<Voter> search(String search){        
        List<Voter> searched = new ArrayList<>();
        List<Voter> firstNameList = service.getManager().createNamedQuery("Voter.findByFirstName")
                .setParameter("firstName","%" + search + "%").getResultList();
        List<Voter> lastNameList = service.getManager().createNamedQuery("Voter.findByLastName")
                .setParameter("lastName", "%" + search + "%").getResultList();
        firstNameList.stream().filter((voter) -> (!searched.contains(voter))).forEach((voter) -> {
            searched.add(voter);
        });
        lastNameList.stream().filter((voter) -> (!searched.contains(voter))).forEach((voter) -> {
            searched.add(voter);
        });        
        return searched;
    }
    
    
    
    public List<Voter> searchByGradeLevel(String gradeLevel){
        return service.getManager().createNamedQuery("Voter.findByGradeLevel").setParameter("gradeLevel", gradeLevel).getResultList();
    }
    
    
   
    public void save(Voter voter){
        service.getTransaction().begin();
        Voter merged = this.service.getManager().merge(voter);;
        service.getTransaction().commit();
    }
    
    public void remove(Voter voter){
        service.getTransaction().begin();
        service.getManager().remove(voter);
        service.getTransaction().commit();
    }
    
    
}
