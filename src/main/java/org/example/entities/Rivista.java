package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.enumerations.Periodicità;

@Entity

public class Rivista extends ArticoloBibliografico {

    @Enumerated(EnumType.STRING)
    private Periodicità periodicità;

    public Rivista() {};

    public Rivista(String isbn, String titolo, int annoDiPubblicazione, int numeroPagine, Periodicità periodicità) {
        super(isbn, titolo, annoDiPubblicazione, numeroPagine);
        this.periodicità = periodicità;
    }

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "titolo='" + getTitolo() + '\'' +
                "anno di pubblicazione='" + getAnnoDiPubblicazione() + '\'' +
                "periodicità=" + periodicità +
                '}';
    }
}
