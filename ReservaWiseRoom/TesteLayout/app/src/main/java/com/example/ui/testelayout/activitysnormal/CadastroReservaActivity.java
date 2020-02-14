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

import com.example.ui.testelayout.Adapter.ListaReservaAdapter;
import com.example.ui.testelayout.Modal.Reserva;
import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorCadastroReserva;
import com.example.ui.testelayout.ServidorHttp.VerificadorLogin;

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
    int ano, mes, dia, hora, minuto;
    int anoFinalI, mesFinalI, diaFinalI, horaFinalI, minutoFinalI;
    int anoFinalF, mesFinalF, diaFinalF, horaFinalF, minutoFinalF;
    private long dateInicio, dateFim, dateLong;
    public TextView stringData;
    private ListaReservaAdapter adapter;
    SharedPreferences preferences;
    private Context context = this;

    public static final String mypreference = "USER_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);
        setTitle(TITULO_APPBAR);
        getDataCadastroReserva(dateLong);


        btndata = (ImageButton) findViewById(R.id.btn_select_data);
        btnFinalizarReserva = (Button) findViewById(R.id.btFinalReserva);
        btnhoraI = (ImageButton) findViewById(R.id.btn_select_horaIcial);
        btnhoraF = (ImageButton) findViewById(R.id.btn_select_horaFinal);
        textoHoraI = (TextView) findViewById(R.id.text_horaioInicial);
        textoData = (TextView) findViewById(R.id.text_calendario);
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
                    JSONObject reservaObj = new JSONObject(authReturn);

                    if (authReturn.equals("Reserva realizada com sucesso mano")) {
                        Toast.makeText(CadastroReservaActivity.this, "Reserva efetuada com sucesso bro ", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CadastroReservaActivity.this, "Seu cadastro de reserva deu ruim mano", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CadastroReservaActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }


                Intent intent = new Intent(CadastroReservaActivity.this, ReservaSalaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //////////////////////////////////////////////////////////////////

        btndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnhoraI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendario = Calendar.getInstance();
                final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");
                calendario.setTimeInMillis(getDataCadastroReserva(dateLong));
                final int hora = calendario.get(Calendar.HOUR_OF_DAY);
                final int min = calendario.get(Calendar.MINUTE);

                if (v == btnhoraI) {
                  //  TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        TimePickerDialog.OnTimeSetListener horaSetListener = new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            getHora(hourOfDay, minute, calendario, formatHora, textoHoraI);
                            dateInicio = calendario.getTime().getTime();
                            System.out.println("data e hora inicio " + dateInicio);

                            //textoHoraI.setText(formatHora.format(calendario.getTime()));
                        }
                    };
                        new TimePickerDialog(CadastroReservaActivity.this, horaSetListener, calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), false).show();
                }

           /* final Calendar calendar = Calendar.getInstance();

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
*/
            }
        });

        btnhoraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendario = Calendar.getInstance();
                final SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm", Locale.getDefault());
                calendario.setTimeInMillis(getDataCadastroReserva(dateLong));
                final int hora = calendario.get(Calendar.HOUR_OF_DAY);
                final int min = calendario.get(Calendar.MINUTE);

                if (v == btnhoraF) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            getHora(hourOfDay, minute, calendario, formatHora, textoHoraF);
                            dateFim = calendario.getTime().getTime();
                            System.out.println("data e hora fim  " + dateFim);

                            //textoHoraI.setText(formatHora.format(calendario.getTime()));
                        }
                    }, hora, min, android.text.format.DateFormat.is24HourFormat(context));
                    timePickerDialog.show();
                }
            }
        });


/////////////////////////////////////////////////////////////////
    }


    private void getHora(int hourOfDay, int minute, Calendar calendar, SimpleDateFormat formataHora, TextView hora) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        hora.setText(formataHora.format(calendar.getTime()));
    }

      /*  private void showHorarioDialogo ( final ImageButton btnhoraF){
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
*/
      /*  private void showDateDialog ( final ImageButton btndata){
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
        }*/


    private long getDataCadastroReserva(long dateLong) {

        stringData = findViewById(R.id.text_calendario);
        Intent intent = getIntent();
        Bundle bundleParam = intent.getExtras();
        if (bundleParam != null) {
            String data = bundleParam.getString("DataStr");
            dateLong = bundleParam.getLong("Date");
            System.out.println("DATE LONG " + dateLong);

            stringData.setText(data);

            return dateLong;
        } else {
            Toast.makeText(CadastroReservaActivity.this, "jisus me ajuda pq a data ta nula", Toast.LENGTH_LONG).show();
        }
        return 0;
    }

}


