package com.example.database.DAL;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tipoconserva.findAll", query = "SELECT tc FROM Tipoconserva tc"),
        @NamedQuery(name = "Tipoconserva.findByIdtipoconserva", query = "SELECT tc FROM Tipoconserva tc WHERE tc.codtipoconserva = :codtipoconserva"),
        @NamedQuery(name = "Tipoconserva.findByCodpeixe", query = "SELECT tc FROM Tipoconserva tc WHERE tc.codpeixe = :codpeixe")})
public class Tipoconserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tpconserva_seq")
    @SequenceGenerator(name = "tpconserva_seq",
    sequenceName = "tpconserva_seq", allocationSize = 1)
    @Column(name = "CODTIPOCONSERVA")
    private Integer codtipoconserva;
    @Basic
    @Column(name = "NOME")
    private String nome;
    @Basic
    @Column(name = "PRECOACTVENDA")
    private double precoactvenda;
    @Basic
    @Column(name = "DESCRICAO")
    private String descricao;
    @Basic
    @Column(name = "QTDSTOCK")
    private int qtdstock;
    @Basic
    @Column(name = "CODPEIXE")
    private Integer codpeixe;

    @Lob
    @Column(name = "IMAGEM")
    private byte[] imagem;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @OneToMany(mappedBy = "tipoconservaByCodtipoconserva")
    private Collection<Lote> lotesByCodtipoconserva;
    @ManyToOne
    @JoinColumn(name = "CODPEIXE", referencedColumnName = "CODPEIXE", insertable = false, nullable = false, updatable = false)
    private Peixe peixeByCodpeixe;
    @OneToMany(mappedBy = "tipoconservaByCodtipoconserva")
    private Collection<Tipoconservaencomenda> tipoconservaencomendasByCodtipoconserva;
    @OneToMany(mappedBy = "tipoconservaByCodtipoconserva")
    private Collection<Valornutricional> valornutricionalsByCodtipoconserva;

    public Integer getCodtipoconserva() {
        return codtipoconserva;
    }

    public void setCodtipoconserva(Integer codtipoconserva) {
        this.codtipoconserva = codtipoconserva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoactvenda() {
        return precoactvenda;
    }

    public void setPrecoactvenda(double precoactvenda) {
        this.precoactvenda = precoactvenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdstock() {
        return qtdstock;
    }

    public void setQtdstock(int qtdstock) {
        this.qtdstock = qtdstock;
    }

    public Integer getCodpeixe() {
        return codpeixe;
    }

    public void setCodpeixe(Integer codpeixe) {
        this.codpeixe = codpeixe;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipoconserva that = (Tipoconserva) o;
        return Double.compare(that.precoactvenda, precoactvenda) == 0 && qtdstock == that.qtdstock && Objects.equals(codtipoconserva, that.codtipoconserva) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(codpeixe, that.codpeixe) && Arrays.equals(imagem, that.imagem) && Objects.equals(lotesByCodtipoconserva, that.lotesByCodtipoconserva) && Objects.equals(peixeByCodpeixe, that.peixeByCodpeixe) && Objects.equals(tipoconservaencomendasByCodtipoconserva, that.tipoconservaencomendasByCodtipoconserva) && Objects.equals(valornutricionalsByCodtipoconserva, that.valornutricionalsByCodtipoconserva);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(codtipoconserva, nome, precoactvenda, descricao, qtdstock, codpeixe, lotesByCodtipoconserva, peixeByCodpeixe, tipoconservaencomendasByCodtipoconserva, valornutricionalsByCodtipoconserva);
        result = 31 * result + Arrays.hashCode(imagem);
        return result;
    }

    public Collection<Lote> getLotesByCodtipoconserva() {
        return lotesByCodtipoconserva;
    }

    public void setLotesByCodtipoconserva(Collection<Lote> lotesByCodtipoconserva) {
        this.lotesByCodtipoconserva = lotesByCodtipoconserva;
    }

    public Peixe getPeixeByCodpeixe() {
        return peixeByCodpeixe;
    }

    public void setPeixeByCodpeixe(Peixe peixeByCodpeixe) {
        this.peixeByCodpeixe = peixeByCodpeixe;
    }

    public Collection<Tipoconservaencomenda> getTipoconservaencomendasByCodtipoconserva() {
        return tipoconservaencomendasByCodtipoconserva;
    }

    public void setTipoconservaencomendasByCodtipoconserva(Collection<Tipoconservaencomenda> tipoconservaencomendasByCodtipoconserva) {
        this.tipoconservaencomendasByCodtipoconserva = tipoconservaencomendasByCodtipoconserva;
    }

    public Collection<Valornutricional> getValornutricionalsByCodtipoconserva() {
        return valornutricionalsByCodtipoconserva;
    }

    public void setValornutricionalsByCodtipoconserva(Collection<Valornutricional> valornutricionalsByCodtipoconserva) {
        this.valornutricionalsByCodtipoconserva = valornutricionalsByCodtipoconserva;
    }
}
