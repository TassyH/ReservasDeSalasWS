package com.example.ui.testelayout.activityfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.testelayout.R;

public class ContaUsuarioFragment extends Fragment {
   TextView tv_user_nome, tv_user_email, tv_user_senha;
   EditText edNome, edEmail, edSenha;
   Spinner spOrganizacao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_conta_usuario, container, false);

        TextView user_nome = view.findViewById(R.id.tv_user_nome);
        TextView user_email = view.findViewById(R.id.tv_user_email);
        TextView user_senha = view.findViewById(R.id.tv_user_senha);

        edNome = (EditText) view.findViewById(R.id.ed_cadastro_nome);
        edEmail = (EditText) view.findViewById(R.id.ed_cadastro_email);
        edSenha = (EditText) view.findViewById(R.id.ed_cadastro_senha);
        spOrganizacao = (Spinner) view.findViewById(R.id.sp_organizacao);

        /*String nomeString = edNome.getText().toString().trim();
        String emailString = edEmail.getText().toString().trim();
        String senhaString = edSenha.getText().toString().trim();*/






        return view;
    }
}
