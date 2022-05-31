package com.example.database.DAL;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PeixerequisicaoPK implements Serializable {
    @Id
    @Column(name = "CODPEIXE")
    private Integer codpeixe;
    @Id
    @Column(name = "CODREQUISICAO")
    private Integer codrequisicao;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeixerequisicaoPK that = (PeixerequisicaoPK) o;
        return Objects.equals(codpeixe, that.codpeixe) && Objects.equals(codrequisicao, that.codrequisicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codpeixe, codrequisicao);
    }
}
