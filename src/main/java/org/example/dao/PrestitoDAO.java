package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entities.Prestito;
import org.example.entities.Utente;

public class PrestitoDAO {

    private EntityManager em;

    public PrestitoDAO(EntityManager em){
        this.em = em;
    }

    public void addElement(Prestito p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
}
