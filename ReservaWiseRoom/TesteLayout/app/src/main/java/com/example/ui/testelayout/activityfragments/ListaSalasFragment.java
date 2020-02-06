package com.example.ui.testelayout.activityfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;

import com.example.ui.testelayout.Adapter.ListaSalasAdapter;
import com.example.ui.testelayout.Dao.SalasDAO;
import com.example.ui.testelayout.Modal.Sala;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorSala;
import com.example.ui.testelayout.activitysnormal.ReservaSalaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaSalasFragment extends Fragment {
    
   TextView nomeSala;
    SharedPreferences preferences;
    List<Sala> salas = new ArrayList<>();
    ArrayAdapter adapter;
    ListView listaDeSalas;
    ArrayList<Sala> arraySalas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_salas, container, false);
        nomeSala = view.findViewById(R.id.item_nome_sala);



        try{
             String verifSalas = null;
             preferences = getContext().getSharedPreferences("USER_LOGIN", 0);
             verifSalas= new VerificadorSala().execute(preferences.getString("userIdOrganizacao", null)).get();
             System.out.println(verifSalas);
             JSONArray salasJson = new JSONArray(verifSalas);

           // List<Sala> salas = new ArrayList<>(); declara fora do oncreate eu acho alguém me ajuda

            if (salasJson.length() > 0) {

                for (int i = 0; i < salasJson.length(); i++) {
                    JSONObject salaJSon = salasJson.getJSONObject(i);

                    String nome = salaJSon.getString("nome");
                    Sala sala = new Sala();
                    sala.setNome(nome);

                    salas.add(sala);
                    arraySalas.add(sala.getNome());

                }
                listaDeSalas = view.findViewById(R.id.listview_de_salas);
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arraySalas);
                listaDeSalas.setAdapter(adapter);


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }





          /* listaDeSalas.setAdapter(new ListaSalasAdapter(salas, view.getContext()));
          preferences = getContext().getSharedPreferences("USER_LOGIN", 0);

        JSONObject usuarioJSON = new JSONObject();
        try {
            JSONObject organizacao = usuarioJSON.getJSONObject("idOrganizacao");
            int idOrganizacao = organizacao.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

*/
/////////////////////////////////////////////////////////////////////////////////////////////////
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

