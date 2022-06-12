package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Encomenda.findAll", query = "SELECT e FROM Encomenda e"),
        @NamedQuery(name = "Encomenda.findByCodEnc", query = "SELECT e FROM Encomenda e WHERE e.codencomenda = :codencomenda"),
        @NamedQuery(name = "Encomenda.findByCodcli", query = "SELECT e FROM Encomenda e WHERE e.codcliente = :codcliente"),
        @NamedQuery(name = "Encomenda.sumprice", query = "SELECT sum(e.precototal) FROM Encomenda e")})
public class Encomenda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "encomenda_seq")
    @SequenceGenerator(name = "encomenda_seq",
            sequenceName = "encomenda_seq", allocationSize = 1)
    @Column(name = "CODENCOMENDA")
    private Integer codencomenda;
    @Basic
    @Column(name = "PRECOTOTAL")
    private Double precototal;
    @Basic
    @Column(name = "DATA")
    private String data;
    @Basic
    @Column(name = "CODCLIENTE")
    private Integer codcliente;
    @ManyToOne
    @JoinColumn(name = "CODCLIENTE", referencedColumnName = "CODCLIENTE", insertable = false, nullable = false, updatable = false)
    private Cliente clienteByCodcliente;
    @OneToMany(mappedBy = "encomendaByCodencomenda")
    private Collection<Estadoencomenda> estadoencomendasByCodencomenda;
    @OneToMany(mappedBy = "encomendaByCodencomenda")
    private Collection<Tipoconservaencomenda> tipoconservaencomendasByCodencomenda;

    public Integer getCodencomenda() {
        return codencomenda;
    }

    public void setCodencomenda(Integer codencomenda) {
        this.codencomenda = codencomenda;
    }

    public Double getPrecototal() {
        return precototal;
    }

    public void setPrecototal(Double precototal) {
        this.precototal = precototal;
    }

    public Integer getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Integer codcliente) {
        this.codcliente = codcliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encomenda encomenda = (Encomenda) o;
        return precototal == encomenda.precototal && codcliente == encomenda.codcliente && Objects.equals(codencomenda, encomenda.codencomenda) && Objects.equals(data, encomenda.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codencomenda, precototal, codcliente, data);
    }

    public Cliente getClienteByCodcliente() {
        return clienteByCodcliente;
    }

    public void setClienteByCodcliente(Cliente clienteByCodcliente) {
        this.clienteByCodcliente = clienteByCodcliente;
    }

    public Collection<Estadoencomenda> getEstadoencomendasByCodencomenda() {
        return estadoencomendasByCodencomenda;
    }

    public void setEstadoencomendasByCodencomenda(Collection<Estadoencomenda> estadoencomendasByCodencomenda) {
        this.estadoencomendasByCodencomenda = estadoencomendasByCodencomenda;
    }

    public Collection<Tipoconservaencomenda> getTipoconservaencomendasByCodencomenda() {
        return tipoconservaencomendasByCodencomenda;
    }

    public void setTipoconservaencomendasByCodencomenda(Collection<Tipoconservaencomenda> tipoconservaencomendasByCodencomenda) {
        this.tipoconservaencomendasByCodencomenda = tipoconservaencomendasByCodencomenda;
    }
}
