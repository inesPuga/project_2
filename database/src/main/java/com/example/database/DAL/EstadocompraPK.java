package com.example.database.DAL;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EstadocompraPK implements Serializable {
    @Column(name = "IDC")
    @Id
    private Integer idc;
    @Column(name = "CODCOMPRA")
    @Id
    private Integer codcompra;

    public Integer getIdc() {
        return idc;
    }

    public void setIdc(Integer idc) {
        this.idc = idc;
    }

    public Integer getCodcompra() {
        return codcompra;
    }

    public void setCodcompra(Integer codcompra) {
        this.codcompra = codcompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadocompraPK that = (EstadocompraPK) o;
        return Objects.equals(idc, that.idc) && Objects.equals(codcompra, that.codcompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idc, codcompra);
    }
}
