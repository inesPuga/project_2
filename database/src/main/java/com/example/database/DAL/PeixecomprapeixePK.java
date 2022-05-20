package com.example.database.DAL;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PeixecomprapeixePK implements Serializable {
    @Column(name = "CODCOMPRA")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codcompra;
    @Column(name = "CODPEIXE")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codpeixe;

    public Integer getCodcompra() {
        return codcompra;
    }

    public void setCodcompra(Integer codcompra) {
        this.codcompra = codcompra;
    }

    public Integer getCodpeixe() {
        return codpeixe;
    }

    public void setCodpeixe(Integer codpeixe) {
        this.codpeixe = codpeixe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeixecomprapeixePK that = (PeixecomprapeixePK) o;
        return Objects.equals(codcompra, that.codcompra) && Objects.equals(codpeixe, that.codpeixe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codcompra, codpeixe);
    }
}
