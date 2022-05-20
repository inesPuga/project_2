package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Peixerequisicao.findAll", query = "SELECT pr FROM Peixerequisicao pr"),
        @NamedQuery(name = "Peixerequisicao.findByPk", query = "SELECT pr FROM Peixerequisicao pr WHERE pr.codpeixe = :codpeixe AND pr.codrequisicao = :codrequisicao"),
        @NamedQuery(name = "Peixerequisicao.findByCodreq", query = "SELECT pr FROM Peixerequisicao pr WHERE pr.codrequisicao = :codrequisicao"),
        @NamedQuery(name = "Peixerequisicao.findByCodpeixe", query = "SELECT pr FROM Peixerequisicao pr WHERE pr.codpeixe = :codpeixe")})
@IdClass(PeixerequisicaoPK.class)
public class Peixerequisicao {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODPEIXE")
    private Integer codpeixe;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODREQUISICAO")
    private Integer codrequisicao;
    @Basic
    @Column(name = "QTD")
    private int qtd;
    @ManyToOne
    @JoinColumn(name = "CODPEIXE", referencedColumnName = "CODPEIXE", insertable = false, nullable = false, updatable = false)
    private Peixe peixeByCodpeixe;
    @ManyToOne
    @JoinColumn(name = "CODREQUISICAO", referencedColumnName = "CODREQUISICAO", insertable = false, nullable = false, updatable = false)
    private Requisicao requisicaoByCodrequisicao;

    public Integer getCodpeixe() {
        return codpeixe;
    }

    public void setCodpeixe(Integer codpeixe) {
        this.codpeixe = codpeixe;
    }

    public Integer getCodrequisicao() {
        return codrequisicao;
    }

    public void setCodrequisicao(Integer codrequisicao) {
        this.codrequisicao = codrequisicao;
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
        Peixerequisicao that = (Peixerequisicao) o;
        return qtd == that.qtd && Objects.equals(codpeixe, that.codpeixe) && Objects.equals(codrequisicao, that.codrequisicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codpeixe, codrequisicao, qtd);
    }

    public Peixe getPeixeByCodpeixe() {
        return peixeByCodpeixe;
    }

    public void setPeixeByCodpeixe(Peixe peixeByCodpeixe) {
        this.peixeByCodpeixe = peixeByCodpeixe;
    }

    public Requisicao getRequisicaoByCodrequisicao() {
        return requisicaoByCodrequisicao;
    }

    public void setRequisicaoByCodrequisicao(Requisicao requisicaoByCodrequisicao) {
        this.requisicaoByCodrequisicao = requisicaoByCodrequisicao;
    }
}
