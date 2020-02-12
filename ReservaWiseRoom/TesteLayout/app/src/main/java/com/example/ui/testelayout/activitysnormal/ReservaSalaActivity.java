package com.example.ui.testelayout.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ui.testelayout.Adapter.ListaReservaAdapter;
import com.example.ui.testelayout.Adapter.ListaSalasAdapter;
import com.example.ui.testelayout.Modal.Sala;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorLogin;
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
    public static final String mypreference = "USER_LOGIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_sala);

        final ImageButton btn_infor = findViewById(R.id.btn_infor);
        //  TextView tx_titulo = findViewById(R.id.txt_titulo_sala);
        TextView tx_nome = findViewById(R.id.tx_nome_sala);
        TextView tx_local = findViewById(R.id.tx_local_sala);
        TextView tx_latitude = findViewById(R.id.tx_latitude_sala);
        TextView tx_longitude = findViewById(R.id.tx_longitude_sala);
        TextView tx_quantPessoas = findViewById(R.id.tx_quantPessoas_sala);
        TextView tx_refrigeracao = findViewById(R.id.tx_refrigeracao_sala);
        TextView tx_area_sala = findViewById(R.id.tx_area_sala);
        TextView tx_dataAlteracao = findViewById(R.id.tx_dataAlteracao_sala);
        TextView tx_dataCriacao = findViewById(R.id.tx_dataCriacao_sala);
        TextView tx_midia = findViewById(R.id.tx_possuiMidia_sala);
        //  final ConstraintLayout expandir = findViewById(R.id.expandableView);


/*

              btn_infor.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (expandir.getVisibility()==View.GONE){
                          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                          expandir.setVisibility(View.VISIBLE);
                          btn_infor.setBackgroundResource(R.drawable.icon_btn_cima);
                      } else {
                          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                          expandir.setVisibility(View.GONE);
                          btn_infor.setBackgroundResource(R.drawable.icon_btn_baixo);
                      }
                  }
              });
*/
///////////////////////////////////////////////////////////////////////////////////////////////////

        try {
            SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            String idOrg = preferences.getString("userIdOrganizacao", null);
            String listaSalasFromPref = "";
            listaSalasFromPref = preferences.getString("listaSalas", null);
            Intent in = getIntent();
            Bundle bundle = in.getExtras();
            int position = bundle.getInt("position");
            if (listaSalasFromPref != null) {

                    JSONArray salasJson = new JSONArray(listaSalasFromPref);

                    JSONObject salaJsonObjeto = salasJson.getJSONObject(position);
                    if (salaJsonObjeto.has("nome") && salaJsonObjeto.has("quantidadePessoasSentadas") && salaJsonObjeto.has("id") && salaJsonObjeto.has("possuiMultimidia")&& salaJsonObjeto.has("possuiArcon") && salaJsonObjeto.has("areaDaSala") && salaJsonObjeto.has("longitude")&& salaJsonObjeto.has("latitude")&& salaJsonObjeto.has("dataCriacao")&& salaJsonObjeto.has("dataAlteracao")) {
                        String nome = salaJsonObjeto.getString("nome");
                        int quantPessoas = salaJsonObjeto.getInt("quantidadePessoasSentadas");
                        int idSala = salaJsonObjeto.getInt("id");
                        double area = salaJsonObjeto.getDouble("areaDaSala");
                        double longitude  = salaJsonObjeto.getDouble("longitude") ;
                        double latitude  = salaJsonObjeto.getDouble("latitude");
                        String dataCriacao = salaJsonObjeto.getString("dataCriacao");
                        String dataAlteracao = salaJsonObjeto.getString("dataAlteracao");
                        // midia = salaJsonObjeto.getBoolean("pussuiMultimidia");
                        // String refrigeracao = salaJsonObjeto.getString(String.valueOf("possuiArcon"));


                        tx_nome.setText("sala: " + nome);
                        tx_quantPessoas.setText("Quantidade de pessoas sentadas: " + quantPessoas);
                      //  tx_midia.setText("possui midia : "+midia);
                       // tx_refrigeracao.setText("refrigeracao: "+refrigeracao);
                        tx_area_sala.setText("area da sala: " + area);
                        tx_latitude.setText("latitude: "+latitude);
                        tx_longitude.setText("longitude: "+longitude);
                        tx_dataAlteracao.setText("dataAlteracao: "+dataAlteracao);
                        tx_dataCriacao.setText("dataCriacao: "+dataCriacao);



                    }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }





     /*   SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        String idOrg = preferences.getString("userIdOrganizacao", null);
        String nomeSala = preferences.getString("nome", null);
       *//* String emailUser = preferences.getString("userEmail", null);
        String nomeOrganizacao = preferences.getString("userNomeEmpresa", null);*//*
        tx_nome.setText("Nome: "+nomeSala);
        *//*user_email.setText("Email: "+emailUser);
        user_org.setText("Sua Empresa: "+nomeOrganizacao);*/
/////////////////////////////////////////////////////////////////////////////////////////////////////


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