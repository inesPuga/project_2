package com.example.web.Model;

public class TipoConserva {
    private String nome;

    private String descricao;

    private int codtipoconserva;


    private int codpeixe;

    private double precoactvenda;

    private int qtdstock;

    private byte imagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodtipoconserva() {
        return codtipoconserva;
    }

    public void setCodtipoconserva(int codtipoconserva) {
        this.codtipoconserva = codtipoconserva;
    }

    public int getCodpeixe() {
        return codpeixe;
    }

    public void setCodpeixe(int codpeixe) {
        this.codpeixe = codpeixe;
    }

    public double getPrecoactvenda() {
        return precoactvenda;
    }

    public void setPrecoactvenda(double precoactvenda) {
        this.precoactvenda = precoactvenda;
    }

    public int getQtdstock() {
        return qtdstock;
    }

    public void setQtdstock(int qtdstock) {
        this.qtdstock = qtdstock;
    }

    public byte getImagem() {
        return imagem;
    }

    public void setImagem(byte imagem) {
        this.imagem = imagem;
    }
}
