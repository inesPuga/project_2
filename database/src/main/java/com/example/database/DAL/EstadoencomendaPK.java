package com.example.database.DAL;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EstadoencomendaPK implements Serializable {
    @Id
    @Column(name = "IDE")
    private Integer ide;
    @Id
    @Column(name = "CODENCOMENDA")
    private Integer codencomenda;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoencomendaPK that = (EstadoencomendaPK) o;
        return Objects.equals(ide, that.ide) && Objects.equals(codencomenda, that.codencomenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ide, codencomenda);
    }
}
