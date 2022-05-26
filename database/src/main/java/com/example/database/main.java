package com.example.database;

import com.example.database.BLL.*;
import com.example.database.DAL.*;


public class main {
    public static void main(String[] args) {
        /*Utilizador user = new Utilizador();
        user.setNome("Inês Puga Alves");
        user.setUsername("inespuga");
        String psw = LogicDataBase.passEncrypt("piloto");
        user.setPassword(psw);
        user.setCargo("A");
        user.setEmail("inespuga2002@gmail.com");
        user.setNumtel(969890424);
        UtilizadorBLL.create(user);*/
        /*for(Utilizador i : UtilizadorBLL.readAll()) {
            if(i.getUsername().equals("inespuga")) {
                i.setStatus(1);
                UtilizadorBLL.update(i);
            }
        }*/
        /*Peixe p = new Peixe();
        p.setNome("Sardinha");
        p.setStock(500);
        PeixeBLL.create(p);*/
        /*Peixe p = PeixeBLL.readByCodpeixe(1);
        Tipoconserva tp = new Tipoconserva();
        tp.setCodpeixe(p.getCodpeixe());
        tp.setDescricao("Sardinha com molho de tomate");
        tp.setPrecoactvenda(2.99);
        tp.setNome("Sardinha em conserva");
        tp.setQtdstock(100);
        TipoconservaBLL.create(tp);*/
        Utilizador u = UtilizadorBLL.readById(21);
        u.setCargo("GV");
        UtilizadorBLL.update(u);
    }
}
