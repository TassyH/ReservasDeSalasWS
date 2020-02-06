package com.example.ui.testelayout.activityfragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.testelayout.R;
import com.example.ui.testelayout.activitysnormal.CadastroUsuarioActivity;
import com.example.ui.testelayout.activitysnormal.LoginUsuarioActivity;
import com.example.ui.testelayout.ui.fragment_main.MainActivity;

public class ContaUsuarioFragment extends Fragment {
   TextView user_nome, user_email, user_senha;
    EditText edNome, edEmail, edSenha;
   Spinner spOrganizacao;
   LoginUsuarioActivity login;
   ImageButton btnSair;
   SharedPreferences sharedPreferences;
   SharedPreferences.Editor editor;
    String nomeString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_conta_usuario, container, false);

          btnSair = (ImageButton)view.findViewById(R.id.btn_sair_conta);
          user_nome = (TextView)view.findViewById(R.id.tv_user_nome);
          user_email = (TextView)view.findViewById(R.id.tv_user_email);
          user_senha = (TextView)view.findViewById(R.id.tv_user_senha);

          /* nomeString = getActivity().getIntent().getExtras().getString("email");
           user_nome.setText(nomeString);*/


        btnSair.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferences pref = getContext().getSharedPreferences("USER_LOGIN", 0);
               SharedPreferences.Editor editor = pref.edit();
               editor.remove("userEmail");
               editor.remove("userName");
               editor.remove("userId");
               editor.remove("userIdOrganizacao");
               editor.remove("userNomeEmpresa");
               editor.remove("userTipoEmpresa");
               editor.commit();

               Intent intent = new Intent(view.getContext(), LoginUsuarioActivity.class);
               startActivity(intent);


          }

       });



        return view;
    }
}
