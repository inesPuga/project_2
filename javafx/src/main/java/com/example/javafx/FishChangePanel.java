package com.example.javafx;

import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;

public class FishChangePanel {

    private static FishChangePanel uniqueInstance = null;
    private Peixe p;

    private FishChangePanel() {}

    public static FishChangePanel getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new FishChangePanel();
        return uniqueInstance;
    }

    public void in(Peixe tp) {
        this.p = p;
    }

    public void out() {
        p = null;
    }

    public Peixe get() {
        return p;
    }

}
