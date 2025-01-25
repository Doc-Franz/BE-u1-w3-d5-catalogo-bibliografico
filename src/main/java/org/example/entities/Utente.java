package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroTessera;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;


    @OneToMany(mappedBy = "utente") // un utente può avere accesso a diversi prestiti
    private List<Prestito> listaPrestiti;

    public Utente() {};

    public Utente(String nome, String cognome, LocalDate dataDiNascita){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.listaPrestiti = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(int numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public List<Prestito> getListaPrestiti() {
        return listaPrestiti;
    }

    public void setListaPrestiti(List<Prestito> listaPrestiti) {
        this.listaPrestiti = listaPrestiti;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", numeroTessera=" + numeroTessera +
                ", listaPrestiti=" + listaPrestiti +
                '}';
    }
}
