package com.example.ui.testelayout.Modal;

import java.io.Serializable;

public class Sala implements Serializable {

    private int id;
    private int idOrganizao;

    private String nome;
    private String imagem;
    private String localizacao;
    private String longitude;
    private String latitude;

    private boolean possuiMidia;
    private double area;
    private boolean refrigeracao;
    private String descricao;
    private String quantidadePessoasSentadas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrganizao() {
        return idOrganizao;
    }

    public void setIdOrganizao(int idOrganizao) {
        this.idOrganizao = idOrganizao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    public boolean getPossuiMidia() {
        return possuiMidia;
    }

    public void setPossuiMidia(boolean possuiMidia) {
        this.possuiMidia = possuiMidia;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean getRefrigeracao() {
        return refrigeracao;
    }

    public void setRefrigeracao(boolean refrigeracao) {
        this.refrigeracao = refrigeracao;
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



