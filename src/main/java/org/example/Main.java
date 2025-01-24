package org.example;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.Archivio;
import org.example.entities.Libro;

public class Main
{
    private static Faker faker = new Faker();
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionecatalogo");
    private static EntityManager em = emf.createEntityManager();

    public static void main( String[] args ) {

        Archivio archivio = new Archivio(em);
        Libro l1 = new Libro(faker.code().isbn10(), faker.book().title(), faker.number().numberBetween(1960, 2025), faker.number().numberBetween(30, 900), faker.book().author(), faker.book().genre());

    }
}
