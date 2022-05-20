package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Gerente.findAll", query = "SELECT g FROM Gerente g"),
        @NamedQuery(name = "Gerente.findByIdgerente", query = "SELECT g FROM Gerente g WHERE g.codg = :codg"),
        @NamedQuery(name = "Gerente.findByUsername", query = "SELECT g FROM Gerente g, Utilizador u WHERE u.iduser=g.iduser AND u.username LIKE :username"),
        @NamedQuery(name = "Gerente.findUser", query= "SELECT u FROM Utilizador u WHERE u.iduser = :iduser")})
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerente_seq")
    @SequenceGenerator(name = "gerente_seq",
            sequenceName = "gerente_seq", allocationSize = 1)
    @Column(name = "CODG")
    private Integer codg;
    @Basic
    @Column(name = "IDUSER")
    private Integer iduser;
    @ManyToOne
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER", insertable = false, nullable = false, updatable = false)
    private Utilizador utilizadorByIduser;

    public Integer getCodg() {
        return codg;
    }

    public void setCodg(Integer codg) {
        this.codg = codg;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gerente gerente = (Gerente) o;
        return Objects.equals(codg, gerente.codg) && Objects.equals(iduser, gerente.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codg, iduser);
    }

    public Utilizador getUtilizadorByIduser() {
        return utilizadorByIduser;
    }

    public void setUtilizadorByIduser(Utilizador utilizadorByIduser) {
        this.utilizadorByIduser = utilizadorByIduser;
    }
}
