/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.business.services;


import creamylatte.business.models.Candidate;
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
        return service.getManager().createNamedQuery("Voter.SearchByName")
                .setParameter("firstName", "%" + search + "%")
                .setParameter("lastName", "%" + search + "%").getResultList();
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
    
    public void remove(Candidate candidate){
        service.getTransaction().begin();
        service.getManager().remove(candidate);
        service.getTransaction().commit();
    }
    
    
}
