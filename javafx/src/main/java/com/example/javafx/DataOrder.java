package com.example.javafx;

import com.example.database.DAL.Encomenda;
import com.example.database.DAL.Utilizador;

public class DataOrder {
    private static DataOrder uniqueInstance = null;
    private Encomenda order;

    private DataOrder() {}

    public static DataOrder getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new DataOrder();
        return uniqueInstance;
    }

    public void in(Encomenda order) {
        this.order = order;
    }

    public void out() {
        order = null;
    }

    public Encomenda get() {
        return order;
    }
}
