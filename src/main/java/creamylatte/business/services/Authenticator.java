/*
 * Voting System
 * Project By:  * 
 * Almiradz Mling  * 
 * Eduard John Madriaga  * 
 * Rodz Aguilar Piang  * 
 * Mark Kendrick Asena * 
 */


package creamylatte.business.services;


import creamylatte.business.models.UserAccount;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;


/**
 *
 * @author Hadouken
 * This class is used to Authenticate users
 */
public class Authenticator {
    
    //Injected to make sure there is only one instance
    @Inject
    DBService service;
    
    public List<UserAccount> findByUserName(String search){
         List<UserAccount> searched;
         Query query = service.getManager().createNamedQuery("UserAccount.findByUsername");
         query.setParameter("username", "%" + search + "%");
         searched = query.getResultList();
         return searched;
    }
    
    public UserAccount findUser(String username){
        UserAccount ua;
        Query query = service.getManager().createNamedQuery("UserAccount.findByUsername");
        query.setParameter("username", username);
        ua = (UserAccount)query.getSingleResult();
        return ua;
    }
}
