package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.entities.ArticoloBibliografico;
import org.example.entities.Utente;

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

    public ArticoloBibliografico getById(int id){
        return em.find(ArticoloBibliografico.class, id);
    }

    public void removeElement(int id){
        em.getTransaction().begin();
        em.remove(getById(id));
        em.getTransaction().commit();
    }
}
