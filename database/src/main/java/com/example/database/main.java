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

        /*Utilizador u = new Utilizador();
        u.setUsername("luis");
        u.setNome("Luís Camião");
        u.setNumtel(912400588);
        u.setStatus(1);
        u.setPassword(LogicDataBase.passEncrypt("ola"));
        u.setCargo("GS");
        u.setEmail("luis@gmail.com");
        UtilizadorBLL.create(u);
        Gestorstock gs = new Gestorstock();
        gs.setIduser(u.getIduser());
        GestorstockBLL.create(gs);*/

        /*for(Gestorstock i : GestorstockBLL.readAll()) {
            if(i.getCodgs()==1) {
                GestorstockBLL.delete(i);
            }
        }
        for(Utilizador i : UtilizadorBLL.readAll()) {
            if(i.getIduser()==41) {
                UtilizadorBLL.delete(i);
            }
        }*/
        Requisicao req = new Requisicao();
        req.setData("30/05/2022");
        req.setCodgs(2);
        req.setQtdtotal(100);
        RequisicaoBLL.create(req);
        Peixerequisicao reqfish = new Peixerequisicao();
        reqfish.setCodpeixe(1);
        reqfish.setCodrequisicao(1);
        reqfish.setQtd(2);
        PeixerequisicaoBLL.create(reqfish);

    }
}
