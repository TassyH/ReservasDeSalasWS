package com.example.ui.controledesalas.activitysnormal;

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

import com.example.ui.controledesalas.Adapter.ListaReservaAdapter;
import com.example.ui.controledesalas.Modal.Sala;
import com.example.ui.controledesalas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        TextView tx_reservaHoraInicial =  findViewById(R.id.item_datahora_inicial);
        TextView tx_reservaHoraFinal =  findViewById(R.id.item_datahora_final);
        TextView tx_reservaDescricao = findViewById(R.id.item_descricao_reserva);
        TextView tx_reservaNomeSala = findViewById(R.id.item_nome_sala);
        final ConstraintLayout expandir = findViewById(R.id.layoutExpand);
        final CardView cardView = findViewById(R.id.card_reserva);
        ListView reservaListview = findViewById(R.id.listViewReservas);




              btn_infor.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (expandir.getVisibility()==View.GONE){
                          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                          expandir.setVisibility(View.VISIBLE);
                          btn_infor.setBackgroundResource(R.drawable.icon_btn_baixo);
                         // btn_infor.setVisibility(R.drawable.icon_btn_cima);

                      } else {
                          TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                          expandir.setVisibility(View.GONE);
                          btn_infor.setBackgroundResource(R.drawable.icon_btn_baixo);

                      }


                  }
              });

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

                System.out.println("meudeus"+position);

                    JSONArray salasJson = new JSONArray(listaSalasFromPref);

                    JSONObject salaJsonObjeto = salasJson.getJSONObject(position);
                    if (salaJsonObjeto.has("nome") && salaJsonObjeto.has("quantidadePessoasSentadas") && salaJsonObjeto.has("id") && salaJsonObjeto.has("possuiMultimidia")&& salaJsonObjeto.has("possuiArcon") && salaJsonObjeto.has("areaDaSala") && salaJsonObjeto.has("longitude")&& salaJsonObjeto.has("latitude")&& salaJsonObjeto.has("dataCriacao")&& salaJsonObjeto.has("dataAlteracao")&& salaJsonObjeto.has("localizacao")) {
                        String nome = salaJsonObjeto.getString("nome");
                        String local = salaJsonObjeto.getString("localizacao");
                        int quantPessoas = salaJsonObjeto.getInt("quantidadePessoasSentadas");
                        int idSala = salaJsonObjeto.getInt("id");
                        double area = salaJsonObjeto.getDouble("areaDaSala");
                        double longitude  = salaJsonObjeto.getDouble("longitude") ;
                        double latitude  = salaJsonObjeto.getDouble("latitude");
                        //String dataCriacao = salaJsonObjeto.getString("dataCriacao");
                       // String dataAlteracao = salaJsonObjeto.getString("dataAlteracao");
                        //boolean midia = salaJsonObjeto.getBoolean("pussuiMultimidia");
                       // boolean refrigeracao = salaJsonObjeto.getBoolean(String.valueOf("possuiArcon"));

                        preferences = getSharedPreferences("USER_LOGIN", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("idSala", Integer.toString(idSala));
                        editor.commit();


                        tx_nome.setText(nome);
                        tx_local.setText(" Localizacao da sala"+local);
                        tx_quantPessoas.setText("Quantidade de pessoas sentadas: " + quantPessoas);
                      //  tx_midia.setText("possui midia : "+midia);
                       // tx_refrigeracao.setText("refrigeracao: "+refrigeracao);
                        tx_area_sala.setText(" Area da sala: " + area);
                        tx_latitude.setText(" Latitude: "+latitude);
                        tx_longitude.setText("Longitude: "+longitude);
                        //tx_dataAlteracao.setText("dataAlteracao: "+dataAlteracao);
                       // tx_dataCriacao.setText("dataCriacao: "+dataCriacao);



                    }

                System.out.println(preferences.getString("idSala", null));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }





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



