package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entities.ArticoloBibliografico;

public class Archivio {

    private EntityManager em;

    public Archivio(EntityManager em){
        this.em = em;
    }

    public void addElement(ArticoloBibliografico a){
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }
}
