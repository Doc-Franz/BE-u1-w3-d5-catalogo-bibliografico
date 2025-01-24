package org.example.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "articolo_bibliografico")

public abstract class ArticoloBibliografico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String isbn;
    @Column(nullable = false)
    private String titolo;
    private int annoDiPubblicazione;
    private int numeroPagine;

    @OneToMany(mappedBy = "elementoPrestato") // lo stesso articolo può essere soggetto a diversi prestiti
    private List<Prestito> listaPrestiti;

    public ArticoloBibliografico() {};

    public ArticoloBibliografico(String isbn, String titolo, int annoDiPubblicazione, int numeroPagine){
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
        this.listaPrestiti = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Prestito> getListaPrestiti() {
        return listaPrestiti;
    }

    public void setListaPrestiti(List<Prestito> listaPrestiti) {
        this.listaPrestiti = listaPrestiti;
    }

    @Override
    public String toString() {
        return "ArticoloBibliografico{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", listaPrestiti=" + listaPrestiti +
                '}';
    }
}
