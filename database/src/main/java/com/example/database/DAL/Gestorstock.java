package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Gestorstock.findAll", query = "SELECT gs FROM Gestorstock gs"),
        @NamedQuery(name = "Gestorstock.findByIdgs", query = "SELECT gs FROM Gestorstock gs WHERE gs.codgs = :codgs"),
        @NamedQuery(name = "Gestorstock.findByUsername", query = "SELECT gs FROM Gestorstock gs, Utilizador u WHERE u.iduser=gs.iduser AND u.username LIKE :username"),
        @NamedQuery(name = "Gestorstock.findUser", query= "SELECT u FROM Utilizador u WHERE u.iduser = :iduser")})
public class Gestorstock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gestorstock_seq")
    @SequenceGenerator(name = "gestorstock_seq",
            sequenceName = "gestorstock_seq", allocationSize = 1)
    @Column(name = "CODGS")
    private Integer codgs;
    @Basic
    @Column(name = "IDUSER")
    private Integer iduser;
    @ManyToOne
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER", insertable = false, nullable = false, updatable = false)
    private Utilizador utilizadorByIduser;
    @OneToMany(mappedBy = "gestorstockByCodgs")
    private Collection<Requisicao> requisicaosByCodgs;

    public Integer getCodgs() {
        return codgs;
    }

    public void setCodgs(Integer codgs) {
        this.codgs = codgs;
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
        Gestorstock that = (Gestorstock) o;
        return Objects.equals(codgs, that.codgs) && Objects.equals(iduser, that.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codgs, iduser);
    }

    public Utilizador getUtilizadorByIduser() {
        return utilizadorByIduser;
    }

    public void setUtilizadorByIduser(Utilizador utilizadorByIduser) {
        this.utilizadorByIduser = utilizadorByIduser;
    }

    public Collection<Requisicao> getRequisicaosByCodgs() {
        return requisicaosByCodgs;
    }

    public void setRequisicaosByCodgs(Collection<Requisicao> requisicaosByCodgs) {
        this.requisicaosByCodgs = requisicaosByCodgs;
    }
}
