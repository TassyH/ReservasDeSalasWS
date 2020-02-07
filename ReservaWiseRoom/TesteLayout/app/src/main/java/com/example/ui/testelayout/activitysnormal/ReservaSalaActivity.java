package com.example.ui.testelayout.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ui.testelayout.Adapter.ListaReservaAdapter;
import com.example.ui.testelayout.Adapter.ListaSalasAdapter;
import com.example.ui.testelayout.Modal.Sala;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorSala;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReservaSalaActivity extends AppCompatActivity {
    List<Sala> salas = new ArrayList<>();
    ListaReservaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_sala);

              TextView  tx_nome = findViewById(R.id.tx_nome_sala);
              TextView  tx_local = findViewById(R.id.tx_local_sala);
              TextView  tx_latitude = findViewById(R.id.tx_latitude_sala);
              TextView  tx_longitude = findViewById(R.id.tx_longitude_sala);
              TextView  tx_quantPessoas = findViewById(R.id.tx_quantPessoas_sala);
              TextView  tx_refrigeracao = findViewById(R.id.tx_refrigeracao_sala);
              TextView  tx_area_sala = findViewById(R.id.tx_area_sala);
              TextView  tx_dataAlteracao = findViewById(R.id.tx_dataAlteracao_sala);
              TextView  tx_dataCriacao = findViewById(R.id.tx_dataCriacao_sala);
              TextView  tx_midia = findViewById(R.id.tx_possuiMidia_sala);


        SharedPreferences preferences = getSharedPreferences("DATA_EXIBIR", 0);
        TextView tx_titulo = findViewById(R.id.tx_titulo_sala);
        String idOrg  = preferences.getString("userIdOrganizacao", null);

              SharedPreferences.Editor editor = preferences.edit();



                FloatingActionButton botaoNovaReserva = findViewById(R.id.fab_reserva);
                botaoNovaReserva.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ReservaSalaActivity.this, CadastroReservaActivity.class);
                        startActivity(intent);
                    }
                });
            }

    }

