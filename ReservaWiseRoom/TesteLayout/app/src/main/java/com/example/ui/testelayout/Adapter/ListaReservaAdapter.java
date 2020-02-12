package com.example.ui.testelayout.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ui.testelayout.Modal.Sala;
import com.example.ui.testelayout.R;

import java.util.List;

public class ListaReservaAdapter extends BaseAdapter {

    private List<Sala> salas;
    private Context context;

    public ListaReservaAdapter(List<Sala> salas, Context context){
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
    public long getItemId(int posicao) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.fragment_lista_reserva, parent, false);

      /*  Sala sala = salas.get(posicao);
        TextView tituloSala = viewCriada.findViewById(R.id.txt_titulo_sala);
        tituloSala.setText(sala.getNome());
*/
        return viewCriada;
    }
}
