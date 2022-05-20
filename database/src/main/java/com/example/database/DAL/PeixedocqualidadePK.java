package com.example.database.DAL;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PeixedocqualidadePK implements Serializable {
    @Column(name = "CODPEIXE")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codpeixe;
    @Column(name = "CODDOCQUALIDADE")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coddocqualidade;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeixedocqualidadePK that = (PeixedocqualidadePK) o;
        return Objects.equals(codpeixe, that.codpeixe) && Objects.equals(coddocqualidade, that.coddocqualidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codpeixe, coddocqualidade);
    }
}
