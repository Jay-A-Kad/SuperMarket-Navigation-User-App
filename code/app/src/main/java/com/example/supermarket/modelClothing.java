package com.example.supermarket;

import android.widget.Button;

public class modelClothing {

    String p_name , p_qty, p_price,p_id;
    Button addProductToCart;

     modelClothing() {

    }


    public modelClothing(String p_name, String p_qty, String p_price, String p_id, Button addProductToCart) {
        this.p_name = p_name;
        this.p_qty = p_qty;
        this.p_price = p_price;
        this.p_id = p_id;
        this.addProductToCart = addProductToCart;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_qty() {
        return p_qty;
    }

    public void setP_qty(String p_qty) {
        this.p_qty = p_qty;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public Button getAddProductToCart() {
        return addProductToCart;
    }

    public void setAddProductToCart(Button addProductToCart) {
        this.addProductToCart = addProductToCart;
    }
}
