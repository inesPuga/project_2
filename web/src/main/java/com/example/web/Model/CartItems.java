package com.example.web.Model;

import com.example.database.DAL.Tipoconserva;

import java.util.ArrayList;
import java.util.List;

public class CartItems {
    private List<Tipoconserva> products = new ArrayList<>();

    public void add(List<Tipoconserva> products, Tipoconserva product) {
        products.add(product);
    }

    public void print(List<Tipoconserva> products) {
        for(Tipoconserva i : products) {
            System.out.println(i.getNome());
        }
    }

    public void remove(List<Tipoconserva> products, Tipoconserva product) {
        products.remove(product);
    }

}
