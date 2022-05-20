package com.example.database.DAL;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TipoconservaencomendaPK implements Serializable {
    @Column(name = "CODTIPOCONSERVA")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codtipoconserva;
    @Column(name = "CODENCOMENDA")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codencomenda;

    public Integer getCodtipoconserva() {
        return codtipoconserva;
    }

    public void setCodtipoconserva(Integer codtipoconserva) {
        this.codtipoconserva = codtipoconserva;
    }

    public Integer getCodencomenda() {
        return codencomenda;
    }

    public void setCodencomenda(Integer codencomenda) {
        this.codencomenda = codencomenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoconservaencomendaPK that = (TipoconservaencomendaPK) o;
        return Objects.equals(codtipoconserva, that.codtipoconserva) && Objects.equals(codencomenda, that.codencomenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codtipoconserva, codencomenda);
    }
}
