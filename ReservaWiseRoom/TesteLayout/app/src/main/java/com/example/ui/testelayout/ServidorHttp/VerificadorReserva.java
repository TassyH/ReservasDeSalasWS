package com.example.ui.testelayout.ServidorHttp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerificadorReserva extends AsyncTask<String, Void, String>
{
    String urlWS = "http://172.30.248.99:8080/ReservaDeSala/rest/alocacaosala/alocacao_sala/";

    @Override
    protected String doInBackground(String... strings)
    {
        StringBuilder result = new StringBuilder();
        try
        {
            URL url = new URL(urlWS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("authorization", "secret");
            conn.setRequestProperty("id_organizacao", strings[0]);
            conn.setConnectTimeout(2000);

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
                result.append(line);
            }
            rd.close();





            System.out.println("cadastro de reservas deu boa sim irmao");
            return result.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("cadastro de reservas nao deu boa nao irmao");
        }
        return result.toString();
    }


}
