package com.example.ui.controledesalas.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.Toast;

import com.example.ui.controledesalas.Adapter.ListaReservaAdapter;
import com.example.ui.controledesalas.DatePickerCalendar;
import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.ServidorHttp.VerificadorCadastroReserva;
import com.example.ui.controledesalas.TimePickerCalendar;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroReservaActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
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
    private SharedPreferences configuraHora;
    DialogFragment datePickerCalendar = new DatePickerCalendar();
    DialogFragment timePickerCalendar = new TimePickerCalendar();
    long dateTimeInicialMiliseconds;
    long dateTimeFinalMiliseconds;
    public static final String mypreference = "USER_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);
        setTitle(TITULO_APPBAR);
        configuraHora = getSharedPreferences("HORA_INICIAL", 0);


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

                    dateTimeFormat(textoHoraI, textoHoraF);

                    reservaJson.put("nome", nomeString);
                    reservaJson.put("descricao", descricaoString);
                    reservaJson.put("data_hora_inicio", dateTimeInicialMiliseconds);
                    reservaJson.put("data_hora_final", dateTimeFinalMiliseconds);
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




        ////////////////////////////data e hora //////////////////////////////////////
        textoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerCalendar.show(getSupportFragmentManager(),"Date pk");

            }
        });

        textoHoraI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerCalendar.show(getSupportFragmentManager(), "Hora Picker");
                boolean horaInicial;
                SharedPreferences.Editor editor = configuraHora.edit();
                horaInicial = true;
                editor.putBoolean("HoraInicial", horaInicial).commit();


            }
        });

        textoHoraF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerCalendar.show(getSupportFragmentManager(), "Hora Picker");
                boolean HoraInicial;
                SharedPreferences.Editor editor = configuraHora.edit();
                HoraInicial = false;
                editor.putBoolean("HoraInicial", HoraInicial).commit();
            }
        });


/////////////////////////////////////////////////////////////////
    }

    //Sala sala = salaAtual();

   /* private Sala salaAtual() {
        Intent intent = getIntent();
        return (Sala) intent.getSerializableExtra("salaSelecionada");
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView dia = findViewById(R.id.text_print_data);

        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, year);
        calendario.set(Calendar.MONTH, month);
        calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        //textoHoraI.setText(simpleDateFormat.format(calendario.getTime()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String date = (simpleDateFormat.format(calendario.getTime()));
        dia.setText(String.format(date));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Boolean horaInicial = configuraHora.getBoolean("HoraInicial", false);
        String horaFormatada;
        String minutoFormatado;

        horaFormatada = String.valueOf(hourOfDay);
        minutoFormatado = String.valueOf(minute);

        if(hourOfDay<10){
            horaFormatada = "0"+hourOfDay;
        }
        if(minute<10){
            minutoFormatado = "0"+minute;
        }

        if (horaInicial) {
            String horaI = horaFormatada + ":" + minutoFormatado;
            textoHoraI.setText(horaI);
        } else if (!horaInicial) {
            String horaF = horaFormatada + ":" + minutoFormatado;
            textoHoraF.setText(horaF);
        }
    }

      private void dateTimeFormat(TextView textoHoraI, TextView textoHoraF) {
        SimpleDateFormat dateTimeFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          textoData = (TextView) findViewById(R.id.text_print_data);
          String dataString = textoData.getText().toString();//ok
          String horaInicioStr = textoHoraI.getText().toString();//ok
          String horaFimStr = textoHoraF.getText().toString();//ok

          String dateTimeInicial = dataString + " - "+horaInicioStr.trim();
          String dateTimeFinal = dataString + " - "+horaFimStr.trim();


          System.out.println("josh "+dateTimeInicial);
          System.out.println("tyler "+dateTimeFinal);

          try {

              Date dateTimeInicioParseado = dateTimeFormat.parse(dateTimeInicial);
              Date dateTimeFimParseado = dateTimeFormat.parse(dateTimeFinal);
              dateTimeInicialMiliseconds=dateTimeInicioParseado.getTime();
              dateTimeFinalMiliseconds = dateTimeFimParseado.getTime();


              System.out.println("data long"+dateTimeInicialMiliseconds);
              System.out.println("hora long"+dateTimeFinalMiliseconds);

          } catch (ParseException e) {
              e.printStackTrace();
          }
      }

    }





