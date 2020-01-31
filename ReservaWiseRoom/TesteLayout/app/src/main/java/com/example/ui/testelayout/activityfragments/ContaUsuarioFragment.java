package com.example.ui.testelayout.activityfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.testelayout.R;

public class ContaUsuarioFragment extends Fragment {
   TextView tv_user_nome, tv_user_email, tv_user_senha;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_conta_usuario, container, false);

        TextView user_nome = view.findViewById(R.id.tv_user_nome);
        TextView user_email = view.findViewById(R.id.tv_user_email);
        TextView user_senha = view.findViewById(R.id.tv_user_senha);

        return view;
    }
}
