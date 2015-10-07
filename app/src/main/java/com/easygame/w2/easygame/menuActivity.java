package com.easygame.w2.easygame;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.easygame.w2.db.BuscarCarro;
import com.easygame.w2.db.CadastroCarros;

public class menuActivity extends Activity {
    private  static final  String[] nomes = new String[]{
            "Separar Times","Cadastrar Jogadores","Buscar Participantes","Sair"
    };
    private TextView tituloMenu;
    //private ListView listMenu;
    //private ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tituloMenu = (TextView)findViewById(R.id.tituloMenuPrincipal);
        //listMenu = (ListView)findViewById(R.id.listViewMenu);
        Button button =(Button) findViewById(R.id.btMenuSepararTimes);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(this,SepararTimesActivity.class));
                Intent it = new Intent(menuActivity.this,SepararTimesActivity.class);
                startActivity(it);
            }
        });
        button =(Button) findViewById(R.id.btMenuBuscarPraticantes);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(this,SepararTimesActivity.class));
                Intent it = new Intent(menuActivity.this,BuscarPraticantesActivity.class);
                startActivity(it);
            }
        });
        button =(Button) findViewById(R.id.btMenuBuscarPraticantes);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(this,SepararTimesActivity.class));
                // setar o profile = null e carregar tela inicial do Login
                Intent it = new Intent(menuActivity.this,BuscarPraticantesActivity.class);
                startActivity(it);
            }
        });
        button =(Button) findViewById(R.id.btMenuCarros);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(this,SepararTimesActivity.class));
                // setar o profile = null e carregar tela inicial do Login
                Intent it = new Intent(menuActivity.this, CadastroCarros.class);
                startActivity(it);
            }
        });

        //button.setOnClickListener(new Button().onCl
        //adapter = new ArrayAdapter<String>(this, R.layout.activity_menu,nomes);
        //this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nomes));
        //listMenu.setAdapter(adapter);
        //this.setListAdapter(new ArrayAdapter<String>());



    }
    public void Tela(String tela){
        try {
            //startActivity(new Intent(this, tela.class));
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    /*@Override
    protected  void onListItemClick(ListView l,View v, int position, long id){
        switch (position){
            case 0:
                startActivity(new Intent(this,SepararTimesActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,BuscarPraticantesActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,BuscarPraticantesActivity.class));
                break;
            case 3:
                //startActivity(new Intent(this,BuscarPraticantesActivity.class));
                finish();

        }*/

    }


//}
