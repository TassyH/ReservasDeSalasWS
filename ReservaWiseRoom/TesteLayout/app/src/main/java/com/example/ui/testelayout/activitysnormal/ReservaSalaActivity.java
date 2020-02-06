package com.example.ui.testelayout.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ui.testelayout.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReservaSalaActivity extends AppCompatActivity {
    TextView tx_nome, tx_local, tx_quantPessoas, tx_longitude, tx_latitude, tx_refrigeracao, tx_midia, tx_dataCriacao, tx_dataAlteracao, tx_area_sala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_sala);

        tx_nome = findViewById(R.id.tx_nome_sala);
        tx_local = findViewById(R.id.tx_local_sala);
        tx_latitude = findViewById(R.id.tx_latitude_sala);
        tx_longitude = findViewById(R.id.tx_longitude_sala);
        tx_quantPessoas = findViewById(R.id.tx_quantPessoas_sala);
        tx_refrigeracao = findViewById(R.id.tx_refrigeracao_sala);
        tx_area_sala = findViewById(R.id.tx_area_sala);
        tx_dataAlteracao = findViewById(R.id.tx_dataAlteracao_sala);
        tx_dataCriacao = findViewById(R.id.tx_dataCriacao_sala);
        tx_midia = findViewById(R.id.tx_possuiMidia_sala);

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
