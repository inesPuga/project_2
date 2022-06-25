package com.example.web.Model;

import com.example.database.DAL.Tipoconserva;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Tipoconserva product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getNome(), product.getQtdstock()));
    }

}
