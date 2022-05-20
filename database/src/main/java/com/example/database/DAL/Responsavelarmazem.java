package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Responsavelarmazem.findAll", query = "SELECT ra FROM Responsavelarmazem ra"),
        @NamedQuery(name = "Responsavelarmazem.findByIdresponsavelA", query = "SELECT ra FROM Responsavelarmazem ra WHERE ra.coda = :coda"),
        @NamedQuery(name = "Responsavelarmazem.findByUsername", query = "SELECT ra FROM Responsavelarmazem ra, Utilizador u WHERE u.iduser=ra.iduser AND u.username LIKE :username"),
        @NamedQuery(name = "Responsavelarmazem.findUser", query= "SELECT u FROM Utilizador u WHERE u.iduser = :iduser")})
public class Responsavelarmazem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resparm_seq")
    @SequenceGenerator(name = "resparm_seq",
    sequenceName = "resparm_seq", allocationSize = 1)
    @Column(name = "CODA")
    private Integer coda;
    @Basic
    @Column(name = "IDUSER")
    private Integer iduser;
    @ManyToOne
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER", insertable = false, nullable = false, updatable = false)
    private Utilizador utilizadorByIduser;

    public Integer getCoda() {
        return coda;
    }

    public void setCoda(Integer coda) {
        this.coda = coda;
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
        Responsavelarmazem that = (Responsavelarmazem) o;
        return Objects.equals(coda, that.coda) && Objects.equals(iduser, that.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coda, iduser);
    }

    public Utilizador getUtilizadorByIduser() {
        return utilizadorByIduser;
    }

    public void setUtilizadorByIduser(Utilizador utilizadorByIduser) {
        this.utilizadorByIduser = utilizadorByIduser;
    }
}
