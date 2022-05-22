package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Peixe.findAll", query = "SELECT p FROM Peixe p"),
        @NamedQuery(name = "Peixe.findByCodpeixe", query = "SELECT p FROM Peixe p WHERE p.codpeixe = :codpeixe"),
        @NamedQuery(name = "Peixe.findByNomepeixe", query = "SELECT p FROM Peixe p WHERE p.nome LIKE :nome")})
public class Peixe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peixe_seq")
    @SequenceGenerator(name = "peixe_seq",
    sequenceName = "peixe_seq", allocationSize = 1)
    @Column(name = "CODPEIXE")
    private Integer codpeixe;
    @Basic
    @Column(name = "NOME")
    private String nome;
    @Basic
    @Column(name = "STOCK")
    private int stock;
    @OneToMany(mappedBy = "peixeByCodpeixe")
    private Collection<Peixecomprapeixe> peixecomprapeixesByCodpeixe;
    @OneToMany(mappedBy = "peixeByCodpeixe")
    private Collection<Peixedocqualidade> peixedocqualidadesByCodpeixe;
    @OneToMany(mappedBy = "peixeByCodpeixe")
    private Collection<Peixerequisicao> peixerequisicaosByCodpeixe;
    @OneToMany(mappedBy = "peixeByCodpeixe")
    private Collection<Tipoconserva> tipoconservasByCodpeixe;

    public Integer getCodpeixe() {
        return codpeixe;
    }

    public void setCodpeixe(Integer codpeixe) {
        this.codpeixe = codpeixe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peixe peixe = (Peixe) o;
        return stock == peixe.stock && Objects.equals(codpeixe, peixe.codpeixe) && Objects.equals(nome, peixe.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codpeixe, nome, stock);
    }

    public Collection<Peixecomprapeixe> getPeixecomprapeixesByCodpeixe() {
        return peixecomprapeixesByCodpeixe;
    }

    public void setPeixecomprapeixesByCodpeixe(Collection<Peixecomprapeixe> peixecomprapeixesByCodpeixe) {
        this.peixecomprapeixesByCodpeixe = peixecomprapeixesByCodpeixe;
    }

    public Collection<Peixedocqualidade> getPeixedocqualidadesByCodpeixe() {
        return peixedocqualidadesByCodpeixe;
    }

    public void setPeixedocqualidadesByCodpeixe(Collection<Peixedocqualidade> peixedocqualidadesByCodpeixe) {
        this.peixedocqualidadesByCodpeixe = peixedocqualidadesByCodpeixe;
    }

    public Collection<Peixerequisicao> getPeixerequisicaosByCodpeixe() {
        return peixerequisicaosByCodpeixe;
    }

    public void setPeixerequisicaosByCodpeixe(Collection<Peixerequisicao> peixerequisicaosByCodpeixe) {
        this.peixerequisicaosByCodpeixe = peixerequisicaosByCodpeixe;
    }

    public Collection<Tipoconserva> getTipoconservasByCodpeixe() {
        return tipoconservasByCodpeixe;
    }

    public void setTipoconservasByCodpeixe(Collection<Tipoconserva> tipoconservasByCodpeixe) {
        this.tipoconservasByCodpeixe = tipoconservasByCodpeixe;
    }
}
