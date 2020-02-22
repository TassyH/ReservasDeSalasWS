package com.example.ui.controledesalas.activityfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.controledesalas.R;

public class ListaReservasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_lista_reserva, container, false);
        TextView textoResev = view.findViewById(R.id.textReservas);

        TextView tx_reservaHoraInicial =  view.findViewById(R.id.item_datahora_inicial);
        TextView tx_reservaHoraFinal =  view.findViewById(R.id.item_datahora_final);
        TextView tx_reservaDescricao = view.findViewById(R.id.item_descricao_reserva);
        TextView tx_reservaNomeSala = view.findViewById(R.id.item_nome_sala);



        return view;
    }
}
