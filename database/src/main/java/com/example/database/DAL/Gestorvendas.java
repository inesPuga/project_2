package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Gestorvendas.findAll", query = "SELECT gv FROM Gestorvendas gv"),
        @NamedQuery(name = "Gestorvendas.findByIdgv", query = "SELECT gv FROM Gestorvendas gv WHERE gv.codgv = :codgv"),
        @NamedQuery(name = "Gestorvendas.findByUsername", query = "SELECT gv FROM Gestorvendas gv, Utilizador u WHERE u.iduser=gv.iduser AND u.username LIKE :username"),
        @NamedQuery(name = "Gestorvendas.findUser", query= "SELECT u FROM Utilizador u WHERE u.iduser = :iduser")})
public class Gestorvendas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gestorvendas_seq")
    @SequenceGenerator(name = "gestorvendas_seq",
            sequenceName = "gestorvendas_seq", allocationSize = 1)
    @Column(name = "CODGV")
    private Integer codgv;
    @Basic
    @Column(name = "IDUSER")
    private Integer iduser;
    @ManyToOne
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER", insertable = false, nullable = false, updatable = false)
    private Utilizador utilizadorByIduser;

    public Integer getCodgv() {
        return codgv;
    }

    public void setCodgv(Integer codgv) {
        this.codgv = codgv;
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
        Gestorvendas that = (Gestorvendas) o;
        return Objects.equals(codgv, that.codgv) && Objects.equals(iduser, that.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codgv, iduser);
    }

    public Utilizador getUtilizadorByIduser() {
        return utilizadorByIduser;
    }

    public void setUtilizadorByIduser(Utilizador utilizadorByIduser) {
        this.utilizadorByIduser = utilizadorByIduser;
    }
}
