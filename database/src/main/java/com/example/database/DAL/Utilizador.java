package com.example.database.DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u"),
        @NamedQuery(name = "Utilizador.findByIduser", query = "SELECT u FROM Utilizador u WHERE u.iduser = :iduser"),
        @NamedQuery(name = "Utilizador.findAllByUsername", query = "SELECT u FROM Utilizador u WHERE u.username LIKE :username"),
        @NamedQuery(name = "Utilizador.findAllByEmail", query = "SELECT u FROM Utilizador u WHERE u.email LIKE :email")})
public class Utilizador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "IDUSER", nullable = false, updatable = false)
    private Integer iduser;
    @Basic
    @Column(name = "NOME")
    private String nome;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "NUMTEL")
    private int numtel;
    @Basic
    @Column(name = "STATUS")
    private Integer status;
    @Basic
    @Column(name = "CARGO")
    private String cargo;
    @Basic
    @Column(name = "USERNAME")
    private String username;
    @Basic
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "utilizadorByIduser")
    private Collection<Cliente> clientesByIduser;
    @OneToMany(mappedBy = "utilizadorByIduser")
    private Collection<Gerente> gerentesByIduser;
    @OneToMany(mappedBy = "utilizadorByIduser")
    private Collection<Gestorcompras> gestorcomprasByIduser;
    @OneToMany(mappedBy = "utilizadorByIduser")
    private Collection<Gestorstock> gestorstocksByIduser;
    @OneToMany(mappedBy = "utilizadorByIduser")
    private Collection<Gestorvendas> gestorvendasByIduser;
    @OneToMany(mappedBy = "utilizadorByIduser")
    private Collection<Responsavelarmazem> responsavelarmazemsByIduser;
    @OneToMany(mappedBy = "utilizadorByIduser")
    private Collection<Responsavelqualidade> responsavelqualidadesByIduser;

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return numtel == that.numtel && Objects.equals(iduser, that.iduser) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(cargo, that.cargo) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, nome, email, numtel, cargo, username);
    }

    public Collection<Cliente> getClientesByIduser() {
        return clientesByIduser;
    }

    public void setClientesByIduser(Collection<Cliente> clientesByIduser) {
        this.clientesByIduser = clientesByIduser;
    }

    public Collection<Gerente> getGerentesByIduser() {
        return gerentesByIduser;
    }

    public void setGerentesByIduser(Collection<Gerente> gerentesByIduser) {
        this.gerentesByIduser = gerentesByIduser;
    }

    public Collection<Gestorcompras> getGestorcomprasByIduser() {
        return gestorcomprasByIduser;
    }

    public void setGestorcomprasByIduser(Collection<Gestorcompras> gestorcomprasByIduser) {
        this.gestorcomprasByIduser = gestorcomprasByIduser;
    }

    public Collection<Gestorstock> getGestorstocksByIduser() {
        return gestorstocksByIduser;
    }

    public void setGestorstocksByIduser(Collection<Gestorstock> gestorstocksByIduser) {
        this.gestorstocksByIduser = gestorstocksByIduser;
    }

    public Collection<Gestorvendas> getGestorvendasByIduser() {
        return gestorvendasByIduser;
    }

    public void setGestorvendasByIduser(Collection<Gestorvendas> gestorvendasByIduser) {
        this.gestorvendasByIduser = gestorvendasByIduser;
    }

    public Collection<Responsavelarmazem> getResponsavelarmazemsByIduser() {
        return responsavelarmazemsByIduser;
    }

    public void setResponsavelarmazemsByIduser(Collection<Responsavelarmazem> responsavelarmazemsByIduser) {
        this.responsavelarmazemsByIduser = responsavelarmazemsByIduser;
    }

    public Collection<Responsavelqualidade> getResponsavelqualidadesByIduser() {
        return responsavelqualidadesByIduser;
    }

    public void setResponsavelqualidadesByIduser(Collection<Responsavelqualidade> responsavelqualidadesByIduser) {
        this.responsavelqualidadesByIduser = responsavelqualidadesByIduser;
    }
}
