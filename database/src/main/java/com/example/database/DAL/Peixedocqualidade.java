package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Peixedocqualidade.findAll", query = "SELECT pd FROM Peixedocqualidade pd"),
        @NamedQuery(name = "Peixedocqualidade.findByPk", query = "SELECT pd FROM Peixedocqualidade pd WHERE pd.codpeixe = :codpeixe AND pd.coddocqualidade = :coddocqualidade"),
        @NamedQuery(name = "Peixedocqualidade.findByCoddocqld", query = "SELECT pd FROM Peixedocqualidade pd WHERE pd.coddocqualidade = :coddocqualidade"),
        @NamedQuery(name = "Peixedocqualidade.findByCodpeixe", query = "SELECT pd FROM Peixedocqualidade pd WHERE pd.codpeixe = :codpeixe")})
@IdClass(PeixedocqualidadePK.class)
public class Peixedocqualidade {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODPEIXE")
    private Integer codpeixe;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODDOCQUALIDADE")
    private Integer coddocqualidade;
    @Basic
    @Column(name = "QTD")
    private int qtd;
    @ManyToOne
    @JoinColumn(name = "CODPEIXE", referencedColumnName = "CODPEIXE", insertable = false, nullable = false, updatable = false)
    private Peixe peixeByCodpeixe;
    @ManyToOne
    @JoinColumn(name = "CODDOCQUALIDADE", referencedColumnName = "CODDOCQUALIDADE", insertable = false, nullable = false, updatable = false)
    private Docqualidade docqualidadeByCoddocqualidade;

    public Integer getCodpeixe() {
        return codpeixe;
    }

    public void setCodpeixe(Integer codpeixe) {
        this.codpeixe = codpeixe;
    }

    public Integer getCoddocqualidade() {
        return coddocqualidade;
    }

    public void setCoddocqualidade(Integer coddocqualidade) {
        this.coddocqualidade = coddocqualidade;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peixedocqualidade that = (Peixedocqualidade) o;
        return qtd == that.qtd && Objects.equals(codpeixe, that.codpeixe) && Objects.equals(coddocqualidade, that.coddocqualidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codpeixe, coddocqualidade, qtd);
    }

    public Peixe getPeixeByCodpeixe() {
        return peixeByCodpeixe;
    }

    public void setPeixeByCodpeixe(Peixe peixeByCodpeixe) {
        this.peixeByCodpeixe = peixeByCodpeixe;
    }

    public Docqualidade getDocqualidadeByCoddocqualidade() {
        return docqualidadeByCoddocqualidade;
    }

    public void setDocqualidadeByCoddocqualidade(Docqualidade docqualidadeByCoddocqualidade) {
        this.docqualidadeByCoddocqualidade = docqualidadeByCoddocqualidade;
    }
}
