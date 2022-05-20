package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Estadocompra.findAll", query = "SELECT ec FROM Estadocompra ec"),
        @NamedQuery(name = "Estadocompra.findByPk", query = "SELECT ec FROM Estadocompra ec WHERE ec.codcompra = :codcompra AND ec.idc = :idc"),
        @NamedQuery(name = "Estadocompra.findByCodcompra", query = "SELECT ec FROM Estadocompra ec WHERE ec.codcompra = :codcompra"),
        @NamedQuery(name = "Estadocompra.findByIdc", query = "SELECT ec FROM Estadocompra ec WHERE ec.idc = :idc")})
@IdClass(EstadocompraPK.class)
public class Estadocompra {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IDC")
    private Integer idc;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODCOMPRA")
    private Integer codcompra;
    @Basic
    @Column(name = "DTEC")
    private String dtec;
    @ManyToOne
    @JoinColumn(name = "IDC", referencedColumnName = "IDC", insertable = false, nullable = false, updatable = false)
    private Estadoc estadocByIdc;
    @ManyToOne
    @JoinColumn(name = "CODCOMPRA", referencedColumnName = "CODCOMPRA", insertable = false, nullable = false, updatable = false)
    private Comprapeixe comprapeixeByCodcompra;

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

    public String getDtec() {
        return dtec;
    }

    public void setDtec(String dtec) {
        this.dtec = dtec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estadocompra that = (Estadocompra) o;
        return Objects.equals(idc, that.idc) && Objects.equals(codcompra, that.codcompra) && Objects.equals(dtec, that.dtec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idc, codcompra, dtec);
    }

    public Estadoc getEstadocByIdc() {
        return estadocByIdc;
    }

    public void setEstadocByIdc(Estadoc estadocByIdc) {
        this.estadocByIdc = estadocByIdc;
    }

    public Comprapeixe getComprapeixeByCodcompra() {
        return comprapeixeByCodcompra;
    }

    public void setComprapeixeByCodcompra(Comprapeixe comprapeixeByCodcompra) {
        this.comprapeixeByCodcompra = comprapeixeByCodcompra;
    }
}
