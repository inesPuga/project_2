package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cpostal.findAll", query = "SELECT cp FROM Cpostal cp"),
        @NamedQuery(name = "Cpostal.findByCodPostal", query = "SELECT cp FROM Cpostal cp WHERE cp.codpostal = :codpostal")})
public class Cpostal {
    @Id
    @Column(name = "CODPOSTAL")
    private String codpostal;
    @Basic
    @Column(name = "LOCALIDADE")
    private String localidade;
    @OneToMany(mappedBy = "cpostalByCodpostal")
    private Collection<Cliente> clientesByCodpostal;

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpostal cpostal = (Cpostal) o;
        return Objects.equals(codpostal, cpostal.codpostal) && Objects.equals(localidade, cpostal.localidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codpostal, localidade);
    }

    public Collection<Cliente> getClientesByCodpostal() {
        return clientesByCodpostal;
    }

    public void setClientesByCodpostal(Collection<Cliente> clientesByCodpostal) {
        this.clientesByCodpostal = clientesByCodpostal;
    }
}
