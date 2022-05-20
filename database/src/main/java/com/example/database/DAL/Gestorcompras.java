package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Gestorcompras.findAll", query = "SELECT gc FROM Gestorcompras gc"),
        @NamedQuery(name = "Gestorcompras.findByIdgc", query = "SELECT gc FROM Gestorcompras gc WHERE gc.codgc = :codgc"),
        @NamedQuery(name = "Gestorcompras.findByUsername", query = "SELECT gc FROM Gestorcompras gc, Utilizador u WHERE u.iduser=gc.iduser AND u.username LIKE :username"),
        @NamedQuery(name = "Gestorcompras.findUser", query= "SELECT u FROM Utilizador u WHERE u.iduser = :iduser")})
public class Gestorcompras {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gestorcompras_seq")
    @SequenceGenerator(name = "gestorcompras_seq",
            sequenceName = "gestorcompras_seq", allocationSize = 1)
    @Column(name = "CODGC")
    private Integer codgc;
    @Basic
    @Column(name = "IDUSER")
    private Integer iduser;
    @OneToMany(mappedBy = "gestorcomprasByCodgc")
    private Collection<Comprapeixe> comprapeixesByCodgc;
    @ManyToOne
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER", insertable = false, nullable = false, updatable = false)
    private Utilizador utilizadorByIduser;

    public Integer getCodgc() {
        return codgc;
    }

    public void setCodgc(Integer codgc) {
        this.codgc = codgc;
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
        Gestorcompras that = (Gestorcompras) o;
        return Objects.equals(codgc, that.codgc) && Objects.equals(iduser, that.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codgc, iduser);
    }

    public Collection<Comprapeixe> getComprapeixesByCodgc() {
        return comprapeixesByCodgc;
    }

    public void setComprapeixesByCodgc(Collection<Comprapeixe> comprapeixesByCodgc) {
        this.comprapeixesByCodgc = comprapeixesByCodgc;
    }

    public Utilizador getUtilizadorByIduser() {
        return utilizadorByIduser;
    }

    public void setUtilizadorByIduser(Utilizador utilizadorByIduser) {
        this.utilizadorByIduser = utilizadorByIduser;
    }
}
