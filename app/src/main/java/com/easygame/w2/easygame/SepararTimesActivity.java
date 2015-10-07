package com.easygame.w2.easygame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.easygame.w2.adapter.AdapterListView;
import com.easygame.w2.adapter.UsersAdapter;
import com.easygame.w2.dao.Jogador;
import com.easygame.w2.db.CadastroCarros;
import com.easygame.w2.utils.CriarToast;

import java.util.ArrayList;

public class SepararTimesActivity extends Activity implements AdapterView.OnItemClickListener {
    private static final String[] posicoes = new String[]{
            "Goleiro", "Zagueiro", "Ala", "Meio-Campo", "Atacante"
    };
    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<Jogador> itens;

    //private ArrayList<Jogador> jogadores;
    // Criar objeto jogador com os atributos do jogador descritos na tela de cadastro e depois modificar esse array
    // Adicionar a posição e o nome ao jogador e depois jogar esse objeto no list view


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separar_times);


        final Spinner combo = (Spinner) findViewById(R.id.comboPosicoes);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, posicoes);
        //AutoCompleteTextView clubes = (AutoCompleteTextView) findViewById(R.id.posicoes);
        //clubes.setAdapter(adp);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        combo.setAdapter(adp);
        //List View
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        createListView();


        Button buttonMenuCarros =(Button) findViewById(R.id.btMenuCarros);
        buttonMenuCarros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                abrirMenuCarros();
            }
        });

        Button buttonAdicionarJogador =(Button) findViewById(R.id.btAdicionarJogador);
        buttonAdicionarJogador.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //createListView();
                String nome;
                String posicao;
                EditText txtNome = (EditText)findViewById(R.id.txtNomeJogador);
                nome = txtNome.getText().toString();
                if(!nome.equals("")){
                    Spinner txtPos = (Spinner)findViewById(R.id.comboPosicoes);
                    posicao = txtPos.getSelectedItem().toString();
                    addJogador(nome,posicao);

                    txtNome.setText("");

                }else{
                    Context c = getApplicationContext();
                    CriarToast.CriarToast(c,"Informe nome do Jogador");


                    //Leia mais em: Exibindo mensagens no Android com a classe Toast http://www.devmedia.com.br/exibindo-mensagens-no-android-com-a-classe-toast/26668#ixzz3m1x6If4N

                }



            }
        });

        //
    }
    private void addJogador(String nome, String posicao){
        Jogador j = new Jogador(nome, posicao);
        itens.add(j);
        adapterListView.notifyDataSetChanged();


    }
    private void abrirMenuCarros(){
        startActivity(new Intent(this, CadastroCarros.class));
    }

    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<Jogador>();
        Jogador item1 = new Jogador("Nome", "Posição");

        itens.add(item1);


        //Cria o adapter
        adapterListView = new AdapterListView(this, itens);

        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para rolagem.
       listView.setCacheColorHint(Color.BLACK);
    }

    @Override
     public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        //Pega o item que foi selecionado.
        Jogador item = adapterListView.getItem(arg2);
        //Demostração
        Toast.makeText(this, "Você Clicou no jogador: " + item.getNome(), Toast.LENGTH_LONG).show();
    }
}