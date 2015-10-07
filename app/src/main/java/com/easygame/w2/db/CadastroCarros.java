package com.easygame.w2.db;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.easygame.w2.easygame.R;
import com.easygame.w2.db.Carro;


/**
 * Activity que demonstra o cadastro de carros:
 * 
 * - ListActivity: para listar os carros - RepositorioCarro para salvar os dados
 * no banco - Carro
 * 
 * @author rlecheta
 * 
 */
public class CadastroCarros extends ListActivity {
	protected static final int INSERIR_EDITAR = 1;
	protected static final int BUSCAR = 2;

	//public static RepositorioCarro repositorio;
	public static RepositorioCarro repositorio;

	private List<Carro> carros;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		// Copia o banco de dados da pasta /assets
		CopiaBancoDeDadosPastaAssets rep = new CopiaBancoDeDadosPastaAssets(this);
		rep.criarBancoDeDados();
		//repositorio.
		repositorio = new RepositorioCarroScript(this);
		atualizarLista();
	}

	protected void atualizarLista() {
		// Pega a lista de carros e exibe na tela
		carros = repositorio.listarCarros();

		// Adaptador de lista customizado para cada linha de um carro
		// Fonte est� na biblioteca LivroAndroidCap14-BancoDados-Library
		setListAdapter(new CarroListAdapter(this, carros));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_cadastro_carros, menu);
		//menu.add(0, INSERIR_EDITAR, 0, "Inserir Novo").setIcon(R.mipmap.novo);
		//menu.add(0, BUSCAR, 0, "Buscar").setIcon(R.mipmap.pesquisar);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// Clicou no menu
		switch (item.getItemId()) {
		case R.id.menu_editar:
			// Abre a tela com o formul�rio para adicionar
			startActivityForResult(new Intent(this, EditarCarro.class), INSERIR_EDITAR);
			break;
		case R.id.menu_buscar:
			// Abre a tela para buscar o carro pelo nome
			startActivity(new Intent(this, BuscarCarro.class));
			break;
		}
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int posicao, long id) {
		super.onListItemClick(l, v, posicao, id);
		editarCarro(posicao);
	}

	// Recupera o id do carro, e abre a tela de edi��o
	protected void editarCarro(int posicao) {
		// Usu�rio clicou em algum carro da lista
		// Recupera o carro selecionado
		Carro carro = carros.get(posicao);
		// Cria a intent para abrir a tela de editar
		Intent it = new Intent(this, EditarCarro.class);
		// Passa o id do carro como par�metro
		it.putExtra(Carro.Carros._ID, carro.id);
		// Abre a tela de edi��o
		startActivityForResult(it, INSERIR_EDITAR);
	}

	@Override
	protected void onActivityResult(int codigo, int codigoRetorno, Intent it) {
		super.onActivityResult(codigo, codigoRetorno, it);

		// Quando a activity EditarCarro retornar, seja se foi para adicionar vamos atualizar a lista
		if (codigoRetorno == RESULT_OK) {
			// atualiza a lista na tela
			atualizarLista();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
	}
}