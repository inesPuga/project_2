package com.example.database.DAL;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ValornutricionalPK implements Serializable {
    @Column(name = "IDNUTRIENTE")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idnutriente;
    @Column(name = "CODTIPOCONSERVA")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codtipoconserva;

    public Integer getIdnutriente() {
        return idnutriente;
    }

    public void setIdnutriente(Integer idnutriente) {
        this.idnutriente = idnutriente;
    }

    public Integer getCodtipoconserva() {
        return codtipoconserva;
    }

    public void setCodtipoconserva(Integer codtipoconserva) {
        this.codtipoconserva = codtipoconserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValornutricionalPK that = (ValornutricionalPK) o;
        return Objects.equals(idnutriente, that.idnutriente) && Objects.equals(codtipoconserva, that.codtipoconserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idnutriente, codtipoconserva);
    }
}
