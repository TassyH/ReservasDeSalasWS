package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    int ano, mes, dia, hora, minuto;
    int ano1, mes1, dia1, hora1, minuto1;
    int anoFinal, mesFinal, diaFinal, horaFinal, minutoFinal;
    int anoFinalF, mesFinalF, diaFinalF, horaFinalF, minutoFinalF;
    TextView txt, txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       txt = (TextView) findViewById(R.id.txt);
        txt1 = (TextView) findViewById(R.id.txt1);
        Button btn = (Button)findViewById(R.id.btn);
        Button btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                ano1 = c.get(Calendar.YEAR);
                mes1 = c.get(Calendar.MONTH);
                dia1 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialogo = new DatePickerDialog(MainActivity.this, MainActivity.this, ano1, mes1, dia1);
                datePickerDialogo.show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                ano = calendar.get(Calendar.YEAR);
                mes = calendar.get(Calendar.MONTH);
                dia = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, MainActivity.this, ano, mes, dia);
                datePickerDialog.show();
            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        anoFinal = year;
        mesFinal = month +1;
        diaFinal = dayOfMonth;

        anoFinalF = year;
        mesFinalF = month +1;
        diaFinalF = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);


        Calendar calendar = Calendar.getInstance();
        hora1 = calendar.get(Calendar.HOUR_OF_DAY);
        minuto1 = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialogo = new TimePickerDialog(MainActivity.this, MainActivity.this, hora, minuto, DateFormat.is24HourFormat(this));
        timePickerDialogo.show();

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, MainActivity.this, hora1, minuto1, DateFormat.is24HourFormat(this));
        timePickerDialog.show();


    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
       horaFinal = hourOfDay;
       minutoFinal = minute;
        horaFinalF = hourOfDay;
        minutoFinalF = minute;
        txt.setText(diaFinal+"/"+mesFinal+"/"+anoFinal+" - " + horaFinal+":"+minutoFinal);
        txt1.setText(diaFinalF+"/"+mesFinalF+"/"+anoFinalF+" - " + horaFinalF+":"+minutoFinalF);
    }


}
