package org.example;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.example.dao.Archivio;
import org.example.dao.PrestitoDAO;
import org.example.dao.UtenteDAO;
import org.example.entities.*;
import org.example.enumerations.Periodicità;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Main
{
    private static Faker faker = new Faker();
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionecatalogo");
    private static EntityManager em = emf.createEntityManager();

    public static Archivio archivio = new Archivio(em);
    public static UtenteDAO utenteDAO = new UtenteDAO(em);
    public static PrestitoDAO prestitoDAO = new PrestitoDAO(em);

    public static List<Prestito> listaPrestiti;

    public static void main( String[] args ) {

    // Aggiungi elementi al catalogo
    //addLibro();
    //addRivista();

    // Aggiungi utenti
    //addUtente();

    // Aggiungi prestiti
    //addPrestito(2, 4);
    //addPrestito(4, 1);
    //addPrestito(3, 7);
        // addPrestito(2, 2);

    // Rimuovi elementi per ID
    //archivio.removeElement(8);

    // Ricerca elementi in archivio per ID
        ArticoloBibliografico articoloId = findByISBN("0873326806");
        System.out.println(articoloId);

        // Ricerca elementi in archivio per anno di pubblicazione
        System.out.println("");
        System.out.println("Ricerca per anno di pubblicazione");
        List<ArticoloBibliografico> listaArticoloAnnoPubblicazione = findByAnnoDiPubblicazione(2022);
        System.out.println(listaArticoloAnnoPubblicazione);

        // Ricerca elementi in archivio per autore
        System.out.println("");
        System.out.println("Ricerca per autore");
        List<ArticoloBibliografico> listaArticoliAutore = findByAutore("Lissa Kshlerin");
        System.out.println(listaArticoliAutore);

        // Ricerca elementi in archivio per titolo
        System.out.println("");
        System.out.println("Ricerca per titolo");
        List<ArticoloBibliografico> articoloTitolo = findByTitolo("The");
        System.out.println(articoloTitolo);

        // Ricerca prestiti per numero di tessera utente
        System.out.println("");
        System.out.println("Ricerca per numero di tessera");
        List<Prestito> listaPrestitiUtente = findByNumeroTessera(2);
        System.out.println(listaPrestitiUtente);

        // Ricerca prestiti scaduti
        System.out.println("");
        System.out.println("Prestiti scaduti");
        List<Prestito> listaPrestitiScaduti = findPrestitiScaduti();
        System.out.println(listaPrestitiScaduti);

    }

    // funzione che aggiunge libri al catalogo
    public static void addLibro() {
        for (int i = 0; i < 5; i++){
            archivio.addElement(new Libro(faker.code().isbn10(),
                    faker.book().title(),
                    faker.number().numberBetween(1960, 2025),
                    faker.number().numberBetween(30, 900),
                    faker.book().author(),
                    faker.book().genre()));
        }

    }

    // funzione che aggiunge riviste al catalogo
    public static void addRivista() {
        for (int i = 0; i < 5; i++){
            archivio.addElement(new Rivista(faker.code().isbn10(),
                    faker.book().title(),
                    faker.number().numberBetween(1960, 2025),
                    faker.number().numberBetween(30, 900),
                    faker.options().option(Periodicità.values())));
        }
    }

    // funzione che aggiunge utenti
    public static void addUtente() {
        for (int i = 0; i < 5; i++){
            utenteDAO.addElement(new Utente(faker.name().firstName(),
                    faker.name().lastName(),
                    faker.date().birthday().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()));
        };
    }

    // funzione che aggiunge un nuovo prestito
    public static void addPrestito(int numeroTessera, int idArticolo){
        Prestito prestito = new Prestito(utenteDAO.getById(numeroTessera), archivio.getById(idArticolo));
        prestito.setDataRestituzioneEffettiva();
        prestito.setStatoRestituzione();
        prestitoDAO.addElement(prestito);
    }

    // funzione che ricerca un elemento dall'archivio per ID
    public static ArticoloBibliografico findByISBN(String isbn){
        Query q = em.createQuery("SELECT a FROM ArticoloBibliografico a WHERE a.isbn = :isbn");
        q.setParameter("isbn", isbn);
        return (ArticoloBibliografico) q.getSingleResult();
    }

    // funzione che ricerca un elemento dall'archivio per anno di pubblicazione
    public static List<ArticoloBibliografico> findByAnnoDiPubblicazione(int annoDiPubblicazione){
        Query q = em.createQuery("SELECT a FROM ArticoloBibliografico a WHERE a.annoDiPubblicazione = :annoDiPubblicazione");
        q.setParameter("annoDiPubblicazione", annoDiPubblicazione);
        return q.getResultList();
    }

    // funzione che ricerca un elemento dall'archivio per autore
    public static List<ArticoloBibliografico> findByAutore(String autore){
        Query q = em.createQuery("SELECT a FROM ArticoloBibliografico a WHERE a.autore = :autore");
        q.setParameter("autore", autore);
        return q.getResultList();
    }

    // funzione che ricerca un elemento dall'archivio per titolo o parte di esso
    public static List<ArticoloBibliografico> findByTitolo(String titolo){
        Query q = em.createQuery("SELECT a FROM ArticoloBibliografico a WHERE a.titolo LIKE :titolo");
        q.setParameter("titolo", titolo + "%");
        return q.getResultList();
    }

    // funzione che ricerca gli elementi in prestito dato un numero di tessera utente
    public static List<Prestito> findByNumeroTessera(int numeroTessera){
        Query q = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera");
        q.setParameter("numeroTessera", numeroTessera);
        return q.getResultList();
    }

    // funzione che ricerca i prestiti scaduti
    public static List<Prestito> findPrestitiScaduti(){
        Query q = em.createQuery("SELECT p FROM Prestito p Where p.articoloRestituito = :articoloRestituito");
        q.setParameter("articoloRestituito", false);
        return q.getResultList();
    }

}
