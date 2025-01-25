package org.example.entities;


import jakarta.persistence.Entity;

@Entity

public class Libro extends ArticoloBibliografico {

    private String autore;
    private String genere;

    public Libro() {}

    public String getAutore() {
        return autore;
    }

    public Libro(String isbn, String titolo, int annoDiPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoDiPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titolo='" + getTitolo() + '\'' +
                "anno di pubblicazione='" + getAnnoDiPubblicazione() + '\'' +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }
}
