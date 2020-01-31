package com.example.ui.testelayout.activityfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.testelayout.Adapter.ListaSalasAdapter;
import com.example.ui.testelayout.Dao.SalasDAO;
import com.example.ui.testelayout.Modal.Sala;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.activitysnormal.ReservaSalaActivity;

import java.util.List;

public class ListaSalasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_salas, container, false);


        ListView listaDeSalas = view.findViewById(R.id.listview_de_salas);
        List<Sala> salas = new SalasDAO().lista();
        listaDeSalas.setAdapter(new ListaSalasAdapter(salas, view.getContext()));
        listaDeSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ReservaSalaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



}

