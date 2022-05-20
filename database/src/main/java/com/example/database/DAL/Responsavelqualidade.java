package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Responsavelqualidade.findAll", query = "SELECT rq FROM Responsavelqualidade rq"),
        @NamedQuery(name = "Responsavelqualidade.findByIdrq", query = "SELECT rq FROM Responsavelqualidade rq WHERE rq.codrq = :codrq"),
        @NamedQuery(name = "Responsavelqualidade.findByUsername", query = "SELECT rq FROM Responsavelqualidade rq, Utilizador u WHERE u.iduser=rq.iduser AND u.username LIKE :username"),
        @NamedQuery(name = "Responsavelqualidade.findUser", query= "SELECT u FROM Utilizador u WHERE u.iduser = :iduser")})
public class Responsavelqualidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rq_seq")
    @SequenceGenerator(name = "rq_seq",
    sequenceName = "rq_seq", allocationSize = 1)
    @Column(name = "CODRQ")
    private Integer codrq;
    @Basic
    @Column(name = "IDUSER")
    private Integer iduser;
    @OneToMany(mappedBy = "responsavelqualidadeByCodrq")
    private Collection<Docqualidade> docqualidadesByCodrq;
    @ManyToOne
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER", insertable = false, nullable = false, updatable = false)
    private Utilizador utilizadorByIduser;

    public Integer getCodrq() {
        return codrq;
    }

    public void setCodrq(Integer codrq) {
        this.codrq = codrq;
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
        Responsavelqualidade that = (Responsavelqualidade) o;
        return Objects.equals(codrq, that.codrq) && Objects.equals(iduser, that.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codrq, iduser);
    }

    public Collection<Docqualidade> getDocqualidadesByCodrq() {
        return docqualidadesByCodrq;
    }

    public void setDocqualidadesByCodrq(Collection<Docqualidade> docqualidadesByCodrq) {
        this.docqualidadesByCodrq = docqualidadesByCodrq;
    }

    public Utilizador getUtilizadorByIduser() {
        return utilizadorByIduser;
    }

    public void setUtilizadorByIduser(Utilizador utilizadorByIduser) {
        this.utilizadorByIduser = utilizadorByIduser;
    }
}
