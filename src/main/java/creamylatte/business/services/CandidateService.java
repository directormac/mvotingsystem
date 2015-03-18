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
import creamylatte.business.models.Party;
import creamylatte.business.models.Position;
import creamylatte.business.models.Voter;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Hadouken
 */
public class CandidateService {
    @Inject
    private DBService service;
    
    
    public Candidate findCandidate(Integer id){
        return service.getManager().find(Candidate.class, id);
    }
    
    
    public void refresh(Party party){
        service.getManager().refresh(party);
    }
    
    public void refresh(Position position){
        service.getManager().refresh(position);
    }
    
    
    public void refresh(Candidate candidate){
        service.getManager().refresh(candidate);
    }
    
    public void remove(Candidate candidate){
        service.getTransaction().begin();
        service.getManager().remove(candidate);
        service.getTransaction().commit();
    }
    
    public List<Party> getAllParty(){
        return service.getManager().createNamedQuery("Party.findAll").getResultList();
    }
    
    
    public List<Position> getAllPositions(){
        return service.getManager().createNamedQuery("Position.findAll").getResultList();
    }
    
    public List<Voter> getAllVoter(){
        return service.getManager().createNamedQuery("Voter.findAll").getResultList();
    }
    
    public List<Voter> searchByYearLevel(String yearLevel){
        return service.getManager().createNamedQuery("Voter.findByYearLevel").setParameter("yearLevel", yearLevel).getResultList();
    }
    
//    public List<Voter> searchByPartyList(String gradeLevel){
//        return service.getManager().createNamedQuery("Voter.findByGradeLevel").setParameter("gradeLevel", gradeLevel).getResultList();
//    }
    
    public List<Candidate> getAllCandidates(){
        return service.getManager().createNamedQuery("Candidate.findAll").getResultList();
    }
    
    public void save(Party party){
     service.getTransaction().begin();
     Party merge = service.getManager().merge(party);
     service.getTransaction().commit();
    }
    
    public void save(Candidate candidate){
        service.getTransaction().begin();
        Candidate merge = service.getManager().merge(candidate);
        service.getTransaction().commit();
    }
    
    public void removeParty(Party party){
        service.getTransaction().begin();
        service.getManager().remove(party);
        service.getTransaction().commit();
    }
    
    
}
