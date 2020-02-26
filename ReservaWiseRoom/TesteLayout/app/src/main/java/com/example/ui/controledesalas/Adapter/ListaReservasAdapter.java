package com.example.ui.controledesalas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ui.controledesalas.Modal.Reserva;
import com.example.ui.controledesalas.Modal.Sala;
import com.example.ui.controledesalas.R;

import java.util.List;

public class ListaReservasAdapter extends BaseAdapter {

    private final List<Reserva> reservas;
    private Context context;

    public ListaReservasAdapter(List<Reserva> reservas, Context context){
        this.reservas = reservas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_lista_reservas, parent, false);

        Reserva reserva = reservas.get(posicao);
        TextView nomeLocador = viewCriada.findViewById(R.id.item_nome_sala);
        nomeLocador.setText(reserva.getNomeOrganizador());

        TextView horaInicial = viewCriada.findViewById(R.id.item_datahora_inicial);
        horaInicial.setText(reserva.getHoraIncial());

        TextView horaFinal = viewCriada.findViewById(R.id.item_datahora_inicial);
        horaFinal.setText(reserva.getHoraFinal());

        TextView descricao = viewCriada.findViewById(R.id.item_descricao_reserva);
        descricao.setText(reserva.getDescricao());

        return viewCriada;
    }
}
