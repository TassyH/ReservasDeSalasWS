package com.example.ui.controledesalas.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ui.controledesalas.Modal.Organizacao;
import com.example.ui.controledesalas.R;
import com.example.ui.controledesalas.ServidorHttp.VerificadorCadastro;
import com.example.ui.controledesalas.ServidorHttp.VerificadorDominio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CadastroUsuarioActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    EditText edNome, edEmail, edSenha;
    private  Spinner spOrganizacao;
    Button btnCadastrar, btnCadastrar2;
    private  List<Organizacao> lista= new ArrayList<>();
    List<String> listaDeStrings = new ArrayList<>();
    private int orgSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);



        // inicializacaoDosCampos();
        setContentView(R.layout.activity_cadastro_usuario);

        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar);
        btnCadastrar2 = (Button) findViewById(R.id.btn_cadastro2);
        edNome = (EditText) findViewById(R.id.ed_cadastro_nome);
        edEmail = (EditText) findViewById(R.id.ed_cadastro_email);
        edSenha = (EditText) findViewById(R.id.ed_cadastro_senha);
        spOrganizacao = (Spinner)findViewById(R.id.sp_organizacao);


        spOrganizacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  orgSelect = lista.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nomeString = edNome.getText().toString().trim();
                String emailString = edEmail.getText().toString().trim();
                String senhaString = edSenha.getText().toString().trim();

                JSONObject usuarioJson = new JSONObject();


                try {
                    usuarioJson.put("email", emailString);
                    usuarioJson.put("nome", nomeString);
                    usuarioJson.put("senha", senhaString);
                    usuarioJson.put("idOrganizacao", orgSelect);

                    if (emailString.isEmpty() || nomeString.isEmpty()|| senhaString.isEmpty()){
                         
                    }else{
                        Intent intent = new Intent(CadastroUsuarioActivity.this, LoginUsuarioActivity.class);
                        startActivity(intent);
                    }



                    String novoUsuarioDecode;
                    String userCod = new String(Base64.encodeToString(usuarioJson.toString().getBytes("UTF-8"), Base64.NO_WRAP));
                    System.out.println(usuarioJson.toString());

                    exibirMensagem(new VerificadorCadastro().execute(userCod).get());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e){

                }
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////

        edEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    String emailAfterTextChange = edEmail.getText().toString();
                    if(emailAfterTextChange.contains("@")){
                        String [] emailCompleto = emailAfterTextChange.split("@");
                        if(emailAfterTextChange.length()>0){
                            String dominio = emailCompleto[1];
                            if (dominio.contains(".")) {
                                try {
                                    String listaOrganizacao = new VerificadorDominio().execute(dominio).get();
                                    System.out.println("dominio: " + dominio);
                                    lista.clear();
                                    listaDeStrings.clear();
                                    /////parswado o json///////////////////////
                                    JSONArray jsonLista = new JSONArray(listaOrganizacao);

                                    if (jsonLista.length() > 0) {
                                        for (int i = 0; i < jsonLista.length(); i++) {

                                            JSONObject jsonObjeto = jsonLista.getJSONObject(i);

                                            if (jsonObjeto.has("id") && jsonObjeto.has("nome") && jsonObjeto.has("tipoOrganizacao")) {

                                                int id = jsonObjeto.getInt("id");
                                                String nome = jsonObjeto.getString("nome");
                                                String tipoOrganizacao = jsonObjeto.getString("tipoOrganizacao");

                                                Organizacao novaOrganizacao = new Organizacao();
                                                novaOrganizacao.setId(id);
                                                novaOrganizacao.setNome(nome);
                                                novaOrganizacao.setTipoOrganizacao(tipoOrganizacao);


                                                String tipoOrganizacaoStr = "";
                                                if(novaOrganizacao.getTipoOrganizacao().equals("M")){
                                                    tipoOrganizacaoStr = "Matriz";
                                                }
                                                else {
                                                    tipoOrganizacaoStr = "Filial";
                                                }


                                                listaDeStrings.add(novaOrganizacao.getNome() + " - " + tipoOrganizacaoStr);
                                                lista.add(novaOrganizacao);

                                            }

                                        }

                                        ArrayAdapter<String> adapter = new ArrayAdapter<>(CadastroUsuarioActivity.this, android.R.layout.simple_spinner_item, listaDeStrings);
                                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        spOrganizacao.setAdapter(adapter);
                                        spOrganizacao.setVisibility(View.VISIBLE);

                                    } else {
                                        //nada
                                    }

                                   } catch (ExecutionException e) {
                                       e.printStackTrace();
                                   } catch (InterruptedException e) {
                                      e.printStackTrace();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }
            }
        });



    }


    public void exibirMensagem(String mensagem)
    {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }


}

