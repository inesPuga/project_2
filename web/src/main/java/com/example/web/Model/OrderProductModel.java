package com.example.web.Model;

public class OrderProductModel {
    private int idProduct;
    private int idOrder;
    private int idUser;

    public OrderProductModel() {}

    public OrderProductModel(int idProduct, int idOrder) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
}
