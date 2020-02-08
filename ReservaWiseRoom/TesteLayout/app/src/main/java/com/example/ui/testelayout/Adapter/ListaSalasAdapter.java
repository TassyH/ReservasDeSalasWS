package com.example.ui.testelayout.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ui.testelayout.Modal.Sala;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.activityfragments.ListaSalasFragment;

import java.util.List;

public class ListaSalasAdapter extends BaseAdapter {

    private final List<Sala> salas;
    private Context context;

    public ListaSalasAdapter(List<Sala> salas, Context context){
        this.salas = salas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return salas.size();
    }

    @Override
    public Sala getItem(int posicao) {
        return salas.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_lista_salas, parent, false);

        Sala sala = salas.get(posicao);
        TextView nomeSala = viewCriada.findViewById(R.id.item_nome_sala);
        nomeSala.setText(sala.getNome());

        TextView local = viewCriada.findViewById(R.id.item_local_sala);
        local.setText(sala.getLocalizacao());

        TextView quantPessoas = viewCriada.findViewById(R.id.item_quantPessoas_sala);
        quantPessoas.setText(sala.getQuantidadePessoasSentadas());

        /*TextView refrigeracao = viewCriada.findViewById(R.id.item_refrigeracao);
        refrigeracao.setText(sala.getRefrigeracao());*/

        TextView descricao = viewCriada.findViewById(R.id.item_dataCriacao_sala);
        descricao.setText(sala.getDescricao());
        return viewCriada;

    }

}

