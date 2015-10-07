package com.easygame.w2.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;
import com.easygame.w2.utils.*;
/**
 * Copia o banco de dados da pasta assets para a pasta
 * /data/data/pacote_da_applicacao/databases
 * 
 * @author Ricardo Lecheta
 * 
 */
public class CopiaBancoDeDadosPastaAssets extends RepositorioCarro {

	// Pasta onde o banco de dados é salvo. Contém o nome do pacote da aplicação.
	//private static String DB_PATH = "/data/data/br.livro.android.cap14.banco/databases/";
	private static String DB_PATH = "/data/data/com.easygame.w2.easygame/databases/";

	private static String NOME_BANCO = "livro_android";

	private final Context context;

	public CopiaBancoDeDadosPastaAssets(Context context) {
		this.context = context;
	}

	/**
	 * Cria o banco de dados se precisar. Se ele não existir, o banco da pasta assets é copiado para a aplicação
	 */
	public void criarBancoDeDados() {

		try {
			boolean existe = verificaSeBancoDeDadosExiste();

			if (existe) {
				// Banco de dados existe, então não fazemos nada
			} else {

				// For�a a criação do banco de dados padrão, para depois fazermos a cópia e sobrescrever
				// Ao chamar isso o m�todo onCreate(db) ser� chamado, mas não vamos implementar nada l�.
				SQLiteDatabase db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
				db.close();

				// Copia o banco da pasta /assets para a pasta da aplicação em /data/data/pacote_da_applicacao/databases
				copiaBancoDeDados();
			}
		} catch (Exception e) {
			Log.e("livro","Erro ao copiar: " + e.getMessage(), e);
		}
	}

	/**
	 * Verifica se o banco de dados já foi criado.
	 */
	private boolean verificaSeBancoDeDadosExiste() {
		SQLiteDatabase db = null;
		try {
			// RepositorioCarroAssets
			String path = DB_PATH + NOME_BANCO;
			db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// Opa, se deu erro é porque ainda não existe
			e.printStackTrace();
		}

		if (db != null) {
			db.close();
		}

		boolean existe = db != null ? true : false;
		return existe;
	}

	/**
	 * Copia o arquivo /assets/livro_android para a pasta interna do banco de dados da aplicação
	 * */
	private void copiaBancoDeDados() throws IOException {

		// Abre o arquivo da pasta /assets para ler e copiar para a aplicação
		InputStream in = context.getAssets().open(NOME_BANCO);

		// Caminho do arquivo que será: /data/data/br.livro.android.cap14.banco/databases/livro_android
		String arquivo = DB_PATH + NOME_BANCO;

		// Cria a OutputStream para escrever 
		OutputStream out = new FileOutputStream(arquivo);

		// Le a inputstream
		byte[] bytes = IOUtils.toBytes(in);
		
		// Escreve na OutputStream (para criar o arquivo)
		out.write(bytes);
		out.flush();
		out.close();
		
		Toast.makeText(context, "Banco copiado com sucesso", Toast.LENGTH_SHORT).show();
	}

	public SQLiteDatabase getDatabase() {
		SQLiteDatabase db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);		
		return db;
	}
}
