package com.example.ui.testelayout.Modal;

import java.io.Serializable;

public class Reserva implements Serializable {

    private String nomeOrganizador;
    private String horaIncial;
    private String horaFinal;
    private int id;
    private String descricao;
    private String dataCriacao;
    private String dataAlteracao;

    public Reserva (int id, String nomeOrganizador, String horaFinal, String horaIncial, String descricao, String dataAlteracao, String dataCriacao){
        this.dataAlteracao = dataAlteracao;
        this.dataCriacao = dataCriacao;
        this.horaIncial = horaIncial;
        this.horaFinal = horaFinal;
        this.descricao = descricao;
        this.nomeOrganizador = nomeOrganizador;
        this.id = id;
    }

    public String getNomeOrganizador() {
        return nomeOrganizador;
    }

    public void setNomeOrganizador(String nomeOrganizador) {
        this.nomeOrganizador = nomeOrganizador;
    }

    public String getHoraIncial() {
        return horaIncial;
    }

    public void setHoraIncial(String horaIncial) {
        this.horaIncial = horaIncial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }



}
