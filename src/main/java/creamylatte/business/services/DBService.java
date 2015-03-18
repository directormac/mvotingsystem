
package creamylatte.business.services;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class DBService {
    private EntityManager em;
    private EntityTransaction et;

    @PostConstruct
    public void init(){
        this.em = (EntityManager) Persistence.createEntityManagerFactory("VotingSystemPU").createEntityManager();
        this.et = em.getTransaction();
    }
    
    public EntityManager getManager(){
        return this.em;
    }
    
    public EntityTransaction getTransaction(){
        return this.et;
    }
}
