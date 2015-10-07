package com.easygame.w2.dao;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by emerson on 24/08/15.
 */
public class Jogador implements Serializable {
    /**
     * Pacote do Content Provider. Precisa ser �nico.
     */
    public static final String AUTHORITY = "br.easygame.w2.provider.jogador";
    public long id;
    public String nome;
    public String posicao;
    public String endereco;
    public String telefone;

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
    public static String[] colunas = new String[] { Jogadores._ID, Jogadores.NOME, Jogadores.POSICAO, Jogadores.TELEFONE };






    public Jogador(long id, String nome,String posicao, String endereco, String telefone) {
        super();
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    public  Jogador(){}

    /**
     * Classe interna para representar as colunas e ser utilizada por um Content
     * Provider
     *
     * Filha de BaseColumns que j� define (_id e _count), para seguir o padr�o
     * Android
     */
    public static final class Jogadores implements BaseColumns {

        // N�o pode instanciar esta Classe
        private Jogadores() {
        }

        // content://br.livro.android.provider.carro/carros
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/jogadores");

        // Mime Type para todos os carros
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.jogadores";

        // Mime Type para um �nico carro
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.jogadores";

        // Ordena��o default para inserir no order by
        public static final String DEFAULT_SORT_ORDER = "_id ASC";

        public static final String NOME = "nome";
        public static final String POSICAO = "posicao";
        public static final String ENDERECO = "endereco";
        public static final String TELEFONE = "telefone";

        // M�todo que constr�i uma Uri para um Carro espec�fico, com o seu id
        // A Uri � no formato "content://br.livro.android.provider.carro/carros/id"
        public static Uri getUriId(long id) {
            // Adiciona o id na URI default do /carros
            Uri uriJogador = ContentUris.withAppendedId(Jogadores.CONTENT_URI, id);
            return uriJogador;
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Posição: " + posicao + ", Endereço: " + endereco +",Telefone: " + telefone;
    }

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
