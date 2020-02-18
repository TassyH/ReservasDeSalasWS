package com.example.ui.testelayout.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.example.ui.testelayout.Adapter.ListaReservaAdapter;
import com.example.ui.testelayout.Modal.Reserva;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorCadastroReserva;
import com.example.ui.testelayout.ServidorHttp.VerificadorLogin;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CadastroReservaActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Realizar reservas";
    ImageButton btndata, btnhoraI, btnhoraF;
    Button btnFinalizarReserva;
    TextView textoData, textoHoraI, textoHoraF;
    EditText edNomeLocador, edDrescricaoLocador;
    private long dateInicio, dateFim, dateLong;
    public TextView stringData;
    private ListaReservaAdapter adapter;
    private SharedPreferences preferences;
    private Context context = this;

    public static final String mypreference = "USER_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);
        setTitle(TITULO_APPBAR);




        btnFinalizarReserva = (Button) findViewById(R.id.btFinalReserva);
        textoHoraI = (TextView) findViewById(R.id.text_horaioInicial);
        textoData = (TextView) findViewById(R.id.text_print_data);
        textoHoraF = (TextView) findViewById(R.id.text_horaioFinal);
        edDrescricaoLocador = (EditText) findViewById(R.id.ed_descricao);
        edNomeLocador = (EditText) findViewById(R.id.ed_nome_organizador);


//////////////////////////METODOS ONCLICK////////////////////////////////////////////////////////
        btnFinalizarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /////////////////////////////CADASTRO RESERVA ////////////////////////////////////////////////////////////////
                String nomeString = edNomeLocador.getText().toString().trim();
                String descricaoString = edDrescricaoLocador.getText().toString().trim();
                String authReturn = "";
                SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                String idOrg = preferences.getString("userId", null);
                String idSala = preferences.getString("idSala", null);

                TextView organizador = findViewById(R.id.ed_nome_organizador);
                organizador.setText("Organizador: " + preferences.getString("userName", null));

                JSONObject reservaJson = new JSONObject();

                try {

                    reservaJson.put("nome", nomeString);
                    reservaJson.put("descricao", descricaoString);
                    reservaJson.put("data_hora_inicio", dateInicio);
                    reservaJson.put("data_hora_final", dateFim);
                    reservaJson.put("id_sala", idSala);
                    reservaJson.put("id_usuario", idOrg);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CadastroReservaActivity.this, "Erro ao inserir dados da reserva", Toast.LENGTH_LONG).show();
                }

                try {

                    String reservaEncoded = (Base64.encodeToString(reservaJson.toString().getBytes("UTF-8"), Base64.NO_WRAP));
                    System.out.println(reservaEncoded);

                    authReturn = new VerificadorCadastroReserva().execute(reservaEncoded).get();

                    if (authReturn.equals("Reserva realizada com sucesso")) {
                        Toast.makeText(CadastroReservaActivity.this, "Reserva efetuada com sucesso bro ", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CadastroReservaActivity.this, "Seu cadastro de reserva deu ruim mano", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CadastroReservaActivity.this, " que ta acontecendo meupai", Toast.LENGTH_LONG).show();
                    finish();
                }


                Intent intent = new Intent(CadastroReservaActivity.this, ReservaSalaActivity.class);
                startActivity(intent);
                finish();
            }
        });




        //////////////////////////////////////////////////////////////////
        textoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*stringData = findViewById(R.id.text_print_data);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String data = dateFormat.format(calendarView.getSelectedDate().getDate().getTime());

                    System.out.println("Data em long " + dateLong);
                    stringData.setText(data);*/
            }
        });

        textoHoraI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        textoHoraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


/////////////////////////////////////////////////////////////////
    }


    private void getHora(int hourOfDay, int minute, Calendar calendar, SimpleDateFormat formataHora, TextView hora) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        hora.setText(formataHora.format(calendar.getTime()));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }





}


