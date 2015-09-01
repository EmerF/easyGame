package com.easygame.w2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.easygame.w2.dao.Jogador;
import com.easygame.w2.easygame.R;

import java.util.ArrayList;

/**
 * Created by emerson on 31/08/15.
 */
public class UsersAdapter extends ArrayAdapter<Jogador> {
    public UsersAdapter(Context context, ArrayList<Jogador> users) {
        super(context, 0, users);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Jogador jogador = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_user, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.txtNome);
        TextView tvHome = (TextView) convertView.findViewById(R.id.txtPosicao);
        // Populate the data into the template view using the data object
        tvName.setText(jogador.getNome());
        tvHome.setText(jogador.getPosicao());
        // Return the completed view to render on screen
        return convertView;
    }
}
