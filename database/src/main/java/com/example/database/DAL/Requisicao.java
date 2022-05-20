package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Requisicao.findAll", query = "SELECT r FROM Requisicao r"),
        @NamedQuery(name = "Requisicao.findByCodrequisicao", query = "SELECT r FROM Requisicao r WHERE r.codrequisicao = :codrequisicao"),
        @NamedQuery(name = "Requisicao.findByCodgs", query = "SELECT r FROM Requisicao r WHERE r.codgs = :codgs")})
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisicao_seq")
    @SequenceGenerator(name = "requisicao_seq",
    sequenceName = "requisicao_seq", allocationSize = 1)
    @Column(name = "CODREQUISICAO")
    private Integer codrequisicao;
    @Basic
    @Column(name = "QTDTOTAL")
    private int qtdtotal;
    @Basic
    @Column(name = "CODGS")
    private Integer codgs;
    @Basic
    @Column(name = "DATA")
    private String data;
    @OneToMany(mappedBy = "requisicaoByCodrequisicao")
    private Collection<Peixerequisicao> peixerequisicaosByCodrequisicao;
    @ManyToOne
    @JoinColumn(name = "CODGS", referencedColumnName = "CODGS", insertable = false, nullable = false, updatable = false)
    private Gestorstock gestorstockByCodgs;

    public Integer getCodrequisicao() {
        return codrequisicao;
    }

    public void setCodrequisicao(Integer codrequisicao) {
        this.codrequisicao = codrequisicao;
    }

    public int getQtdtotal() {
        return qtdtotal;
    }

    public void setQtdtotal(int qtdtotal) {
        this.qtdtotal = qtdtotal;
    }

    public Integer getCodgs() {
        return codgs;
    }

    public void setCodgs(Integer codgs) {
        this.codgs = codgs;
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
        Requisicao that = (Requisicao) o;
        return qtdtotal == that.qtdtotal && codgs == that.codgs && Objects.equals(codrequisicao, that.codrequisicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codrequisicao, qtdtotal, codgs);
    }

    public Collection<Peixerequisicao> getPeixerequisicaosByCodrequisicao() {
        return peixerequisicaosByCodrequisicao;
    }

    public void setPeixerequisicaosByCodrequisicao(Collection<Peixerequisicao> peixerequisicaosByCodrequisicao) {
        this.peixerequisicaosByCodrequisicao = peixerequisicaosByCodrequisicao;
    }

    public Gestorstock getGestorstockByCodgs() {
        return gestorstockByCodgs;
    }

    public void setGestorstockByCodgs(Gestorstock gestorstockByCodgs) {
        this.gestorstockByCodgs = gestorstockByCodgs;
    }
}
