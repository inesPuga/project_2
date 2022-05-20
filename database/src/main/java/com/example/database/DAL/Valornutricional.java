package com.example.database.DAL;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Valornutricional.findAll", query = "SELECT v FROM Valornutricional v"),
        @NamedQuery(name = "Valornutricional.findByPk", query = "SELECT v FROM Valornutricional v WHERE v.codtipoconserva = :codtipoconserva AND v.idnutriente = :idnutriente"),
        @NamedQuery(name = "Valornutricional.findByCodtpconserva", query = "SELECT v FROM Valornutricional v WHERE v.codtipoconserva = :codtipoconserva"),
        @NamedQuery(name = "Valornutricional.findByIdnutriente", query = "SELECT v FROM Valornutricional v WHERE v.idnutriente = :idnutriente")})
@IdClass(ValornutricionalPK.class)
public class Valornutricional {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IDNUTRIENTE")
    private Integer idnutriente;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODTIPOCONSERVA")
    private Integer codtipoconserva;
    @Basic
    @Column(name = "QTD")
    private int qtd;
    @ManyToOne
    @JoinColumn(name = "IDNUTRIENTE", referencedColumnName = "IDNUTRIENTE", insertable = false, nullable = false, updatable = false)
    private Nutriente nutrienteByIdnutriente;
    @ManyToOne
    @JoinColumn(name = "CODTIPOCONSERVA", referencedColumnName = "CODTIPOCONSERVA", insertable = false, nullable = false, updatable = false)
    private Tipoconserva tipoconservaByCodtipoconserva;

    public Integer getIdnutriente() {
        return idnutriente;
    }

    public void setIdnutriente(Integer idnutriente) {
        this.idnutriente = idnutriente;
    }

    public Integer getCodtipoconserva() {
        return codtipoconserva;
    }

    public void setCodtipoconserva(Integer codtipoconserva) {
        this.codtipoconserva = codtipoconserva;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valornutricional that = (Valornutricional) o;
        return qtd == that.qtd && Objects.equals(idnutriente, that.idnutriente) && Objects.equals(codtipoconserva, that.codtipoconserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idnutriente, codtipoconserva, qtd);
    }

    public Nutriente getNutrienteByIdnutriente() {
        return nutrienteByIdnutriente;
    }

    public void setNutrienteByIdnutriente(Nutriente nutrienteByIdnutriente) {
        this.nutrienteByIdnutriente = nutrienteByIdnutriente;
    }

    public Tipoconserva getTipoconservaByCodtipoconserva() {
        return tipoconservaByCodtipoconserva;
    }

    public void setTipoconservaByCodtipoconserva(Tipoconserva tipoconservaByCodtipoconserva) {
        this.tipoconservaByCodtipoconserva = tipoconservaByCodtipoconserva;
    }
}
