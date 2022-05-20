package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Docqualidade.findAll", query = "SELECT dq FROM Docqualidade dq"),
        @NamedQuery(name = "Docqualidade.findByCodcodqld", query = "SELECT dq FROM Docqualidade dq WHERE dq.coddocqualidade = :coddocqualidade"),
        @NamedQuery(name = "Docqualidade.findByCodrq", query = "SELECT dq FROM Docqualidade dq WHERE dq.codrq = :codrq")})
public class Docqualidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "docqld_seq")
    @SequenceGenerator(name = "docqld_seq",
    sequenceName = "docqld_seq", allocationSize = 1)
    @Column(name = "CODDOCQUALIDADE")
    private Integer coddocqualidade;
    @Basic
    @Column(name = "FASEPRODUCAO")
    private String faseproducao;
    @Basic
    @Column(name = "DATA")
    private String data;
    @Basic
    @Column(name = "CODRQ")
    private Integer codrq;
    @ManyToOne
    @JoinColumn(name = "CODRQ", referencedColumnName = "CODRQ", insertable = false, nullable = false, updatable = false)
    private Responsavelqualidade responsavelqualidadeByCodrq;
    @OneToMany(mappedBy = "docqualidadeByCoddocqualidade")
    private Collection<Peixedocqualidade> peixedocqualidadesByCoddocqualidade;

    public Integer getCoddocqualidade() {
        return coddocqualidade;
    }

    public void setCoddocqualidade(Integer coddocqualidade) {
        this.coddocqualidade = coddocqualidade;
    }

    public String getFaseproducao() {
        return faseproducao;
    }

    public void setFaseproducao(String faseproducao) {
        this.faseproducao = faseproducao;
    }

    public Integer getCodrq() {
        return codrq;
    }

    public void setCodrq(Integer codrq) {
        this.codrq = codrq;
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
        Docqualidade that = (Docqualidade) o;
        return codrq == that.codrq && Objects.equals(coddocqualidade, that.coddocqualidade) && Objects.equals(faseproducao, that.faseproducao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coddocqualidade, faseproducao, codrq);
    }

    public Responsavelqualidade getResponsavelqualidadeByCodrq() {
        return responsavelqualidadeByCodrq;
    }

    public void setResponsavelqualidadeByCodrq(Responsavelqualidade responsavelqualidadeByCodrq) {
        this.responsavelqualidadeByCodrq = responsavelqualidadeByCodrq;
    }

    public Collection<Peixedocqualidade> getPeixedocqualidadesByCoddocqualidade() {
        return peixedocqualidadesByCoddocqualidade;
    }

    public void setPeixedocqualidadesByCoddocqualidade(Collection<Peixedocqualidade> peixedocqualidadesByCoddocqualidade) {
        this.peixedocqualidadesByCoddocqualidade = peixedocqualidadesByCoddocqualidade;
    }
}
