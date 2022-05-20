package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Nutriente.findAll", query = "SELECT n FROM Nutriente n"),
        @NamedQuery(name = "Nutriente.findByIdnutriente", query = "SELECT n FROM Nutriente n WHERE n.idnutriente = :idnutriente"),
        @NamedQuery(name = "Nutriente.findByNomenutriente", query = "SELECT n FROM Nutriente n WHERE n.nomenutriente LIKE :nomenutriente")})
public class Nutriente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nutriente_seq")
    @SequenceGenerator(name = "nutriente_seq",
            sequenceName = "nutriente_seq", allocationSize = 1)
    @Column(name = "IDNUTRIENTE")
    private Integer idnutriente;
    @Basic
    @Column(name = "NOMENUTRIENTE")
    private String nomenutriente;
    @OneToMany(mappedBy = "nutrienteByIdnutriente")
    private Collection<Valornutricional> valornutricionalsByIdnutriente;

    public Integer getIdnutriente() {
        return idnutriente;
    }

    public void setIdnutriente(Integer idnutriente) {
        this.idnutriente = idnutriente;
    }

    public String getNomenutriente() {
        return nomenutriente;
    }

    public void setNomenutriente(String nomenutriente) {
        this.nomenutriente = nomenutriente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nutriente nutriente = (Nutriente) o;
        return Objects.equals(idnutriente, nutriente.idnutriente) && Objects.equals(nomenutriente, nutriente.nomenutriente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idnutriente, nomenutriente);
    }

    public Collection<Valornutricional> getValornutricionalsByIdnutriente() {
        return valornutricionalsByIdnutriente;
    }

    public void setValornutricionalsByIdnutriente(Collection<Valornutricional> valornutricionalsByIdnutriente) {
        this.valornutricionalsByIdnutriente = valornutricionalsByIdnutriente;
    }
}
