package com.example.ui.testelayout.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import com.example.ui.testelayout.Adapter.ListaReservaAdapter;
import com.example.ui.testelayout.Modal.Reserva;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorCadastroReserva;
import com.example.ui.testelayout.ServidorHttp.VerificadorLogin;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroReservaActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    public static final String TITULO_APPBAR = "Realizar reservas";
    ImageButton btndata, btnhoraI, btnhoraF;
    Button btnFinalizarReserva;
    TextView textoData, textoHoraI, textoHoraF;
    EditText edNomeLocador, edDrescricaoLocador;
    int ano, mes, dia, hora, minuto;
    int anoFinalI, mesFinalI, diaFinalI, horaFinalI, minutoFinalI;
    int anoFinalF, mesFinalF, diaFinalF, horaFinalF, minutoFinalF;
    private ListaReservaAdapter adapter;
    SharedPreferences preferences;

    public static final String mypreference = "USER_LOGIN";

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
        textoHoraI = (TextView) findViewById(R.id.text_horaioInicial);
       // textoData = (TextView) findViewById(R.id.text_calendario);
        textoHoraF = (TextView) findViewById(R.id.text_horaioFinal);
        edDrescricaoLocador = (EditText) findViewById(R.id.ed_descricao);
        edNomeLocador = (EditText) findViewById(R.id.ed_nome_organizador);


//////////////////////////METODOS ONCLICK////////////////////////////////////////////////////////
        btnFinalizarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /////////////////////////////CADASTRO RESERVA ////////////////////////////////////////////////////////////////
                String nomeString = edNomeLocador.getText().toString().trim();
                String descricaoString  = edDrescricaoLocador.getText().toString().trim();
                String dataString  = textoData.getText().toString().trim();
                String horaInicialString = textoHoraI.getText().toString();
                long horaFinalString  = textoHoraF.getDrawingTime();
                JSONObject reservaJson = new JSONObject();

                try {


                    SharedPreferences preferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    String idOrg = preferences.getString("userId", null);
                    String idSala = preferences.getString("idSala", null);

                    Reserva novaReserva = new Reserva();

                    reservaJson.put("nome",novaReserva.getNomeOrganizador());
                    reservaJson.put("descricao",novaReserva.getDescricao());
                    reservaJson.put("data",novaReserva.getDataCriacao());
                    reservaJson.put("data_hora_inicio",novaReserva.getHoraIncial());
                    reservaJson.put("data_hora_final", novaReserva.getHoraFinal());
                    reservaJson.put("id_sala", idSala);
                    reservaJson.put("id_usuario", idOrg);

                    String reservaEncoded = (Base64.encodeToString(reservaJson.toString().getBytes("UTF-8"), Base64.NO_WRAP));
                    System.out.println(reservaEncoded);
                    String authReturn = "";
                    authReturn = new VerificadorCadastroReserva().execute(reservaEncoded).get();
                    JSONObject reservaObj = new JSONObject(authReturn);


                /* if (reservaJson.has("id_sala") && reservaJson.has("id_usuario") && reservaJson.has("descricao") && reservaJson.has("data_hora_inicio")&& reservaJson.has("data_hora_final")) {

                String descricao = reservaJson.getString("descricao");
                preferences = getSharedPreferences("USER_LOGIN", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("descricao", descricao);
                editor.putString("id_sala", String.valueOf(idSala));
                editor.commit();




            }*/


                } catch(Exception e){
                    e.printStackTrace();
                }






                Intent intent = new Intent(CadastroReservaActivity.this, ReservaSalaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //////////////////////////////////////////////////////////////////

        btnhoraI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                ano = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CadastroReservaActivity.this, CadastroReservaActivity.this, ano, mes, dia);
                datePickerDialog.show();

            }
        });

        btnhoraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                ano = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CadastroReservaActivity.this, CadastroReservaActivity.this, ano, mes, dia);
                datePickerDialog.show();

            }
        });

    }



/////////////////////////////////////////////////////////////////






  /*  private void showHorarioDialogo(final ImageButton btnhoraF) {
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
                textoHoraI.setText(simpleDateFormat.format(calendario.getTime()));


            }
        };

        new DatePickerDialog(CadastroReservaActivity.this, dateSetListener, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();
    }
*/
    private void realizarReservaServidor() {







    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        anoFinalI = year;
        mesFinalI = month +1;
        diaFinalI = dayOfMonth;

        anoFinalF = year;
        mesFinalF = month +1;
        diaFinalF = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(CadastroReservaActivity.this, CadastroReservaActivity.this, hora, minuto, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        horaFinalI = hourOfDay;
        minutoFinalI = minute;
        horaFinalF = hourOfDay;
        minutoFinalF = minute;
        textoHoraI.setText(diaFinalI + "/" + mesFinalI + "/" + anoFinalI + " - " + horaFinalI + ":" + minutoFinalI);


    }
}

