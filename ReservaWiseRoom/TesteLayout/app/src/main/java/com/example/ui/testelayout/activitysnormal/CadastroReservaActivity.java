package com.example.ui.testelayout.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ui.testelayout.Adapter.ListaReservaAdapter;
import com.example.ui.testelayout.Modal.Reserva;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorCadastroReserva;
import com.example.ui.testelayout.ServidorHttp.VerificadorLogin;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroReservaActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Realizar reservas";
    ImageButton btndata, btnhoraI, btnhoraF;
    Button btnFinalizarReserva;
    TextView textoData, textoHoraI, textoHoraF;
    EditText edNomeLocador, edDrescricaoLocador;
    private ListaReservaAdapter adapter;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);
        setTitle(TITULO_APPBAR);
        realizarReservaServidor();

        btndata = (ImageButton) findViewById(R.id.btn_select_data);
        btnFinalizarReserva = (Button) findViewById(R.id.btFinalReserva);
        btnhoraI = (ImageButton) findViewById(R.id.btn_select_horaIcial);
        btnhoraF = (ImageButton) findViewById(R.id.btn_select_horaFinal);
        textoData = (TextView) findViewById(R.id.text_calendario);
        textoHoraI = (TextView) findViewById(R.id.text_horaioInicial);
        textoHoraF = (TextView) findViewById(R.id.text_horaioFinal);
        edDrescricaoLocador = (EditText) findViewById(R.id.ed_nome_organizador);
        edNomeLocador = (EditText) findViewById(R.id.ed_descricao);

/////////////////////////////CADASTRO RESERVA ////////////////////////////////////////////////////////////////
        String idUserString = edNomeLocador.getText().toString().trim();
        String descricaoString  = edDrescricaoLocador.getText().toString().trim();
        String dataString  = textoData.getText().toString().trim();
        String horaInicialString = textoHoraI.getText().toString().trim();
        String horaFinalString  = textoHoraF.getText().toString().trim();
        JSONObject reservaJson = new JSONObject();

        try {

            reservaJson.put("id_user", idUserString);
            reservaJson.put("descricao", descricaoString);
            reservaJson.put("data", dataString);
            reservaJson.put("horaInicial", horaInicialString);
            reservaJson.put("horaFinal", horaFinalString);

            String reservaDecoded = new String(Base64.encodeToString(reservaJson.toString().getBytes("UTF-8"), Base64.NO_WRAP));
            System.out.println("texugos " + reservaJson.toString());
            String authReturn = "";
            authReturn = new VerificadorCadastroReserva().execute(reservaDecoded).get();
            JSONObject userObj = new JSONObject(reservaDecoded);

            if (reservaJson.has("id_sala") && reservaJson.has("id_usuario") && reservaJson.has("descricao") && reservaJson.has("data_hora_inicio")&& reservaJson.has("data_hora_final")&& reservaJson.has("ativo")) {
                int idSala = reservaJson.getInt("id_sala");
                int idUsuario = reservaJson.getInt("id_usuario");
                String descricao = reservaJson.getString("descricao");

                preferences = getSharedPreferences("USER_LOGIN", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("descricao", descricao);
                editor.putString("id_sala", String.valueOf(idSala));
                editor.putString("id_usuario", String.valueOf(idUsuario));
                editor.commit();

                Reserva novaReserva = new Reserva();
            }

            System.out.println(preferences.getString("descricao", null));
            System.out.println(preferences.getString("id_sala", null));
            System.out.println(preferences.getString("id_usuario", null));

            } catch(Exception e){
                e.printStackTrace();
            }




//////////////////////////METODOS ONCLICK////////////////////////////////////////////////////////
        btnFinalizarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroReservaActivity.this, ReservaSalaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //////////////////////////////////////////////////////////////////
        btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(btndata);

            }
        });

        btnhoraI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(btnhoraI);
                ;
            }
        });

        btnhoraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHorarioDialogo(btnhoraF);
            }
        });

    }



/////////////////////////////////////////////////////////////////


    private void showTimeDialog(final ImageButton btnhoraI) {
        final Calendar calendario = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendario.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendario.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                textoHoraI.setText(simpleDateFormat.format(calendario.getTime()));
            }
        };

        new TimePickerDialog(CadastroReservaActivity.this, timeSetListener, calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), false).show();
    }

    private void showHorarioDialogo(final ImageButton btnhoraF) {
        final Calendar calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener horaSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int horaDoDia, int minuto) {
                calendar.set(Calendar.HOUR_OF_DAY, horaDoDia);
                calendar.set(Calendar.MINUTE, minuto);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                textoHoraF.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(CadastroReservaActivity.this, horaSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(final ImageButton btndata) {
        final Calendar calendario = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, month);
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                textoData.setText(simpleDateFormat.format(calendario.getTime()));

            }
        };

        new DatePickerDialog(CadastroReservaActivity.this, dateSetListener, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void realizarReservaServidor() {







    }




}

