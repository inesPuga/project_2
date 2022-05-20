package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Estadoencomenda.findAll", query = "SELECT ee FROM Estadoencomenda ee"),
        @NamedQuery(name = "Estadoencomenda.findByPk", query = "SELECT ee FROM Estadoencomenda ee WHERE ee.codencomenda = :codencomenda AND ee.ide = :ide"),
        @NamedQuery(name = "Estadoencomenda.findByCodencomenda", query = "SELECT ee FROM Estadoencomenda ee WHERE ee.codencomenda = :codencomenda"),
        @NamedQuery(name = "Estadoencomenda.findByIde", query = "SELECT ee FROM Estadoencomenda ee WHERE ee.ide = :ide")})
@IdClass(EstadoencomendaPK.class)
public class Estadoencomenda {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IDE")
    private Integer ide;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODENCOMENDA")
    private Integer codencomenda;
    @Basic
    @Column(name = "DTEE")
    private String dtee;
    @ManyToOne
    @JoinColumn(name = "IDE", referencedColumnName = "IDE", insertable = false, nullable = false, updatable = false)
    private Estadoe estadoeByIde;
    @ManyToOne
    @JoinColumn(name = "CODENCOMENDA", referencedColumnName = "CODENCOMENDA", insertable = false, nullable = false, updatable = false)
    private Encomenda encomendaByCodencomenda;

    public Integer getIde() {
        return ide;
    }

    public void setIde(Integer ide) {
        this.ide = ide;
    }

    public Integer getCodencomenda() {
        return codencomenda;
    }

    public void setCodencomenda(Integer codencomenda) {
        this.codencomenda = codencomenda;
    }

    public String getDtee() {
        return dtee;
    }

    public void setDtee(String dtee) {
        this.dtee = dtee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estadoencomenda that = (Estadoencomenda) o;
        return Objects.equals(ide, that.ide) && Objects.equals(codencomenda, that.codencomenda) && Objects.equals(dtee, that.dtee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ide, codencomenda, dtee);
    }

    public Estadoe getEstadoeByIde() {
        return estadoeByIde;
    }

    public void setEstadoeByIde(Estadoe estadoeByIde) {
        this.estadoeByIde = estadoeByIde;
    }

    public Encomenda getEncomendaByCodencomenda() {
        return encomendaByCodencomenda;
    }

    public void setEncomendaByCodencomenda(Encomenda encomendaByCodencomenda) {
        this.encomendaByCodencomenda = encomendaByCodencomenda;
    }
}
