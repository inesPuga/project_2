package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
        @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.codcliente = :idcliente"),
        @NamedQuery(name = "Cliente.findByUsername", query = "SELECT c FROM Cliente c, Utilizador u WHERE u.iduser=c.iduser AND u.username LIKE :username"),
        @NamedQuery(name = "Cliente.findUser", query= "SELECT u FROM Utilizador u WHERE u.iduser = :iduser")})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq",
    sequenceName = "client_seq", allocationSize = 1)
    @Column(name = "CODCLIENTE")
    private Integer codcliente;
    @Basic
    @Column(name = "NOMERUA")
    private String nomerua;
    @Basic
    @Column(name = "NUMPORTA")
    private int numporta;
    @Basic
    @Column(name = "NUMCC")
    private int numcc;
    @Basic
    @Column(name = "NIF")
    private int nif;
    @Basic
    @Column(name = "DTNASCIMENTO")
    private String dtnascimento;
    @Basic
    @Column(name = "IDUSER")
    private Integer iduser;
    @Basic
    @Column(name = "CODPOSTAL")
    private String codpostal;
    @ManyToOne
    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER", nullable = false, updatable = false, insertable = false)
    private Utilizador utilizadorByIduser;
    @ManyToOne
    @JoinColumn(name = "CODPOSTAL", referencedColumnName = "CODPOSTAL", nullable = false, updatable = false, insertable = false)
    private Cpostal cpostalByCodpostal;
    @OneToMany(mappedBy = "clienteByCodcliente")
    private Collection<Encomenda> encomendasByCodcliente;

    public Integer getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Integer codcliente) {
        this.codcliente = codcliente;
    }

    public String getNomerua() {
        return nomerua;
    }

    public void setNomerua(String nomerua) {
        this.nomerua = nomerua;
    }

    public int getNumporta() {
        return numporta;
    }

    public void setNumporta(int numporta) {
        this.numporta = numporta;
    }

    public int getNumcc() {
        return numcc;
    }

    public void setNumcc(int numcc) {
        this.numcc = numcc;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(String dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return numporta == cliente.numporta && numcc == cliente.numcc && nif == cliente.nif && Objects.equals(codcliente, cliente.codcliente) && Objects.equals(nomerua, cliente.nomerua) && Objects.equals(dtnascimento, cliente.dtnascimento) && Objects.equals(iduser, cliente.iduser) && Objects.equals(codpostal, cliente.codpostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codcliente, nomerua, numporta, numcc, nif, dtnascimento, iduser, codpostal);
    }

    public Utilizador getUtilizadorByIduser() {
        return utilizadorByIduser;
    }

    public void setUtilizadorByIduser(Utilizador utilizadorByIduser) {
        this.utilizadorByIduser = utilizadorByIduser;
    }

    public Cpostal getCpostalByCodpostal() {
        return cpostalByCodpostal;
    }

    public void setCpostalByCodpostal(Cpostal cpostalByCodpostal) {
        this.cpostalByCodpostal = cpostalByCodpostal;
    }

    public Collection<Encomenda> getEncomendasByCodcliente() {
        return encomendasByCodcliente;
    }

    public void setEncomendasByCodcliente(Collection<Encomenda> encomendasByCodcliente) {
        this.encomendasByCodcliente = encomendasByCodcliente;
    }
}
