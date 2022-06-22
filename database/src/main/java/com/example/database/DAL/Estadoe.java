package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Estadoe.findAll", query = "SELECT ee FROM Estadoe ee"),
        @NamedQuery(name = "Estadoe.findByIde", query = "SELECT ee FROM Estadoe ee WHERE ee.ide = :ide")})
public class Estadoe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ee_seq")
    @SequenceGenerator(name = "ee_seq",
            sequenceName = "ee_seq", allocationSize = 1)
    @Column(name = "IDE")
    private Integer ide;
    @Basic
    @Column(name = "DESCRICAOE")
    private String descricaoe;
    @OneToMany(mappedBy = "estadoeByIde")
    private Collection<Estadoencomenda> estadoencomendasByIde;

    public Integer getIde() {
        return ide;
    }

    public void setIde(Integer ide) {
        this.ide = ide;
    }

    public String getDescricaoe() {
        return descricaoe;
    }

    public void setDescricaoe(String descricaoe) {
        this.descricaoe = descricaoe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estadoe estadoe = (Estadoe) o;
        return Objects.equals(ide, estadoe.ide) && Objects.equals(descricaoe, estadoe.descricaoe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ide, descricaoe);
    }

    public Collection<Estadoencomenda> getEstadoencomendasByIde() {
        return estadoencomendasByIde;
    }

    public void setEstadoencomendasByIde(Collection<Estadoencomenda> estadoencomendasByIde) {
        this.estadoencomendasByIde = estadoencomendasByIde;
    }

    @Override
    public String toString() {
        return descricaoe;
    }
}
