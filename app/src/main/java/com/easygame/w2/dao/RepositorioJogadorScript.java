package com.easygame.w2.dao;

import android.content.Context;

import com.easygame.w2.dao.RepositorioJogador;
//import com.easygame.w2.db.sq

/**
 * <pre>
 * Repositãrio para jogadors que utiliza o SQLite internamente
 * 
 * Para visualizar o banco pelo adb shell:
 * 
 * &gt;&gt; sqlite3 /data/data/br.livro.android.exemplos.banco/databases/Bancojogador
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
public class RepositorioJogadorScript extends RepositorioJogador {

	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS jogador";

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"DROP TABLE IF EXISTS jogador",
			"create table jogador ( _id integer primary key autoincrement, " +
					"nome text not null,posicao text not null, endereco text not null,telefone text not null);",
			"insert into jogador(nome,endereco,telefone) values('Emerson','Goleiro',9819-7736);",
			"insert into jogador(nome,endereco,telefone) values('Ale','Zagueiro',9955-7556);",
			"insert into jogador(nome,endereco,telefone) values('Fuba','Atacante',9788-7569);"};

	// Nome do banco
	private static final String NOME_BANCO = "easy_game";

	// Controle de versão
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String TABELA_jogador = "jogador";

	// Classe utilitária para abrir, criar, e atualizar o banco de dados
	private SQLiteHelper dbHelper;


	// Cria o banco de dados com um script SQL
	public RepositorioJogadorScript(Context ctx) {
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, RepositorioJogadorScript.NOME_BANCO, RepositorioJogadorScript.VERSAO_BANCO,
				RepositorioJogadorScript.SCRIPT_DATABASE_CREATE, RepositorioJogadorScript.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar tambãm
		db = dbHelper.getWritableDatabase();
	}

	// Fecha o banco
	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
