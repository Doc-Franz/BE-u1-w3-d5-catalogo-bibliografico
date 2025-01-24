package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "articoli_bibliografici")

public class ArticoloBibliografico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static long id;
    @Column(unique = true)
    private String isbn;
    private String titolo;
    private int annoDiPubblicazione;
    private int numeroPagine;

    public ArticoloBibliografico() {};

    public ArticoloBibliografico(String isbn, String titolo, int annoDiPubblicazione, int numeroPagine){
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        ArticoloBibliografico.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "ArticoloBibliografico{" +
                "isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
