package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Comprapeixe.findAll", query = "SELECT cp FROM Comprapeixe cp"),
        @NamedQuery(name = "Comprapeixe.findByCodcompra", query = "SELECT cp FROM Comprapeixe cp WHERE cp.codcompra = :codcompra"),
        @NamedQuery(name = "Comprapeixe.findByCodgc", query = "SELECT cp FROM Comprapeixe cp WHERE cp.codgc = :codgc")})
public class Comprapeixe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
    @SequenceGenerator(name = "compra_seq",
            sequenceName = "compra_seq", allocationSize = 1)
    @Column(name = "CODCOMPRA")
    private Integer codcompra;
    @Basic
    @Column(name = "DTCOMPRA")
    private String dtcompra;
    @Basic
    @Column(name = "QTDTOTAL")
    private int qtdtotal;
    @Basic
    @Column(name = "CODGC")
    private Integer codgc;
    @ManyToOne
    @JoinColumn(name = "CODGC", referencedColumnName = "CODGC", insertable = false, nullable = false, updatable = false)
    private Gestorcompras gestorcomprasByCodgc;
    @OneToMany(mappedBy = "comprapeixeByCodcompra")
    private Collection<Estadocompra> estadocomprasByCodcompra;
    @OneToMany(mappedBy = "comprapeixeByCodcompra")
    private Collection<Peixecomprapeixe> peixecomprapeixesByCodcompra;

    public Integer getCodcompra() {
        return codcompra;
    }

    public void setCodcompra(Integer codcompra) {
        this.codcompra = codcompra;
    }

    public String getDtcompra() {
        return dtcompra;
    }

    public void setDtcompra(String dtcompra) {
        this.dtcompra = dtcompra;
    }

    public int getQtdtotal() {
        return qtdtotal;
    }

    public void setQtdtotal(int qtdtotal) {
        this.qtdtotal = qtdtotal;
    }

    public Integer getCodgc() {
        return codgc;
    }

    public void setCodgc(Integer codgc) {
        this.codgc = codgc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprapeixe that = (Comprapeixe) o;
        return qtdtotal == that.qtdtotal && codgc == that.codgc && Objects.equals(codcompra, that.codcompra) && Objects.equals(dtcompra, that.dtcompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codcompra, dtcompra, qtdtotal, codgc);
    }

    public Gestorcompras getGestorcomprasByCodgc() {
        return gestorcomprasByCodgc;
    }

    public void setGestorcomprasByCodgc(Gestorcompras gestorcomprasByCodgc) {
        this.gestorcomprasByCodgc = gestorcomprasByCodgc;
    }

    public Collection<Estadocompra> getEstadocomprasByCodcompra() {
        return estadocomprasByCodcompra;
    }

    public void setEstadocomprasByCodcompra(Collection<Estadocompra> estadocomprasByCodcompra) {
        this.estadocomprasByCodcompra = estadocomprasByCodcompra;
    }

    public Collection<Peixecomprapeixe> getPeixecomprapeixesByCodcompra() {
        return peixecomprapeixesByCodcompra;
    }

    public void setPeixecomprapeixesByCodcompra(Collection<Peixecomprapeixe> peixecomprapeixesByCodcompra) {
        this.peixecomprapeixesByCodcompra = peixecomprapeixesByCodcompra;
    }
}
