package com.easygame.w2.dao;

import java.io.Serializable;

/**
 * Created by emerson on 24/08/15.
 */
public class Jogador implements Serializable {
    private String nome;
    private String posicao;
    private String endereco;
    private String telefone;

    public Jogador(String nome, String posicao, String endereco, String telefone){
        this.nome = nome;
        this.posicao = posicao;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    public Jogador(String nome, String posicao){
        this.nome = nome;
        this.posicao = posicao;

    }
    public  Jogador(){}


    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
