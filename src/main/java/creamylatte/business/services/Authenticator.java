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
import javax.persistence.NoResultException;


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
         return service.getManager().createNamedQuery("UserAccount.findByUsername")
                 .setParameter("username",  search + "%").getResultList();
    }
    
    
    public UserAccount checkCredentials(String username, String password){
        try{
            return (UserAccount)service.getManager().createNamedQuery("UserAccount.CheckCredentials")
                .setParameter("username", username).setParameter("password", password).getSingleResult();
        }catch(NoResultException e){
            System.out.println("Something went wrong");
            return null;
        }
    }
    
    
}
