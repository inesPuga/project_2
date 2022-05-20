package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l"),
        @NamedQuery(name = "Lote.findByCodlote", query = "SELECT l FROM Lote l WHERE l.codlote = :codlote"),
        @NamedQuery(name = "Lote.findByCodtpconserva", query = "SELECT l FROM Lote l WHERE l.codtipoconserva = :codtipoconserva")})
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lote_seq")
    @SequenceGenerator(name = "lote_seq",
    sequenceName = "lote_seq", allocationSize = 1)
    @Column(name = "CODLOTE")
    private Integer codlote;
    @Basic
    @Column(name = "DTPRODUCAO")
    private String dtproducao;
    @Basic
    @Column(name = "DTVALIDADE")
    private String dtvalidade;
    @Basic
    @Column(name = "CODTIPOCONSERVA")
    private short codtipoconserva;
    @ManyToOne
    @JoinColumn(name = "CODTIPOCONSERVA", referencedColumnName = "CODTIPOCONSERVA", insertable = false, nullable = false, updatable = false)
    private Tipoconserva tipoconservaByCodtipoconserva;

    public Integer getCodlote() {
        return codlote;
    }

    public void setCodlote(Integer codlote) {
        this.codlote = codlote;
    }

    public String getDtproducao() {
        return dtproducao;
    }

    public void setDtproducao(String dtproducao) {
        this.dtproducao = dtproducao;
    }

    public String getDtvalidade() {
        return dtvalidade;
    }

    public void setDtvalidade(String dtvalidade) {
        this.dtvalidade = dtvalidade;
    }

    public short getCodtipoconserva() {
        return codtipoconserva;
    }

    public void setCodtipoconserva(short codtipoconserva) {
        this.codtipoconserva = codtipoconserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;
        return codtipoconserva == lote.codtipoconserva && Objects.equals(codlote, lote.codlote) && Objects.equals(dtproducao, lote.dtproducao) && Objects.equals(dtvalidade, lote.dtvalidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codlote, dtproducao, dtvalidade, codtipoconserva);
    }

    public Tipoconserva getTipoconservaByCodtipoconserva() {
        return tipoconservaByCodtipoconserva;
    }

    public void setTipoconservaByCodtipoconserva(Tipoconserva tipoconservaByCodtipoconserva) {
        this.tipoconservaByCodtipoconserva = tipoconservaByCodtipoconserva;
    }
}
