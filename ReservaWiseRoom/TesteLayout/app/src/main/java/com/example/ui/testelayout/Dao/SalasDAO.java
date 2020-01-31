package com.example.ui.testelayout.Dao;

import com.example.ui.testelayout.Modal.Sala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalasDAO {

    public List<Sala> lista() {
        List<Sala> salas = new ArrayList<>(Arrays.asList(
                new Sala(1 , "Conselho Jedi", "img_conselho_jedi", true, "1o andar", true, "12 pessoas",true, "sala pipipopo"),
                new Sala(2 , "Tabula redonda", "img_tabula_redonda", true, "1o andar", true, "13 pesssoas",true,"sala popopipip"),
                new Sala(3,"Olimpo", "img_olimpo", true, "1o andar", true, "13 pesssoas",true, "sala de conversa"),
                new Sala(4,"A sala de vidro", "img_virdo", true, "1o andar", true, "13 pesssoas",true, "sala para apresentacao")));
        return salas;
    }
}
