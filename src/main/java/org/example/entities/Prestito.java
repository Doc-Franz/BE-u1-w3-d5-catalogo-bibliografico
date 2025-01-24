package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestiti")

public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dataInizioPrestito;
    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzioneEffettiva;
    private String statoRestituzione;

    @ManyToOne // un utente può avere accesso a diversi prestiti
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @Column(unique = true) // il codice ISBN è univoco
    @ManyToOne // posso essere effettuati più prestiti dello stesso articolo
    @JoinColumn(name = "articolo_id")
    private ArticoloBibliografico elementoPrestato;

    public Prestito() {};

    public Prestito(Utente utente, ArticoloBibliografico elementoPrestato) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = LocalDate.now();
        this.dataRestituzionePrevista = LocalDate.now().plusDays(30);
        this.dataRestituzioneEffettiva = getDataRestituzioneEffettiva();
        this.statoRestituzione = getStatoRestituzione();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ArticoloBibliografico getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(ArticoloBibliografico elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = LocalDate.now().plusDays((long) (Math.ceil(Math.random() * 50))); // calcolo randomico della data di restituzione effettiva
    }

    public String getStatoRestituzione() {
        return statoRestituzione;
    }

    public void setStatoRestituzione(String statoRestituzione) {
        if (dataRestituzioneEffettiva.isAfter(dataRestituzionePrevista)) {
            this.statoRestituzione = "Articolo restituito in ritardo";
        } else {
            this.statoRestituzione = "Articolo restituito correttamente";
        }
    }
}
