package com.example.ui.testelayout.Modal;

import java.io.Serializable;

public class Sala implements Serializable {
    private  String nome;
    private  String imagem;
    private String localizacao;
    private  String longitude;
    private String latitude;
    private  int id;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = this.nome;
    }

    public String getImagem() {
        return imagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public String getPossuiMidia() {
        return possuiMidia;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getRefrigeracao() {
        return refrigeracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidadePessoasSentadas() {
        return quantidadePessoasSentadas;
    }

    public void setQuantidadePessoasSentadas(String quantidadePessoasSentadas) {
        this.quantidadePessoasSentadas = quantidadePessoasSentadas;
    }

    private  String possuiMidia;
    private double area;
    private  String refrigeracao;
    private String descricao;
    private String quantidadePessoasSentadas;


   /* public Sala(int id, String nome, String imagem, Boolean estado, String localizacao, String refrigeracao, String quantidadePessoasSentadas, String possuiMidia, String descricao, String longitude, String latitude) {
        this.nome = nome;
        this.imagem = imagem;
        this.localizacao = localizacao;
        this.id = id;
        this.quantidadePessoasSentadas = quantidadePessoasSentadas;
        this.possuiMidia = possuiMidia;
        this.refrigeracao = refrigeracao;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;*/

    }

   /* public String getDescricao() {
        return descricao;
    }

    public String isRefrigeracao() {
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

    public String getLocalizacao() {
        return localizacao;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String isPossuiMidia() {
        return possuiMidia;
    }

    public double getArea() {
        return area;
    }
*/



