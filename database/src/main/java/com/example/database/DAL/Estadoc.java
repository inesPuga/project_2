package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Estadoc.findAll", query = "SELECT ec FROM Estadoc ec"),
        @NamedQuery(name = "Estadoc.findByIdc", query = "SELECT ec FROM Estadoc ec WHERE ec.idc = :idc")})
public class Estadoc {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estadoc_seq")
    @SequenceGenerator(name = "estadoc_seq",
            sequenceName = "estadoc_seq", allocationSize = 1)
    @Column(name = "IDC")
    private Integer idc;
    @Basic
    @Column(name = "DESCRICAOC")
    private String descricaoc;
    @OneToMany(mappedBy = "estadocByIdc")
    private Collection<Estadocompra> estadocomprasByIdc;

    public Integer getIdc() {
        return idc;
    }

    public void setIdc(Integer idc) {
        this.idc = idc;
    }

    public String getDescricaoc() {
        return descricaoc;
    }

    public void setDescricaoc(String descricaoc) {
        this.descricaoc = descricaoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estadoc estadoc = (Estadoc) o;
        return Objects.equals(idc, estadoc.idc) && Objects.equals(descricaoc, estadoc.descricaoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idc, descricaoc);
    }

    public Collection<Estadocompra> getEstadocomprasByIdc() {
        return estadocomprasByIdc;
    }

    public void setEstadocomprasByIdc(Collection<Estadocompra> estadocomprasByIdc) {
        this.estadocomprasByIdc = estadocomprasByIdc;
    }
}
