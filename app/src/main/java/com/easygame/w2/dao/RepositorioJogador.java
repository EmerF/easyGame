package com.easygame.w2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.easygame.w2.dao.Jogador;
import com.easygame.w2.db.Carro;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Reposit�rio para carros que utiliza o SQLite internamente
 * 
 * Para visualizar o banco pelo adb shell:
 * 
 * &gt;&gt; sqlite3 /data/data/br.livro.android.exemplos.banco/databases/BancoCarro
 * 
 * &gt;&gt; Mais info dos comandos em: http://www.sqlite.org/sqlite.html
 * 
 * &gt;&gt; .exit para sair
 * 
 * </pre>
 * 
 * @author rlecheta
 * 
 */
public class RepositorioJogador {
	private static final String CATEGORIA = "jogador";

	// Nome do banco
	private static final String NOME_BANCO = "easy_game";
	// Nome da tabela
	public static final String NOME_TABELA = "jogador";

	protected SQLiteDatabase db;

	public RepositorioJogador(Context ctx) {
		// Abre o banco de dados j� existente
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	protected RepositorioJogador() {
		// Apenas para criar uma subclasse...
	}

	// Salva o carro, insere um novo ou atualiza
	public long salvar(Jogador jogador) {
		long id = jogador.id;

		if (id != 0) {
			atualizar(jogador);
		} else {
			// Insere novo
			id = inserir(jogador);
		}


		return id;
	}

	// Insere um novo jogador
	public long inserir(Jogador jogador) {
		ContentValues values = new ContentValues();
		values.put(Jogador.Jogadores.NOME, jogador.nome);
		values.put(Jogador.Jogadores.POSICAO, jogador.posicao);
		values.put(Jogador.Jogadores.ENDERECO, jogador.endereco);
		values.put(Jogador.Jogadores.TELEFONE, jogador.telefone);

		long id = inserir(values);
		return id;
	}

	// Insere um novo carro
	public long inserir(ContentValues valores) {
		long id = db.insert(NOME_TABELA, "", valores);
		return id;
	}

	// Atualiza o jogador no banco. O id do jogador � utilizado.
	public int atualizar(Jogador jogador) {
		ContentValues values = new ContentValues();
		values.put(Jogador.Jogadores.NOME, jogador.nome);
		values.put(Jogador.Jogadores.POSICAO, jogador.posicao);
		values.put(Jogador.Jogadores.ENDERECO, jogador.endereco);
		values.put(Jogador.Jogadores.TELEFONE, jogador.telefone);


		String _id = String.valueOf(jogador.id);

		String where = Jogador.Jogadores._ID + "=?";
		String[] whereArgs = new String[] { _id };

		int count = atualizar(values, where, whereArgs);

		return count;
	}

	// Atualiza o jogador com os valores abaixo
	// A cl�usula where � utilizada para identificar o carro a ser atualizado
	public int atualizar(ContentValues valores, String where, String[] whereArgs) {
		int count = db.update(NOME_TABELA, valores, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros");
		return count;
	}

	// Deleta o jogador com o id fornecido
	public int deletar(long id) {
		String where = Jogador.Jogadores._ID + "=?";

		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };

		int count = deletar(where, whereArgs);

		return count;
	}

	// Deleta o jogaro com os argumentos fornecidos
	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		Log.i(CATEGORIA, "Deletou [" + count + "] registros");
		return count;
	}

	// Busca o carro pelo id
	public Jogador buscarJogador(long id) {
		// select * from carro where _id=?
		Cursor c = db.query(true, NOME_TABELA, Jogador.colunas, Jogador.Jogadores._ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {

			// Posicinoa no primeiro elemento do cursor
			c.moveToFirst();

			Jogador jogador = new Jogador();

			// L� os dados
			jogador.id = c.getLong(0);
			jogador.nome = c.getString(1);
			jogador.posicao = c.getString(2);
			jogador.endereco = c.getString(3);
			jogador.telefone = c.getString(4);

			return jogador;
		}

		return null;
	}

	// Retorna um cursor com todos os jogadores
	public Cursor getCursor() {
		try {
			// select * from carros
			return db.query(NOME_TABELA, Jogador.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os jogadores: " + e.toString());
			return null;
		}
	}

	// Retorna uma lista com todos os jogadores
	public List<Jogador> listarJogadores() {
		Cursor c = getCursor();

		List<Jogador> jogadores = new ArrayList<Jogador>();

		if (c.moveToFirst()) {

			// Recupera os �ndices das colunas
			int idxId = c.getColumnIndex(Jogador.Jogadores._ID);
			int idxNome = c.getColumnIndex(Jogador.Jogadores.NOME);
			int idxPosicao = c.getColumnIndex(Jogador.Jogadores.POSICAO);
			int idxEndereco = c.getColumnIndex(Jogador.Jogadores.ENDERECO);
			int idxTelefone = c.getColumnIndex(Jogador.Jogadores.TELEFONE);

			// Loop at� o final
			do {
				Jogador jogador = new Jogador();
				jogadores.add(jogador);

				// recupera os atributos de carro
				jogador.id = c.getLong(idxId);
				jogador.nome = c.getString(idxNome);
				jogador.posicao = c.getString(idxPosicao);
				jogador.endereco = c.getString(idxEndereco);
				jogador.telefone = c.getString(idxTelefone);

			} while (c.moveToNext());
		}

		return jogadores;
	}

	// Busca o jogador pelo nome "select * from jogador where nome=?"
	public Jogador buscarJogadorPorNome(String nome) {
		Jogador jogador = null;

		try {
			// Idem a: SELECT _id,nome,placa,ano from CARRO where nome = ?
			Cursor c = db.query(NOME_TABELA, Jogador.colunas, Jogador.Jogadores.NOME + "='" + nome + "'", null, null, null, null);

			// Se encontrou...
			if (c.moveToNext()) {

				jogador = new Jogador();

				// utiliza os métodos getLong(), getString(), getInt(), etc para recuperar os valores
				jogador.id = c.getLong(0);
				jogador.nome = c.getString(1);
				jogador.posicao = c.getString(2);
				jogador.endereco = c.getString(3);
				jogador.telefone = c.getString(4);
			}
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar o jogador pelo nome: " + e.toString());
			return null;
		}

		return jogador;
	}

	// Busca um jogador utilizando as configura��es definidas no
	// SQLiteQueryBuilder
	// Utilizado pelo Content Provider de carro
	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) {
		Cursor c = queryBuilder.query(this.db, projection, selection, selectionArgs, groupBy, having, orderBy);
		return c;
	}

	// Fecha o banco
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
}
