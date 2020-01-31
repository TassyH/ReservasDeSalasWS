package com.example.ui.testelayout.activitysnormal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ui.testelayout.R;
import com.example.ui.testelayout.ServidorHttp.VerificadorLogin;
import com.example.ui.testelayout.activityfragments.ListaSalasFragment;
import com.example.ui.testelayout.ui.fragment_main.MainActivity;

public class LoginUsuarioActivity extends AppCompatActivity {
    EditText edEmailLog, edSenhaLog;
    Button btnLogin, btnCadastrar2;

    public static final String TITULO_APPBAR = "login Usuario";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        setTitle(TITULO_APPBAR);

        final EditText edEmailLog = (EditText) findViewById(R.id.ed_login_email);
        final EditText edSenhaLog = (EditText) findViewById(R.id.ed_login_senha);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnCadastrar2 = (Button) findViewById(R.id.btn_cadastro2);

        btnCadastrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String emailString = edEmailLog.getText().toString().trim();
                    String senhaString = edSenhaLog.getText().toString().trim();

                    if (emailString.isEmpty()|| senhaString.isEmpty()){

                    }else{
                        Intent intent = new Intent(LoginUsuarioActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                    if (emailString.isEmpty()|| senhaString.isEmpty()){

                    }else{
                        Intent intent = new Intent(LoginUsuarioActivity.this,MainActivity.class);
                        startActivity(intent);
                    }


                    exibirMensagem(new VerificadorLogin().execute(emailString, senhaString).get());

                } catch (Exception e) {

                }
            }
        });
    }

    public void exibirMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }




    }





