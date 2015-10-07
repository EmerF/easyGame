package com.easygame.w2.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by emerson on 17/09/15.
 */
public class CriarToast {



    public static void CriarToast(Context contexto,String texto){
        //Context contexto = getApplicationContext();
        //String texto = "Informe nome do jogador";
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, texto,duracao);
        toast.show();

    }



}
