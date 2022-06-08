package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tipoconservaencomenda.findAll", query = "SELECT tc FROM Tipoconservaencomenda tc"),
        @NamedQuery(name = "Tipoconservaencomenda.findByPk", query = "SELECT tc FROM Tipoconservaencomenda tc WHERE tc.codtipoconserva = :codtipoconserva AND tc.codencomenda = :codencomenda"),
        @NamedQuery(name = "Tipoconservaencomenda.findByCodtpconserva", query = "SELECT tc FROM Tipoconservaencomenda tc WHERE tc.codtipoconserva = :codtipoconserva"),
        @NamedQuery(name = "Tipoconservaencomenda.findByCodencomenda", query = "SELECT tc FROM Tipoconservaencomenda tc WHERE tc.codencomenda = :codencomenda")})
@IdClass(TipoconservaencomendaPK.class)
public class Tipoconservaencomenda {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODTIPOCONSERVA")
    private Integer codtipoconserva;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODENCOMENDA")
    private Integer codencomenda;
    @Basic
    @Column(name = "QTD")
    private int qtd;
    @ManyToOne
    @JoinColumn(name = "CODTIPOCONSERVA", referencedColumnName = "CODTIPOCONSERVA", insertable = false, nullable = false, updatable = false)
    private Tipoconserva tipoconservaByCodtipoconserva;
    @ManyToOne
    @JoinColumn(name = "CODENCOMENDA", referencedColumnName = "CODENCOMENDA", insertable = false, nullable = false, updatable = false)
    private Encomenda encomendaByCodencomenda;

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

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipoconservaencomenda that = (Tipoconservaencomenda) o;
        return qtd == that.qtd && Objects.equals(codtipoconserva, that.codtipoconserva) && Objects.equals(codencomenda, that.codencomenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codtipoconserva, codencomenda, qtd);
    }

    public Tipoconserva getTipoconservaByCodtipoconserva() {
        return tipoconservaByCodtipoconserva;
    }

    public void setTipoconservaByCodtipoconserva(Tipoconserva tipoconservaByCodtipoconserva) {
        this.tipoconservaByCodtipoconserva = tipoconservaByCodtipoconserva;
    }

    public Encomenda getEncomendaByCodencomenda() {
        return encomendaByCodencomenda;
    }

    public void setEncomendaByCodencomenda(Encomenda encomendaByCodencomenda) {
        this.encomendaByCodencomenda = encomendaByCodencomenda;
    }
}
