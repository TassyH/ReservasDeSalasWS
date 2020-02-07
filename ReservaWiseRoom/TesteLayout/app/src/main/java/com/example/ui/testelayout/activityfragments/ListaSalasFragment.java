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

import com.example.ui.testelayout.Adapter.ListaReservaAdapter;
import com.example.ui.testelayout.Adapter.ListaSalasAdapter;
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
    List<Sala> salas = new ArrayList<>();
    List<Sala> listaSalasStrings = new ArrayList<>();
    ListaSalasAdapter adapt;
    ArrayList<String> arraySalas;
    ListaSalasAdapter adpter;

    //rivate List<Sala> salas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_salas, container, false);
        nomeSala = view.findViewById(R.id.item_nome_sala);

        // preferences = getContext().getSharedPreferences("USER_LOGIN", 0);

        // System.out.println(preferences.getAll());


        try {
            SharedPreferences preferences = getContext().getSharedPreferences("USER_LOGIN", 0);
            String idOrg = preferences.getString("userIdOrganizacao", null);

            String verifSalas = "";
            verifSalas = new VerificadorSala().execute(idOrg).get();


            //  List<Sala> salas = new ArrayList<>();// declara fora do oncreate eu acho alguém me ajuda

            if (verifSalas.length() > 0) {
                JSONArray salasJson = new JSONArray(verifSalas);

                System.out.println("nome da sala" + verifSalas);

                for (int i = 0; i < salasJson.length(); i++) {
                    JSONObject salaJsonObjeto = salasJson.getJSONObject(i);
                    if (salaJsonObjeto.has("nome") && salaJsonObjeto.has("localizacao")) {
                        String nome = salaJsonObjeto.getString("nome");
                        String local  =  salaJsonObjeto.getString("localizacao");

                        Sala novaSala = new Sala();
                        novaSala.setNome(nome);
                        novaSala.setLocalizacao(local);
                        salas.add(novaSala);

                    }
                }
                ListView listaDeSalas = view.findViewById(R.id.listview_de_salas);
                listaDeSalas.setAdapter(new ListaSalasAdapter(salas, getContext()));
              /*  ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaSalasStrings);
                listaDeSalas.setAdapter(adapter);*/


                listaDeSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(view.getContext(), ReservaSalaActivity.class);
                        startActivity(intent);
                    }
                });


            } else {
                System.out.println("Deu ruim bro");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/////////////////////////////////////////////////////////////////////////////////////////////////

        return view;
    }


}

