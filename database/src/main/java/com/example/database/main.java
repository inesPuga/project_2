package com.example.database;

import com.example.database.BLL.*;
import com.example.database.DAL.*;


public class main {
    public static void main(String[] args) {
        /*Utilizador user = new Utilizador();
        user.setUsername("inespuga");
        user.setStatus(1);
        user.setCargo("A");
        user.setEmail("inespuga2002@gmail.com");
        user.setNome("Inês Puga Alves");
        user.setNumtel(969890424);
        user.setPassword(LogicDataBase.passEncrypt("piloto"));
        UtilizadorBLL.create(user);*/
        /*Gestorstock gs = new Gestorstock();
        gs.setIduser(user.getIduser());
        GestorstockBLL.create(gs);*/

        /*for(Encomenda i : EncomendaBLL.readAll()){
            System.out.println(i.getCodencomenda());
        }*/

        Estadoencomenda ee = new Estadoencomenda();
        ee.setCodencomenda(1);
        ee.setIde(1);
        ee.setDtee("08/06/2022");
        EstadoencomendaBLL.create(ee);
    }
}
