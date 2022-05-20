package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Peixecomprapeixe.findAll", query = "SELECT pcp FROM Peixecomprapeixe pcp"),
        @NamedQuery(name = "Peixecomprapeixe.findByPk", query = "SELECT pcp FROM Peixecomprapeixe pcp WHERE pcp.codpeixe = :codpeixe AND pcp.codcompra = :codcompra"),
        @NamedQuery(name = "Peixecomprapeixe.findByCodcompra", query = "SELECT pcp FROM Peixecomprapeixe pcp WHERE pcp.codcompra = :codcompra"),
        @NamedQuery(name = "Peixecomprapeixe.findByCodpeixe", query = "SELECT pcp FROM Peixecomprapeixe pcp WHERE pcp.codpeixe = :codpeixe")})
@IdClass(PeixecomprapeixePK.class)
public class Peixecomprapeixe {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODCOMPRA")
    private Integer codcompra;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODPEIXE")
    private Integer codpeixe;
    @Basic
    @Column(name = "QTD")
    private int qtd;
    @ManyToOne
    @JoinColumn(name = "CODCOMPRA", referencedColumnName = "CODCOMPRA", insertable = false, nullable = false, updatable = false)
    private Comprapeixe comprapeixeByCodcompra;
    @ManyToOne
    @JoinColumn(name = "CODPEIXE", referencedColumnName = "CODPEIXE", insertable = false, nullable = false, updatable = false)
    private Peixe peixeByCodpeixe;

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
        Peixecomprapeixe that = (Peixecomprapeixe) o;
        return qtd == that.qtd && Objects.equals(codcompra, that.codcompra) && Objects.equals(codpeixe, that.codpeixe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codcompra, codpeixe, qtd);
    }

    public Comprapeixe getComprapeixeByCodcompra() {
        return comprapeixeByCodcompra;
    }

    public void setComprapeixeByCodcompra(Comprapeixe comprapeixeByCodcompra) {
        this.comprapeixeByCodcompra = comprapeixeByCodcompra;
    }

    public Peixe getPeixeByCodpeixe() {
        return peixeByCodpeixe;
    }

    public void setPeixeByCodpeixe(Peixe peixeByCodpeixe) {
        this.peixeByCodpeixe = peixeByCodpeixe;
    }
}
