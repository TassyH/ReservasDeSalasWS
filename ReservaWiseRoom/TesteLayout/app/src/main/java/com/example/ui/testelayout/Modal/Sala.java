package com.example.ui.testelayout.Modal;

import java.io.Serializable;

public class Sala implements Serializable {
    private final String nome;
    private final String imagem;
    private final String localizacao;
    private final int id;
    private final boolean possuiMidia;
    private double area;
    private final boolean refrigeracao, estado;
    String descricao;
    private String quantidadePessoasSentadas;


    public Sala(int id, String nome, String imagem, Boolean estado, String localizacao, boolean refrigeracao, String quantidadePessoasSentadas, boolean possuiMidia, String descricao) {
        this.nome = nome;
        this.estado = estado;
        this.imagem = imagem;
        this.localizacao = localizacao;
        this.id = id;
        this.quantidadePessoasSentadas = quantidadePessoasSentadas;
        this.possuiMidia = possuiMidia;
        this.refrigeracao = refrigeracao;
        this.descricao = descricao;

    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isRefrigeracao() {
        return refrigeracao;
    }

    public String getQuantidadePessoasSentadas() {
        return quantidadePessoasSentadas;
    }

    public void setQuantidadePessoasSentadas(String quantidadePessoasSentadas) {
        this.quantidadePessoasSentadas = quantidadePessoasSentadas;
    }

    public String getImagem() {
        return imagem;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public boolean isPossuiMidia() {
        return possuiMidia;
    }

    public double getArea() {
        return area;
    }



}
