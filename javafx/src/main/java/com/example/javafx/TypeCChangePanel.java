package com.example.javafx;

import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;

public class TypeCChangePanel {

    private static TypeCChangePanel uniqueInstance = null;
    private Tipoconserva tp;

    private TypeCChangePanel() {}

    public static TypeCChangePanel getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new TypeCChangePanel();
        return uniqueInstance;
    }

    public void in(Tipoconserva tp) {
        this.tp = tp;
    }

    public void out() {
        tp = null;
    }

    public Tipoconserva get() {
        return tp;
    }

}
